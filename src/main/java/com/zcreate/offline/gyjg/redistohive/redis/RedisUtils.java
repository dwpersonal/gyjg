package com.zcreate.offline.gyjg.redistohive.redis;

import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RedisUtils {

    private static JedisPool jedisPool;// 非切片连接池
    private static JedisSentinelPool jedisSentinelPool;// 非切片连接池
    private static ShardedJedisPool shardedJedisPool;// 切片连接池
    private static JedisPoolConfig config;
    private static JedisCluster jedisCluster;

    static {
        config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000l);
        config.setTestOnBorrow(false);
        config.setJmxEnabled(true);
    }

    public static Jedis getJedisSentinelPool() {

        if (jedisSentinelPool == null) {
            synchronized (RedisUtils.class) {

                if (jedisSentinelPool == null) {
                    // JedisSentinelPool 初始化
                    String masterName = "cdh6";

                    //sentinel地址集合
                    Set<String> set = new HashSet<String>();
                    set.add("10.1.80.4:26379");
                    set.add("10.1.80.5:26379");
                    set.add("10.1.80.7:26379");
                    jedisSentinelPool = new JedisSentinelPool(masterName, set, config);
                }
            }
        }

        return jedisSentinelPool.getResource();
    }

    public static Jedis getJedis() {

        if (jedisPool == null) {
            synchronized (RedisUtils.class) {
                Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
                jedisClusterNodes.add(new HostAndPort("10.1.80.8", 6379));
                JedisCluster jc = new JedisCluster(jedisClusterNodes);
            }

        }
        return jedisPool.getResource();
    }

    public static ShardedJedis getShardedJedis() {

        //初始化非切片池
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        shards.add(new JedisShardInfo("cd1", 6379, "master"));
        shards.add(new JedisShardInfo("cd2", 6379, "master"));
        shards.add(new JedisShardInfo("cd3", 6379, "master"));
        shards.add(new JedisShardInfo("cd4", 6379, "master"));
        shards.add(new JedisShardInfo("cd5", 6379, "master"));
        shards.add(new JedisShardInfo("cd6", 6379, "master"));
        shards.add(new JedisShardInfo("cd7", 6379, "master"));
        shards.add(new JedisShardInfo("cd8", 6379, "master"));
        shardedJedisPool = new ShardedJedisPool(config, shards);

        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        return shardedJedis;
    }

    public static JedisCluster getClusterClient(String hosts) {

        if (jedisCluster == null) {
            synchronized (RedisUtils.class) {

                if (jedisCluster == null) {
                    if (hosts == null || "".equals(hosts)) {
                        throw new RuntimeException("hosts is empty");
                    }

                    Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
                    String[] hostStr = hosts.split(",");

                    for(int i = 0; i < hostStr.length; i ++){
                        String host = hostStr[i];
                        String args[] = host.split(":");
                        if (args.length != 2) {
                            throw new RuntimeException("host format error");
                        }
                        jedisClusterNodes.add(new HostAndPort(args[0], Integer.parseInt(args[1])));
                    }
                    jedisCluster = new JedisCluster(jedisClusterNodes);
                }
            }
        }
        return jedisCluster;
    }

    public static void getOne() {
        Jedis jedis = new Jedis("cdh1");
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
    }
}