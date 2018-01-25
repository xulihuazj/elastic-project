/*
 * TestJobInitializer.java 1.0.0 2018/01/25  16:59 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  16:59 created by xulihua
 */
package com.xulihuazj.initializer;


import com.xulihuazj.log.LogHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用于测试环境自动执行 Job 调用
 *
 * @author xulihua
 * @description
 * @date 2018/01/23 21:08
 */

@Component
public class TestJobInitializer {

    private final Logger logger = LogManager.getLogger(TestJobInitializer.class);

    @Value("${job.execute.environment}")
    private String environment;

    private final String validEnvironment = "test";

    //每2分钟执行一次
    //@Scheduled(fixedDelay = 120 * 1000)
    @Scheduled(cron = "0 0/2 * * * ?")
    private void send() {
        if (StringUtils.isNotBlank(environment) && environment.equals(validEnvironment)) {
            LogHelper.debug(logger, "测试环境自身定时执行Job");
        }
    }

    //每6小时执行一次
    @Scheduled(cron = "0 0 0/6 * * ?")
    private void fulfilSettlementOrder() {
        if (StringUtils.isNotBlank(environment) && environment.equals(validEnvironment)) {
            LogHelper.debug(logger, "测试环境自身定时执行Job");
        }
    }
}
