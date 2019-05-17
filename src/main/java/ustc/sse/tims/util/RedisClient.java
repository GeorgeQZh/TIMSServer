package ustc.sse.tims.util;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.*;
import ustc.sse.tims.config.SystemConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.util
 * @date 2019/5/17-10:31
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */


public class RedisClient {

    private Jedis jedis;//非切片额客户端连接
    private JedisPool jedisPool;//非切片连接池
    private ShardedJedis shardedJedis;//切片额客户端连接
    private ShardedJedisPool shardedJedisPool;//切片连接池

    private static String host;
    private static int port;
    private static String password;

    @Autowired
    private void setParams(SystemConfig conf){
        host = conf.getRedis_host();
        port = conf.getRedis_port();
        password = conf.getRedis_password();
    }

    public RedisClient() {
        initialPool();
        initialShardedPool();
        shardedJedis = shardedJedisPool.getResource();
        jedis = jedisPool.getResource();

    }

    /**
     * 初始化非切片池
     */
    private void initialPool() {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(5);
        config.setTestOnBorrow(false);

        jedisPool = new JedisPool(config,host,port);
    }

    /**
     * 初始化切片池
     */
    private void initialShardedPool() {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(5);
        config.setTestOnBorrow(false);
        // slave链接
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        shards.add(new JedisShardInfo(host,port,password));

        // 构造池
        shardedJedisPool = new ShardedJedisPool(config, shards);
    }

}
