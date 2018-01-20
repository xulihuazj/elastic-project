/*
 * OnlineCondition.java 1.0.0 2017/12/16  21:32 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017/12/16  21:32 created by xulihua
 */
package com.xulihuazj.boot.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
/**
 * @Description:
 * @author: xulihua
 * @date: 2017/12/16 21:32
 */
public class OnlineCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        org.springframework.core.env.Environment currentEnvironment = conditionContext.getEnvironment();
        String online = currentEnvironment.getProperty("api.environment");
        // 返回true 代表该环境初始化相关bean false 不初始化
        return StringUtils.isNotBlank(online) && online.toUpperCase().equals("PROD");
    }
}
