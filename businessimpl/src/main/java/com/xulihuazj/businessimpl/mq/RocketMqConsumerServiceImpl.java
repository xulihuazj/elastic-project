/*
 * RocketMqConsumerServiceImpl.java 1.0.0 2018/01/16  14:07 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/16  14:07 created by xulihua
 */
package com.xulihuazj.businessimpl.mq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.xulihuazj.business.mq.RocketMqConsumerService;

import javax.annotation.Resource;

public class RocketMqConsumerServiceImpl implements RocketMqConsumerService {


    @Resource
    private DefaultMQPushConsumer defaultMQPushConsumer;

    public void getMessage(){
        defaultMQPushConsumer.getme
    }

}
