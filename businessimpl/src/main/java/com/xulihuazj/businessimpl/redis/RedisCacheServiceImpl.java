/*
 * RedisCacheServiceImpl.java 1.0.0 2018/01/23  12:53 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/23  12:53 created by xulihua
 */
package com.xulihuazj.businessimpl.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xulihuazj.business.redis.CacheService;
import com.xulihuazj.date.CustomDateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * Redis 缓存实现
 * 本地的缓存类,使用HashMap存储缓存数据
 * 注意:目前此类对于过期缓存使用懒清除机制(即过期的缓存项,再次访问时才会清除并释放内存,在没有访问时不会自动清除),使用时请酌情考虑内存问题。
 */
public class RedisCacheServiceImpl implements CacheService {

    private final Logger logger = LogManager.getLogger(RedisCacheServiceImpl.class);

    //Reids缓存线程池
    private JedisPool jedisPool;

    public RedisCacheServiceImpl(CacheConfigModel redisConfig) {
        if (null == redisConfig) {
            throw new IllegalArgumentException("使用redis时,redisConfig不能为空!");
        } else {
            JedisPoolConfig config = new JedisPoolConfig();
            // 配置文件
            config.setMaxIdle(redisConfig.getMaxIdle());
            config.setMaxTotal(redisConfig.getMaxTotal());
            config.setTestOnBorrow(redisConfig.isTestOnBorrow());
            config.setTestOnReturn(redisConfig.isTestOnReturn());
            jedisPool = new JedisPool(config, redisConfig.getHost(), redisConfig.getPort(), 3000, redisConfig.getPassword());
        }
    }

    @Override
    public void set(String key, Object value) {
        set(key, value, null);
    }

    @Override
    public void set(String key, Object value, Date expireTime) {
        if (expireTime != null && expireTime.before(new Date())) {
            throw new IllegalArgumentException("缓存的过期时间不能早于当前时间!");
        }
        int expireSecond = 0;
        if (expireTime != null) {
            expireSecond = (int) (expireTime.getTime() - new Date().getTime()) / 1000;
        }
        set(key, value, expireSecond, TimeUnit.SECONDS);
    }

    @Override
    public void set(String key, Object value, int expireTime, TimeUnit timeUnit) {
        if (null == key) {
            throw new IllegalArgumentException("设置缓存时key不能为空!");
        } else {
            Jedis jedis = null;
            try {
                String stringValue = value instanceof String ? value.toString() : JSON.toJSONString(value);
                jedis = jedisPool.getResource();
                jedis.set(key, stringValue);
                if (expireTime > 0) {
                    jedis.expire(key, CustomDateUtil.getSecondTime(expireTime, timeUnit));
                }
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }
    }




    @Override
    public <T> T get(Class<T> classObject, String key) {
        T returnObject = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String value = jedis.get(key);
            if (value != null) {
                try {
                    returnObject = JSON.parseObject(value.getBytes(), classObject);
                } catch (Exception ex) {
                    returnObject = (T) value;
                }
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return returnObject;
    }

    @Override
    public <T> T get(TypeReference<T> type, String key) {
        T returnObject = null;
        Jedis jedis = jedisPool.getResource();
        String value = jedis.get(key.toString());
        if (null != value) {
            returnObject = JSON.parseObject(value, type);
        }
        jedis.close();
        return returnObject;
    }

    @Override
    public void remove(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long incr(String key, int expireSecond) {
        Jedis jedis = null;
        Long returnValue;
        try {
            jedis = jedisPool.getResource();
            returnValue = jedis.incr(key);
            if (returnValue == 1 && expireSecond > 0) {
                jedis.expire(key, expireSecond);
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return returnValue;
    }

    @Override
    public Long decr(String key, int expireSecond) {
        Jedis jedis = null;
        Long returnValue;
        try {
            jedis = jedisPool.getResource();
            returnValue = jedis.decr(key);
            if (returnValue == -1 && expireSecond > 0) {
                jedis.expire(key, expireSecond);
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return returnValue;
    }
}
