# 终端识别管理系统

目前：
DHCP 部分已整合Redis及数据库，修改了相应的查询逻辑

数据库sql文件已上传,在resources\DataBase\MySQL目录下

nmap 部分xml解析没有问题，现需要写Ajax查询NMAP指令的结果

要点：

1、c代码(dhcp_plugin_c/dhcp_plugin_ipopts_redis.c)运行在32位ubuntu虚拟机上

2、虚拟机下采用桥接模式

3、运行需要的第三方库：pcap, hiredis 自行百度下载安装

4、编译： gcc dhcp_plugin_redis.c -o dhcppr -lpcap -lhiredis

5、运行： sudo ./dhcppr redishost redisport 

6、redis运行在docker上，自行百度安装docker及redis

7、docker上运行redis:docker run -d -p 6379:6379 --name 别名  redis镜像名

8、json 解析采用了jackson

9、java操作redis采用了Jedis

10、需要在中作相应的配置修改SystemConfig.properties





