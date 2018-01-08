/*
 * CacheConfiguration.java 1.0.0 2018/01/08  16:03
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/08  16:03 created by xulihua
 */
package com.xulihuazj.boot.config;


import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.Resource;

@Configuration
// 标注启动了缓存
@EnableCaching
public class CacheConfiguration {


    /*
     * 据shared与否的设置,Spring分别通过CacheManager.create()或new CacheManager()方式来创建一个ehcache基地.
     */
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("config/ehcache.xml"));

        cacheManagerFactoryBean.setShared(true);
        return cacheManagerFactoryBean;
    }

    /*
     * ehcache 主要的管理器
     */
    @Bean(name = "appEhCacheCacheManager")
    @Resource
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean) {
        return new EhCacheCacheManager(bean.getObject());
    }

}
