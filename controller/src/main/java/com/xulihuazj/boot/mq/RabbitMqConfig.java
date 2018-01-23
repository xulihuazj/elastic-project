/*
 * MqRabbitConfig.java 1.0.0 2018/01/23  11:37 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/23  11:37 created by xulihua
 */
package com.xulihuazj.boot.mq;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling//启用任务调度.
@RabbitListener(queues = "study")//启用Rabbit队列监听study key.
@ConditionalOnProperty(value = "rabbitmq.config.enabled", havingValue = "true", matchIfMissing = true)
public class RabbitMqConfig {

    private final Logger logger = LogManager.getLogger(RabbitMqConfig.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled(fixedDelay = 3000)//3s执行1次此方法;
    public void send() {
        rabbitTemplate.convertAndSend("study", "zhenqi");
    }




}
