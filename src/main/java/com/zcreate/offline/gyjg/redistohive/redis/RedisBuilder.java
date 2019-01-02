package com.zcreate.offline.gyjg.redistohive.redis;

import redis.clients.jedis.JedisCluster;

/**
 * Copyright (C)
 * All rights reserved
 * <p>
 * 项目名称 ： RTComputation
 * 项目描述：
 * <p>
 * com.zcreator.bigdata.rtc.common.redis
 * <p>
 * created by guangzhong.wgz
 * date time 2018/11/22
 **/
public class RedisBuilder {

    /**
     * @param hosts
     * @return
     */
    public static synchronized RedisClient getRedisClient(String hosts) {
        if (hosts.isEmpty()) {
            throw new RuntimeException("hosts is empty");
        }
        JedisCluster jedisCluster = RedisUtils.getClusterClient(hosts);

        return new RedisClient(jedisCluster);
    }
}
