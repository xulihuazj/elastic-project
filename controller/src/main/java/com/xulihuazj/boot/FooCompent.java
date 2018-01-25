/*
 * FooCompent.java 1.0.0 2017/12/17  21:10 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017/12/17  21:10 created by xulihua
 */
package com.xulihuazj.boot;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:
 * @author: xulihua
 * @date: 2017/12/17 21:10
 */
public class FooCompent {

    public static void main(final  String... args) throws InterruptedException {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:spring/rabbitmq-context.xml"
        );
        RabbitTemplate rabbitTemplate = context.getBean(RabbitTemplate.class)
                ;
        rabbitTemplate.convertAndSend("11111111");;
        Thread.sleep(1000);
        context.destroy();

    }
}
