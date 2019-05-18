package ustc.sse.tims.util;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

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

    //建立、返回redis连接
    public static synchronized Jedis getJedis (){

        if(jedis==null){
            jedis = new Jedis (Constant.redis_host,Constant.redis_port);

            jedis.connect();
            System.out.println("redis connecting success");
        }
        return jedis;

    }

}
