#include <pcap.h>
#include <time.h>
#include <stdlib.h>
#include <stdio.h>
#include<string.h>
#include<sys/socket.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include<unistd.h>

#include <hiredis/hiredis.h>

#include <linux/ip.h>
#include <linux/udp.h>

#define SIZE_ETHERNET 14	//以太网帧首部长度
#define BUFSIZE 1024
#define TTL 600

char redishost[20];
int redisport;
redisContext* conn;

void sendToRedis(char * ip,char * opt55){

	if(conn == NULL) conn = redisConnect(redishost, redisport);
    if(conn->err)   printf("connection error:%s\n", conn->errstr);

	redisReply* reply;
	char req[1024];
	sprintf(req,"get %s",ip);
	reply = redisCommand(conn, req);
	if(reply->str!=NULL){
		sprintf(req,"del %s",ip);
		redisCommand(conn, req);
		freeReplyObject(reply);
	}
	sprintf(req,"set %s %s",ip,opt55);
    redisCommand(conn, req);
	sprintf(req,"expire %s %d",ip,TTL);
	redisCommand(conn, req);
}


//回调函数：嗅探到DHCP请求包后续操作

void getPacket(u_char * arg, const struct pcap_pkthdr * pkthdr, const u_char * packet)
{
	int ip_opt55 = 0;

	u_char* data = packet;
	u_char* option = NULL;
	u_char len = 0;
	u_char* ptr = data;
	u_char* ptr2,* ptr3, * ptr4;
	ptr2 = ptr3 = ptr4 = NULL;
	int option_55[20] = {0};
	u_char opt55str [100] = {'\0'};
	int ipnums [4] = {0};
	u_char ipstr[20] = {'\0'};


	printf("get a dhcp packet. \n");

	u_char* ip = data + SIZE_ETHERNET;
	struct iphdr* iph = (struct iphdr*)ip;
	u_char* udp = (ip+ iph->ihl*4);
	u_char* dhcp = udp + 8;

	ptr = dhcp;

	//查找DHCP的 magic字段 63 82 53 63
	while( *ptr != 255) {
		if((*ptr) != 0x63 ) ptr++;

		else{
			ptr2 = ptr+1;
			ptr3 = ptr+2;
			ptr4 = ptr+3;
			if((*ptr2) != 0x82 || (*ptr3) != 0x53 || (*ptr4) != 0x63) continue;
			else break;
		}
	}

	//查找option55 及option50
	if((*ptr) !=255) {
		option = ptr + 4;
		while(ip_opt55 != 2 && *option!=255){

			while (*option != 0x37 && *option !=50) {   //55,50

				len = *(++option);
				option += len+1;
			}
			printf("option: %d \n",*option);

			int op = *option;
			len = *(++option);
			printf("len:%d \n",len);
			option++;

			//截取option55
			if(op == 55){
				int i=0;
				while (i < len) {

					option_55[i++] = *(option++);
				}
				option_55[i] = '\0';
				ip_opt55 ++;
			}
			//截取option50
			else{
				int i=0;
				while(i<4){
					ipnums[i] = *(option++);
					i++;
				}

				ip_opt55 ++;
			}

		}

		//如果没同时包含ip及option55，忽略；否则，将其数组抓换成字符串
		if(ip_opt55 != 2) ;
		else{
			char tmp[4];
			int i = 0;
			for ( ; i<4;i++){

				sprintf(tmp, "%d", ipnums[i]);
				sprintf(ipstr, "%s%s", ipstr, tmp);
				sprintf(ipstr, "%s%s", ipstr, ".");
			}
			int len1 = strlen(ipstr);
			ipstr[len1-1] = '\0';

			printf("ip: %s\n", ipstr);

			printf("option55: ");

			i=0;
			while( option_55[i] != '\0') {

				sprintf(tmp, "%d", option_55[i++]);
				sprintf(opt55str, "%s%s", opt55str, tmp);
				sprintf(opt55str, "%s%s", opt55str, ",");
			}
			int len2 = strlen(opt55str);
			opt55str[len2-1]= '\0';
			printf("%s \n",opt55str);


			sendToRedis(ipstr,opt55str);

		}
		ip_opt55 = 0 ;

	}
	else  printf("not a suit packet \n");

}

//开启嗅探
int setup_sniffer()
{
	char errbuf[PCAP_ERRBUF_SIZE];

	bpf_u_int32 mask;						//网络设备dev的掩码
	bpf_u_int32 net;						//网络设备dev的ip

	char *dev = pcap_lookupdev(errbuf);     //查找网卡设备
        if (dev == NULL) {
            fprintf(stderr, "Couldn't find default device: %s\n", errbuf);
            return(2);
        }
        printf("Device: %s\n", dev);

	//得到指定设备的net、mask
	if (pcap_lookupnet(dev, &net, &mask, errbuf) == -1) {
		fprintf(stderr, "Can't get netmask for device %s\n", dev);
		net = 0;
		mask = 0;
	}

	//打开设备进行嗅探，返回一个pcap_t类型的指针，后面操作都要用到这个指针
	//获得数据包捕获描述字函数
	//dev设备名称；参与定义捕获数据的最大字节数，是否置于混杂模式，
	//设置超时时间0表示没有超时等待，errBuf是出错返回NULL时用于传递错误信息
	pcap_t * device = pcap_open_live(dev, 65535, 1, 0, errbuf);
	if (device == NULL) {
		fprintf(stderr, "Couldn't open device %s: %s\n", dev, errbuf);
		return(2);
	}

	struct bpf_program filter;
	char filterstr[50] = { 0 };

	//过滤规则字符串
	sprintf(filterstr, "src port 68 and dst port 67");

	//编译表达式，函数返回-1为失败，返回其他值为成功
	//device:会话句柄
	//&filter:被编译的过滤器版本的地址的引用
	//filterstr:过滤规则字符串
	//1:表达式是否被优化的整形量：0：没有，1：有
	//net：应用此过滤器的网络的网络号
	if (pcap_compile(device, &filter, filterstr, 1, net) == -1) {
		fprintf(stderr, "Couldn't parse filter %s: %s\n", filterstr, pcap_geterr(device));
		return(2);
	}

	//设置过滤器，使用这个过滤器
	pcap_setfilter(device, &filter);
	//device:会话句柄
	//&filterstr:被编译的表达式版本的引用

	//-1代表循环抓包直到出错结束，>0表示循环x次，
	//最后一个参数一般之置为null
	pcap_loop(device, -1, getPacket, NULL);
	return 0;
}


int main(int argc, char * argv[])
{
	if(argc!=3){
		printf("Usage: %s redishost redisport \n",argv[0]);
		exit(0);
	}
	sprintf(redishost,"%s",argv[1]);
	redisport = atoi(argv[2]);

	setup_sniffer();
}

