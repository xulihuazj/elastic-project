/*
 * OnlineCondition.java 1.0.0 2018/01/25  17:49 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  17:49 created by xulihua
 */
package com.xulihuazj.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 仅生产环境初始化条件
 */
public class OnlineCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment onlineEnv = context.getEnvironment();
        String online = onlineEnv.getProperty("api.environment");
        // 返回true 代表该环境初始化相关bean false 不初始化
        return StringUtils.isNotBlank(online) && online.toUpperCase().equals("PROD");
    }
}

