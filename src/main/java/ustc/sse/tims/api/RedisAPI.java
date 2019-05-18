package ustc.sse.tims.api;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import redis.clients.jedis.Jedis;
import ustc.sse.tims.util.Constant;
import ustc.sse.tims.util.RedisUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.api
 * @date 2019/5/17-10:02
 * @Copyright: (c) 2019 USTC. All rights reserved.
 *
 * @Description:  处理redis查询，存储相关操作
 */

@Service
public class RedisAPI {

    @PostMapping("/IpOpts")
    public void setIpOpts(Map<String,String> map){

        Jedis redis = RedisUtil.getJedis();
        for(String key : map.keySet()){
             redis.set(key,map.get(key));
        }

    }

    public static Map<String,String> getIpOpts(){

        Map <String,String> ipOpts = new HashMap<>();
        Jedis redis = RedisUtil.getJedis();

        //模糊查询
        Set<String> keys =  redis.keys(Constant.ip_regex);

        for(String key : keys ){
            ipOpts.put(key,redis.get(key));
        }

        return ipOpts;
    }

}
