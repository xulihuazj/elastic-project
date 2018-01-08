/*
 * ActiveMqConfig.java 1.0.0 2018/01/08  17:44 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/08  17:44 created by xulihua
 */
package com.xulihuazj.boot.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

@Configuration
public class ActiveMqConfig {

    @Bean
    public Queue logQueue() {
        return new ActiveMQQueue("LOG_QUEUE");
    }
}
