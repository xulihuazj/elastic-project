package com.xulihuazj.initializer;/*
 * MailInitializer.java 1.0.0 2017年7月11日 下午4:00:42
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017年7月11日 下午4:00:42 created by xulihua
 */


import com.xulihuazj.error.SystemErrorCode;
import com.xulihuazj.exception.BusinessException;
import com.xulihuazj.log.LogHelper;
import com.xulihuazj.mail.MailUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 邮箱服务器初始化
 *
 * @author xulihua
 * @description
 * @date 2017年7月11日下午4:00:42
 */
@Configuration
public class MailInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LogManager.getLogger(MailInitializer.class);

    //代理服务器地址
    @Value("${mail.host}")
    private String mailProxyHost;

    //端口
    @Value("${mail.port}")
    private Integer mailPort;

    //邮箱协议
    @Value("${mail.protocol}")
    private String mailProtocol;

    @Value("${mail.username}")
    private String mailUserName;

    @Value("${mail.password}")
    private String mailPassword;

    /*  @PostConstruct
      public void initMail(){
          try {
              MailUtil.init(mailProxyHost, mailPort, mailProtocol, mailUserName, mailPassword);
          } catch (Exception e) {
              LogHelper.error(logger, "初始化邮件发送服务失败");
            e.printStackTrace();
          }
      }*/
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ExecutorService executor = Executors.newCachedThreadPool();
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            try {
                Long startTime = System.currentTimeMillis();
                LogHelper.info(logger, "开始异步初始化邮件发送服务，开始时间为：{0}", startTime);
                MailUtil.init(mailProxyHost, mailPort, mailProtocol, mailUserName, mailPassword);
                LogHelper.info(logger, "结束异步初始化邮件发送服务，结束时间为：{0}，总用时为：{1}", System.currentTimeMillis(), System.currentTimeMillis() - startTime);
                return "SUCCESS";
            } catch (Exception e) {
                LogHelper.exception(e, logger, "初始化邮件发送服务失败");
                throw new BusinessException(SystemErrorCode.SYSTEM_SERVICE_HANDLE_ERROR);
            } finally {
                executor.shutdown();
            }
        });
        executor.execute(futureTask);
    }
}
