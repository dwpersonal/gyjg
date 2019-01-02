package com.zcreate.offline.gyjg.redistohive.utils;


import com.zcreate.offline.gyjg.redistohive.redis.RedisBuilder;
import com.zcreate.offline.gyjg.redistohive.redis.RedisClient;

public class RedisClientUtil {

    private static RedisClient redisClient;

    public static RedisClient getRedisClient(String hosts) {

        if (redisClient == null) {
            synchronized (RedisClientUtil.class) {
                redisClient = RedisBuilder.getRedisClient(hosts);
            }
        }
        return redisClient;
    }

}
