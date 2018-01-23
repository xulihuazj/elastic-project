/*
 * RabbitMqProducerServiceImpl.java 1.0.0 2018/01/23  12:25 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/23  12:25 created by xulihua
 */
package com.xulihuazj.businessimpl.mq.rabbitmq;

import com.xulihuazj.business.mq.rabbitmq.RabbitMqProducerService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqProducerServiceImpl implements RabbitMqProducerService {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Override
    public void sendMessage(String message) {
        System.out.println("消息 : " + message);
        this.rabbitTemplate.convertAndSend("helloQueue", message);

    }
}
