package ustc.sse.tims.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import ustc.sse.tims.config.SystemConfig;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.util
 * @date 2019/5/17-10:07
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:   处理redis相关操作
 */


@Component
public class RedisUtil {

    private static Jedis jedis;

    private static String host;
    private static int port;


    @Autowired
    public void setParams(SystemConfig sconf){
        host = sconf.getRedis_host();
        port = sconf.getRedis_port();
    }

    //建立、返回redis连接
    public static synchronized Jedis getJedis (){

        if(jedis==null){
            jedis = new Jedis (host,port);

            jedis.connect();
            System.out.println("redis connecting success");
        }
        return jedis;

    }

}
