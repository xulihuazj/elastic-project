/*
 * ExcuterCosumer.java 1.0.0 2018/1/21  15:01 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/1/21  15:01 created by xulihua
 */
package com.xulihuazj.businessimpl.rocketmq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @Description:
 * @author: xulihua
 * @date: 2018/1/21 15:01
 */
public class ExcuterCosumer {

    @Resource
    private DefaultMQPushConsumer defaultMQPushConsumer;



}

class Listener implements MessageListenerConcurrently {

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messageExtList, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        try {
            for (MessageExt message : messageExtList) {
                String topic = message.getTopic();
                String messageBody = new String(message.getBody(), "utf-8");
                String tags = message.getTags();
                System.out.println("收到消息：" + "topci" + topic + ",tags：" + tags + ",message：" + messageBody);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
