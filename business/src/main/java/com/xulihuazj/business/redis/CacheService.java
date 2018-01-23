/*
 * RedisCacheService.java 1.0.0 2018/01/23  12:39 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/23  12:39 created by xulihua
 */
package com.xulihuazj.business.redis;


import com.alibaba.fastjson.TypeReference;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Redis缓存接口
 */
public interface CacheService {

    /**
     * 设置一个缓存项，无过期时间
     *
     * @param key   缓存key
     * @param value 缓存value
     */
    void set(String key, Object value);

    /**
     * 设置一个缓存项，有固定的过期时间
     *
     * @param key        缓存key
     * @param value      缓存value
     * @param expireTime 过期时间
     */
    void set(String key, Object value, Date expireTime);

    /**
     * 设置一个缓存项，浮动过期时间
     *
     * @param key        缓存key
     * @param value      缓存value
     * @param expireTime 过期的级别数
     * @param timeUnit   单位（毫秒、秒、分、小时、天）
     */
    void set(String key, Object value, int expireTime, TimeUnit timeUnit);

    /**
     * 获取一个缓存项
     *
     * @param classObject Class对象
     * @param key         缓存key
     * @return 值
     */
    <T> T get(Class<T> classObject, String key);

    /**
     * 获取一个缓存项
     *
     * @param key 缓存key
     * @return
     */
    <T> T get(TypeReference<T> type, String key);

    /**
     * 删除一个缓存项
     *
     * @param key
     */
    void remove(String key);

    /**
     * 原子递增操作
     *
     * @param key
     * @param expireSecond 多少秒后过期(此参数只有在第一次创建缓存时起作用，后面再次递增不会改变缓存时长）
     */
    Long incr(String key, int expireSecond);

    /**
     * 原子递减操作
     *
     * @param key
     * @param expireSecond 多少秒后过期(此参数只有在第一次创建缓存时起作用，后面再次递增不会改变缓存时长）
     */
    Long decr(String key, int expireSecond);
}
