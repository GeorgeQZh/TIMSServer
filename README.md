# 终端识别管理系统

目前 DHCP 部分已经可以演示；nmap 部分仍然是 xml解析的问题

要点：

1、c代码(dhcp_plugin_c/dhcp_plugin_ipopts_redis.c)运行在32位ubuntu虚拟机上

2、虚拟机下采用桥接模式

3、运行需要的第三方库：pcap, hiredis 自行百度下载安装

4、编译： gcc dhcp_plugin_redis.c -o dhcppr -lpcap -lhiredis

5、运行： sudo ./dhcppr redishost redisport 

6、redis运行在docker上，自行百度安装docker及redis

7、docker上运行redis:docker run -d -p 6379:6379 --name 别名  redis镜像名

8、jason 解析采用了jackson

9、java操作redis采用了Jedis

10、需要在中作相应的配置修改SystemConfig.properties

11、xml解析值得一试的方向：采用dom4j进行解析


