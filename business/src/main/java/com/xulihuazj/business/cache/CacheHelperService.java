/*
 * CacheHelperService.java 1.0.0 2018/01/23  15:18 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/23  15:18 created by xulihua
 */
package com.xulihuazj.business.cache;

import com.xulihuazj.business.redis.CacheService;
import com.xulihuazj.config.CacheConfigModel;

public interface CacheHelperService {

    /**
     * 获取Redis配置
     */
    CacheConfigModel getRedisConfig();

    /**
     * 获取一个缓存Key
     */
    String getCacheKey(String key);

    /**
     * 获取缓存实例
     */
    CacheService getICache();

}
