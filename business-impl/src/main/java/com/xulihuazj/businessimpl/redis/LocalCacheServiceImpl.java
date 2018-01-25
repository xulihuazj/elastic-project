/*
 * LocalCacheServiceImpl.java 1.0.0 2018/01/23  12:54 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/23  12:54 created by xulihua
 */
package com.xulihuazj.businessimpl.redis;

import com.alibaba.fastjson.TypeReference;
import com.xulihuazj.business.redis.CacheService;
import com.xulihuazj.date.CustomDateUtil;
import com.xulihuazj.log.LogHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 本地缓存实现
 */
public class LocalCacheServiceImpl implements CacheService {

    private final Logger logger = LogManager.getLogger(LocalCacheServiceImpl.class);

    //对象锁
    private static final Object lockHelper = new Object();

    //用于本地缓存的Map对象
    private static Map<String, Object> cacheMap = new HashMap<>();

    private void setCacheMap(String key, CacheObject object) {
        if (StringUtils.isBlank(key)) {
            LogHelper.error(logger, "缓存key不能为空!");
            throw new IllegalArgumentException("设置缓存时key不能为空!");
        } else {
            cacheMap.put(key, object);
        }
    }

    @Override
    public void set(String key, Object value) {
        set(key, new CacheObject(value));
    }

    @Override
    public void set(String key, Object value, Date expireTime) {
        set(key, new CacheObject(value, expireTime));
    }

    @Override
    public void set(String key, Object value, int expireTime, TimeUnit timeUnit) {
        Date expireTimeDate = CustomDateUtil.getExpireTime(expireTime, timeUnit);
        set(key, new CacheObject(value, expireTimeDate));
    }

    @Override
    public <T> T get(Class<T> classObject, String key) {
        return this.getCacheMap(key);
    }

    @Override
    public <T> T get(TypeReference<T> type, String key) {
        return this.getCacheMap(key);
    }

    @Override
    public void remove(String key) {
        LocalCacheServiceImpl.cacheMap.remove(key);
    }

    @Override
    public Long incr(String key, int expireSecond) {
        Long value = 1l;
        synchronized (lockHelper) {
            CacheObject cacheObject = getObject(key);
            if (cacheObject == null) {
                set(key, value, expireSecond, TimeUnit.SECONDS);
            } else {
                value = (Long) cacheObject.getValue();
                value++;
                set(key, value, cacheObject.getExpireTime());
            }
        }
        return value;
    }

    @Override
    public Long decr(String key, int expireSecond) {
        Long value = -1L;
        synchronized (lockHelper) {
            CacheObject cacheObject;
            cacheObject = getObject(key);
            if (cacheObject == null) {
                set(key, value, expireSecond, TimeUnit.SECONDS);
            } else {
                value = (Long) cacheObject.getValue();
                value--;
                set(key, value, cacheObject.getExpireTime());
            }
        }
        return value;
    }

    /**
     * 获取HashMap中的缓存项
     *
     * @param key 缓存key
     */
    private <T> T getCacheMap(String key) {
        T returnObject = null;
        CacheObject cacheObject = getObject(key);
        if (cacheObject != null) {
            returnObject = (T) cacheObject.getValue();
        }
        return returnObject;
    }

    /**
     * 获取HashMap中缓存值
     *
     * @param key 缓存key
     * @return
     */
    private CacheObject getObject(String key) {
        CacheObject cacheObject = (CacheObject) cacheMap.get(key);
        if (cacheObject != null) {
            // 如果有缓存,但是缓存过期,删除
            if (cacheObject.getExpireTime() != null && cacheObject.getExpireTime().before(new Date())) {
                cacheObject = null;
                cacheMap.remove(key);
            }
        }
        return cacheObject;
    }

    private class CacheObject {
        // 缓存的值
        private Object value;

        //缓存的过期时间
        private Date expireTime;

        CacheObject(Object value) {
            this.value = value;
        }

        CacheObject(Object value, Date expireTime) {
            this.value = value;
            this.expireTime = expireTime;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Date getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(Date expireTime) {
            this.expireTime = expireTime;
        }
    }
}
