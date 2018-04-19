package com.unhejing.service;

import com.unhejing.util.JedisUtil;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author unhejing
 * @create 2018-04-16 下午1:46
 **/

public class RedisService {

    /**
     * 添加hash类型的数据
     * @param key
     * @param map
     */
    public void addhash(String key,Map<String,String> map) {
        JedisUtil jedisUtil = JedisUtil.getInstance();
        jedisUtil.hmset(key,map);
    }

    /**
     * 根据key获取所有的域和值
     * @param key
     * @return Map
     */
    public Map<String, String> gethash(String key) {
        JedisUtil jedisUtil = JedisUtil.getInstance();
        return jedisUtil.hgetall(key);
    }

    public Set<String> getKeys(String pattern) {
        JedisUtil jedisUtil = JedisUtil.getInstance();
        return jedisUtil.keys(pattern);
    }

    /**
     * 添加SortSet型数据
     * @param key
     * @param value
     */
    public void addSortSet(String key, String value,int score) {
//        double score = new Date().getTime();
        JedisUtil jedisUtil = JedisUtil.getInstance();
        jedisUtil.zadd(key, value, score);
    }

    /**
     * 获取倒序的SortSet型的数据
     * @param key
     * @return
     */
    public Set<String> getDescSortSet(String key,int start,int end) {
        JedisUtil jedisUtil = JedisUtil.getInstance();
        return jedisUtil.zrevrange(key, start, end);
    }

    /**
     * 删除SortSet型数据
     * @param key
     * @param value
     */
    public void deleteSortSet(String key, String value) {
        JedisUtil jedisUtil = JedisUtil.getInstance();
        jedisUtil.zrem(key, value);
    }

    /**
     * 批量删除SortSet型数据
     * @param key
     * @param value
     */
    public void deleteSortSetBatch(String key, String[] value) {
        JedisUtil jedisUtil = JedisUtil.getInstance();
        jedisUtil.zrem(key, value);
    }

    /**
     * 范围获取倒序的SortSet型的数据
     * @param key
     * @return
     */
    public Set<String> getDescSortSetPage(String key,int start, int end) {
        JedisUtil jedisUtil = JedisUtil.getInstance();
        return jedisUtil.zrevrange(key, start, end);
    }

    /**
     * 获取SortSet型的总数量
     * @param key
     * @return
     */
    public long getSortSetAllCount(String key) {
        JedisUtil jedisUtil = JedisUtil.getInstance();
        return jedisUtil.zcard(key);
    }

    /**
     * 检查KEY是否存在
     * @param key
     * @return
     */
    public boolean checkExistsKey(String key) {
        JedisUtil jedisUtil = JedisUtil.getInstance();
        return jedisUtil.exists(key);
    }

    /**
     * 重命名KEY
     * @param oldKey
     * @param newKey
     * @return
     */
    public String renameKey(String oldKey, String newKey) {
        JedisUtil jedisUtil = JedisUtil.getInstance();
        return jedisUtil.rename(oldKey, newKey);
    }

    /**
     * 删除KEY
     * @param key
     */
    public void deleteKey(String key) {
        JedisUtil jedisUtil = JedisUtil.getInstance();
        jedisUtil.del(key);
    }

    /**
     * 设置失效时间
     * @param key
     * @param seconds 失效时间，秒
     */
    public void setExpireTime(String key, int seconds) {
        JedisUtil jedisUtil = JedisUtil.getInstance();
        jedisUtil.expire(key, seconds);
    }

    /**
     * 删除失效时间
     * @param key
     */
    public void deleteExpireTime(String key) {
        JedisUtil jedisUtil = JedisUtil.getInstance();
        jedisUtil.persist(key);
    }
}
