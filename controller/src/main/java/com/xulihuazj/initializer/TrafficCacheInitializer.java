/*
 * TrafficCacheInitializer.java 1.0.0 2018/01/25  17:37 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  17:37 created by xulihua
 */
package com.xulihuazj.initializer;


import com.xulihuazj.business.hotel.HotelService;
import com.xulihuazj.error.SystemErrorCode;
import com.xulihuazj.exception.BusinessException;
import com.xulihuazj.log.LogHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

@Configuration
public class TrafficCacheInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LogManager.getLogger(TrafficCacheInitializer.class);

    @Value("${system.country}")
    private String systemCountry;

    @Resource
    private HotelService hotelServiceImpl;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if ("SINGAPORE".equals(systemCountry)) {
            ExecutorService executor = Executors.newCachedThreadPool();
            FutureTask<String> futureTask = new FutureTask<>(() -> {
                try {
                    Long startTime = System.currentTimeMillis();
                    LogHelper.info(logger, "开始异步加载房源附近交通信息，开始时间为：{0}", startTime);

                    //hotelServiceImpl.addTrafficCache(null);

                    LogHelper.info(logger, "结束异步加载房源附近交通信息，结束时间为：{0}，总用时为：{1}", System.currentTimeMillis(), System.currentTimeMillis() - startTime);
                    return "SUCCESS";
                } catch (Exception e) {
                    LogHelper.exception(e, logger, "异步加载房源附近交通信息发生异常");
                    throw new BusinessException(SystemErrorCode.SYSTEM_SERVICE_HANDLE_ERROR);
                } finally {
                    executor.shutdown();
                }
            });
            executor.execute(futureTask);
        }
    }
}
