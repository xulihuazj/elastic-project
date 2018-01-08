/*
 * ApiApplication.java 1.0.0 2017/12/16  15:08
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017/12/16  15:08 created by xulihua
 */
package com.xulihuazj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description:
 * @author: xulihua
 * @date: 2017/12/16 15:08
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
@ComponentScan
@Configuration
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ApiApplication.class);
        //application.setShowBanner(false);//关闭Spring Boot 的Banner（标语）显示
        application.setWebEnvironment(true);//设置当前Spring Boot 是否是一个web应用
        //application.setApplicationContextClass();
        application.addListeners();//添加Spring Boot 启动监听器，监听启动时各个活动
        application.run(args);

      /*  new SpringApplicationBuilder()//构建一个分层的ApplicationContext（多个具有父子关系的上下文）
                .showBanner(false)
                .sources(Parent.class)
                .child(Application.class)
                .run(args);*/
    }

}
