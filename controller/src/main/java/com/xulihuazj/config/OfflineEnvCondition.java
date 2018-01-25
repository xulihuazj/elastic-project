/*
 * OfflineEnvCondition.java 1.0.0 2018/01/25  17:40 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  17:40 created by xulihua
 */
package com.xulihuazj.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.HashSet;
import java.util.Set;

/**
 生产环境不初始化bean 条件
 */
public class OfflineEnvCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        Environment env = context.getEnvironment();
        String offlineEnv = env.getProperty("api.environment");
        Set<String> envWhiteList = new HashSet<String>() {
            {
                add("DEV");
                add("TEST");
            }
        };
        // 返回true 代表该环境初始化相关bean false 不初始化
        return StringUtils.isBlank(offlineEnv) || envWhiteList.contains(offlineEnv.toUpperCase());
    }
}
