/*
 * RocketMqConsumerService.java 1.0.0 2018/01/23  12:24 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/23  12:24 created by xulihua
 */
package com.xulihuazj.business.mq.rabbitmq;

public interface RabbitMqConsumerService {

    /**
     * 监听器监听指定的队列
     * @param messageQueue
     */
    void messageListenerQueue(String messageQueue);

    void messageListenerQueue(String queue,String messageQueue);


}
