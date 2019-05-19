package ustc.sse.tims.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

@Controller
public class RedisAPI {

    Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @PostMapping("/IpOpts")
    public String setIpOpts(@RequestParam("ip") String ip,
                          @RequestParam("option55") String opt55){
        Jedis redis = RedisUtil.getJedis();
        logger.debug("ip:"+ip);
        logger.debug("option55:"+opt55);

        //更新键值对
        if(redis.exists(ip)) {
            redis.del(ip);
        }
        redis.set(ip, opt55);
        //设置ttl=600s
        redis.expire(ip,600);
        return "success";
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
