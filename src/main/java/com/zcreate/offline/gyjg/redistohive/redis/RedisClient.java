package com.zcreate.offline.gyjg.redistohive.redis;

import redis.clients.jedis.JedisCluster;

import java.util.List;
import java.util.Set;

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
public class RedisClient {

    private JedisCluster jedisCluster;

    public RedisClient() {
    }

    public RedisClient(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    /**
     * 获取单值
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return jedisCluster.get(key);
    }

    /**
     * 写入值
     *
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        return jedisCluster.set(key, value);
    }

    /**
     * 自增计数
     *
     * @param key
     * @return
     */
    public Long incr(String key) {
        return jedisCluster.incr(key);
    }

    /**
     * 自增计数
     *
     * @param key
     * @param value 初始值
     * @return
     */
    public Long incrBy(String key, long value) {
        return jedisCluster.incrBy(key, value);
    }

    /**
     * 自减计数
     *
     * @param key
     * @return
     */
    public Long decr(String key) {
        return jedisCluster.decr(key);
    }

    /**
     * 自减计数
     *
     * @param key
     * @param value 初始值
     * @return
     */
    public Long decrBy(String key, long value) {
        return jedisCluster.decrBy(key, value);
    }

    /**
     * 批量获取值
     *
     * @param keys
     * @return
     */
    public List<String> mget(String... keys) {
        return jedisCluster.mget(keys);
    }

    /**
     * 存储范围值
     *
     * @param key
     * @param offset
     * @param value
     * @return
     */
    public Long setRange(String key, long offset, String value) {
        return jedisCluster.setrange(key, offset, value);
    }

    /**
     * 获取指定范围的值
     *
     * @param key
     * @param startOffset
     * @param endOffset
     * @return
     */
    public String getRange(String key, int startOffset, int endOffset) {
        return jedisCluster.getrange(key, startOffset, endOffset);
    }

    /**
     * 指定过期时间存储值
     *
     * @param key
     * @param keepaliveLong 单位秒
     * @param value
     * @return
     */
    public String setex(String key, int keepaliveLong, String value) {
        return jedisCluster.setex(key, keepaliveLong, value);
    }

    /**
     * 从左边写入
     *
     * @param key
     * @param value
     * @return
     */
    public Long lpush(String key, String value) {
        return jedisCluster.lpush(key, value);
    }

    /**
     * 出桡
     *
     * @param key
     * @return
     */
    public String lpop(String key) {
        return jedisCluster.lpop(key);
    }

    /**
     * 从左边开始获取
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> lrange(String key, long start, long end) {
        return jedisCluster.lrange(key, start, end);
    }

    /**
     * 删出指定的值
     *
     * @param key
     * @param count 删出的个数
     * @param value 指定的值
     * @return
     */
    public Long lrem(String key, long count, String value) {
        return jedisCluster.lrem(key, count, value);
    }

    /**
     * 返回数据长度
     *
     * @param key
     * @return
     */
    public Long lrem(String key) {
        return jedisCluster.llen(key);
    }

    /**
     * 删出下标这外的元数
     *
     * @param key
     * @return
     */
    public String ltrim(String key, long start, long end) {
        return jedisCluster.ltrim(key, start, end);
    }

    /**
     * 获取指定下标的元素
     *
     * @param key
     * @param offSet 下标位置
     * @return
     */
    public String lindex(String key, long offSet) {
        return jedisCluster.lindex(key, offSet);
    }


    /**
     * set集合新增
     *
     * @param key
     * @param value 值
     * @return
     */
    public Long sadd(String key, String value) {
        return jedisCluster.sadd(key, value);
    }

    /**
     * 返回sets集合所有值
     *
     * @param key
     * @return
     */
    public Set<String> smembers(String key) {
        return jedisCluster.smembers(key);
    }

    /**
     * 删出sets集合指定的值
     *
     * @param key
     * @return
     */
    public Long srem(String key, String value) {
        return jedisCluster.srem(key, value);
    }

    /**
     * 指定的值是否存在sets集合中
     *
     * @param key
     * @return
     */
    public Boolean sismember(String key, String value) {
        return jedisCluster.sismember(key, value);
    }

    /**
     * sets集合求交集
     *
     * @param keys
     * @return
     */
    public Set<String> sinter(String... keys) {
        return jedisCluster.sinter(keys);
    }

    /**
     * sets集合求并集
     *
     * @param keys
     * @return
     */
    public Set<String> sunion(String... keys) {
        return jedisCluster.sunion(keys);
    }

    /**
     * sets集合求差集
     *
     * @param keys
     * @return
     */
    public Set<String> sdiff(String... keys) {
        return jedisCluster.sdiff(keys);
    }

    /**
     * zset中添加元素
     *
     * @param key
     * @param score 权重
     * @param value
     * @return
     */
    public Long zadd(String key, double score, String value) {
        return jedisCluster.zadd(key, score, value);
    }

    /**
     * 按照权重值排序
     *
     * @param key
     * @param start (0, 开始)
     * @param end   (-1, 取到最后)
     * @return
     */
    public Set<String> zrange(String key, long start, long end) {
        return jedisCluster.zrange(key, start, end);
    }

    /**
     * 删出指定的值
     *
     * @param key
     * @param value
     * @return
     */
    public Long zrem(String key, String value) {
        return jedisCluster.zrem(key, value);
    }

    /**
     * 统计zset集合中的元素中
     *
     * @param key
     * @return
     */
    public Long zcard(String key) {
        return jedisCluster.zcard(key);
    }

    /**
     * 查看zset集合中value权重
     *
     * @param key
     * @param value
     * @return
     */
    public double zscore(String key, String value) {
        return jedisCluster.zscore(key, value);
    }

    /**
     * 统计zset集合中权重某个范围内（1.0——5.0），元素的个数
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Long zcount(String key, double min, double max) {
        return jedisCluster.zcount(key, min, max);
    }

    /**
     * hashs中添加元数
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hset(String key, String field, String value) {
        return jedisCluster.hset(key, field, value);
    }

    /**
     * hashs中添加整型元数
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hincrBy(String key, String field, long value) {
        return jedisCluster.hincrBy(key, field, value);
    }

    /**
     * 返回hashs中的所有值
     *
     * @param key
     * @return
     */
    public List<String> hvals(String key) {
        return jedisCluster.hvals(key);
    }

    /**
     * hashs中删除键值对
     *
     * @param key
     * @return
     */
    public Long hdel(String key, String field) {
        return jedisCluster.hdel(key, field);
    }

    /**
     * hashs中判断是否存在
     *
     * @param key
     * @param field
     * @return
     */
    public Boolean hexists(String key, String field) {
        return jedisCluster.hexists(key, field);
    }

    /**
     * hashs中获取对应的值
     *
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field) {
        return jedisCluster.hget(key, field);
    }

    /**
     * hashs中获取多个对应的值
     *
     * @param key
     * @param field
     * @return
     */
    public List<String> hmget(String key, String... field) {
        return jedisCluster.hmget(key, field);
    }

    /**
     * 获取hashs中所有的key
     *
     * @param key
     * @return
     */
    public Set<String> hkeys(String key) {
        return jedisCluster.hkeys(key);
    }

    /**
     * 清除所有数据， 慎用
     */
    public String flushDatabase() {
        return jedisCluster.flushDB();
    }

}