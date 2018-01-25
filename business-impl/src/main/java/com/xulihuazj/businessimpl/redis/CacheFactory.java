/*
 * CacheFactory.java 1.0.0 2018/01/23  14:57 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/23  14:57 created by xulihua
 */
package com.xulihuazj.businessimpl.redis;

import com.xulihuazj.business.redis.CacheService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CacheFactory {

    private final Logger logger = LogManager.getLogger(CacheFactory.class);

    // 本地缓存
    private static CacheService localCacheServiceImpl = new LocalCacheServiceImpl();

    // Redis缓存
    private static Map<Integer, CacheService> redisCacheMap = new HashMap<>();

    /**
     * 获取一个本地缓存操作实例
     */
    public static CacheService getLocalCache() {
        return localCacheServiceImpl;
    }

    /**
     * 获取一个Redis缓存操作实例, 在内网使用时, config请传入null或config.getHost置为空, 这时会返回一个本地Cache的操作接口
     */
    public static CacheService getRedisCache(CacheConfigModel config) {
        // 没有config或者config中的Host为空时,返回一个本地缓存的操作接口
        if (config == null || StringUtils.isEmpty(config.getHost())) {
            return localCacheServiceImpl;
        } else {
            if (redisCacheMap.get(config.hashCode()) == null) {
                synchronized (redisCacheMap) {
                    redisCacheMap.put(config.hashCode(), new RedisCacheServiceImpl(config));
                }
            }
            return redisCacheMap.get(config.hashCode());
        }
    }

}
