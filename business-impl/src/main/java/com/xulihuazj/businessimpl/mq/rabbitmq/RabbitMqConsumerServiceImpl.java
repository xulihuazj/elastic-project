/*
 * RabbitMqConsumerServiceImpl.java 1.0.0 2018/01/23  12:29 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/23  12:29 created by xulihua
 */
package com.xulihuazj.businessimpl.mq.rabbitmq;

import com.xulihuazj.business.mq.rabbitmq.RabbitMqConsumerService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqConsumerServiceImpl implements RabbitMqConsumerService {

    @Override
    @RabbitListener(queues = "queue")
    @RabbitHandler
    public void messageListenerQueue(String messageQueue) {
        System.out.println("接收消息队列" + messageQueue);
    }

    @Override
    public void messageListenerQueue(String queue, String messageQueue) {

    }


}
