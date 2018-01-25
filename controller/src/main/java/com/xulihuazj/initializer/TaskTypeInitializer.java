/*
 * TaskTypeInitializer.java 1.0.0 2018/01/25  17:01
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  17:01 created by xulihua
 */
package com.xulihuazj.initializer;

import com.xulihuazj.business.payment.PaymentService;
import com.xulihuazj.business.task.ImageTaskService;
import com.xulihuazj.business.task.TaskSubBiz;
import com.xulihuazj.enums.task.TaskTypeEnum;
import com.xulihuazj.log.LogHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class TaskTypeInitializer {

    private Logger logger = LogManager.getLogger(TaskTypeInitializer.class);

    private static final Map<String, TaskSubBiz> taskMap = new LinkedHashMap<>();

    @Resource
    private ImageTaskService imageTaskServiceImpl;

    @Resource
    private PaymentService paymentServiceImpl;

    @PostConstruct
    public void cacheTrafficInfo() {
        LogHelper.info(logger, "初始化定时任务");
        taskMap.put(TaskTypeEnum.IMAGE.getCode(), imageTaskServiceImpl);
        taskMap.put(TaskTypeEnum.REFUND.getCode(), paymentServiceImpl);
    }

}