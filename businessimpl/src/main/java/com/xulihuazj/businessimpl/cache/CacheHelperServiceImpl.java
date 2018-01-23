/*
 * CacheHelperServiceImpl.java 1.0.0 2018/01/23  15:17 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/23  15:17 created by xulihua
 */
package com.xulihuazj.businessimpl.cache;

import com.xulihuazj.business.cache.CacheHelperService;
import com.xulihuazj.business.redis.CacheService;
import com.xulihuazj.businessimpl.redis.CacheFactory;
import com.xulihuazj.config.CacheConfigModel;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class CacheHelperServiceImpl implements CacheHelperService {

    //Redis 配置
    private static volatile CacheConfigModel redisConfig = null;

    private static Object lockHelper = new Object();

    private static String cachePrefix = "GLOBAL_";

    @Override
    public CacheConfigModel getRedisConfig() {
        if (redisConfig == null) {
            synchronized (lockHelper) {
                if (redisConfig == null) {
                    redisConfig = new CacheConfigModel();
                }
            }
        }
        return redisConfig;
    }

    @Override
    @Bean
    @ConfigurationProperties(prefix = "redis")
    public String getCacheKey(String key) {
        return cachePrefix + key;
    }

    @Override
    public CacheService getICache() {
        return CacheFactory.getRedisCache((CacheConfigModel) this.getRedisConfig());
    }
}
