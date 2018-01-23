/*
 * RocketMqProducerTest.java 1.0.0 2018/01/19  10:26 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/19  10:26 created by xulihua
 */
package com.xulihuazj.api.externalapi;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

public class RocketMqProducerTest {

    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("Producer");
        producer.setNamesrvAddr("192.168.1.179:9876;192.168.1.206;192.168.1.208;192.168.1.214");
        //当刷盘不成功时，重试
        producer.setRetryAnotherBrokerWhenNotStoreOK(true);
        try {
            producer.start();
            Message message = new Message("PushTopic",
                    "push",
                    "1",
                    "Just for test.".getBytes());

            SendResult result = producer.send(message);
            System.out.println("id:" + result.getMsgId() +
                    " result:" + result.getSendStatus());

            message = new Message("PushTopic",
                    "push",
                    "2",
                    "Just for test.".getBytes());

            result = producer.send(message);
            System.out.println("id:" + result.getMsgId() +
                    " result:" + result.getSendStatus());

            message = new Message("PullTopic",
                    "pull",
                    "1",
                    "Just for test.".getBytes());

            result = producer.send(message);
            System.out.println("id:" + result.getMsgId() +
                    " result:" + result.getSendStatus());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }

    private void rocketMqSelector() {
        DefaultMQProducer producer = new DefaultMQProducer("Producer");
        producer.setNamesrvAddr("192.168.1.179:9876;192.168.1.206;192.168.1.208;192.168.1.214");
        try {
            producer.start();
            Message message = new Message("PushTopic",
                    "push",
                    "1",
                    "Just for test.".getBytes());
            long orderId = 12345L;
            SendResult sendResult = producer.send(message, (mqs, msg, arg) -> {
                Integer id = (Integer) arg;
                int index = id % mqs.size();
                return mqs.get(index);
            }, orderId);
            System.out.println(sendResult);
        } catch (MQClientException | RemotingException | InterruptedException | MQBrokerException e) {
            e.printStackTrace();
        }
    }

}
