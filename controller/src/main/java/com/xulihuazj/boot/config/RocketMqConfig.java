/*
 * RocketMqConfig.java 1.0.0 2018/01/16  15:02 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/16  15:02 created by xulihua
 */
package com.xulihuazj.boot.config;

import com.alibaba.rocketmq.client.consumer.DefaultMQPullConsumer;
import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.client.producer.TransactionMQProducer;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.xulihuazj.boot.listener.MessageListener;
import com.xulihuazj.business.rocketmq.MessageProcessor;
import com.xulihuazj.log.LogHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class RocketMqConfig {

    private Logger logger = LogManager.getLogger(RocketMqConfig.class);

    @Value("${rocketmq.producer.producer_groupName}")
    private String producer_groupName;
    @Value("${rocketmq.producer.namesrvAddr}")
    private String producer_namesrvAddr;
    @Value("${rocketmq.producer.instanceName}")
    private String producer_instanceName;
    @Value("${rocketmq.producer.maxMessageSize}")
    private int producer_maxMessageSize; //4M
    @Value("${rocketmq.producer.sendMsgTimeout}")
    private int producer_sendMsgTimeout;

    @Value("${rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;
    @Value("${rocketmq.consumer.producer_groupName}")
    private String groupName;
    @Value("${rocketmq.consumer.topic}")
    private String topic;
    @Value("${rocketmq.consumer.tag}")
    private String tag;
    @Value("${rocketmq.consumer.consumeThreadMin}")
    private int consumeThreadMin;
    @Value("${rocketmq.consumer.consumeThreadMax}")
    private int consumeThreadMax;

    @Bean
    public DefaultMQProducer getRocketMQProducer() {
        if (StringUtils.isBlank(this.producer_groupName)) {
            throw new RuntimeException("producer_groupName is blank");
        }
        if (StringUtils.isBlank(this.namesrvAddr)) {
            throw new RuntimeException("nameServerAddr is blank");
        }
        if (StringUtils.isBlank(this.producer_instanceName)) {
            throw new RuntimeException("instanceName is blank");
        }
        try {
            DefaultMQProducer producer;
            producer = new DefaultMQProducer(this.producer_groupName);
            //nameserver地址
            producer.setNamesrvAddr(this.namesrvAddr);
            // 客户端实例名称
            producer.setInstanceName(producer_instanceName);
            //客户端限制的消息大小，超过报错，同时服务端也会限制
            producer.setMaxMessageSize(this.producer_maxMessageSize);
            //发送消息超时时间，单位毫秒
            producer.setSendMsgTimeout(this.producer_sendMsgTimeout);
            producer.start();
            LogHelper.info(logger, String.format("producer is start ! producer_groupName:[%s],namesrvAddr:[%s]", this.producer_groupName, this.namesrvAddr));
            return producer;
        } catch (MQClientException e) {
            LogHelper.error(logger, String.format("producer is error {}", e.getMessage(), e));
            throw new RuntimeException(e);
        }
    }

    @Bean
    public DefaultMQProducer getTransactionMQProducer() {
        try {
            TransactionMQProducer producer = new TransactionMQProducer(this.producer_groupName);
            //nameserver地址
            producer.setNamesrvAddr(this.namesrvAddr);
            // 客户端实例名称
            producer.setInstanceName(producer_instanceName);
            //客户端限制的消息大小，超过报错，同时服务端也会限制
            producer.setMaxMessageSize(this.producer_maxMessageSize);
            //发送消息超时时间，单位毫秒
            producer.setSendMsgTimeout(this.producer_sendMsgTimeout);
            producer.setTransactionCheckListener(new TransactionCheckListener() {
                @Override
                public LocalTransactionState checkLocalTransactionState(MessageExt msg) {
                    System.out.println("state---" + new String(msg.getBody()));
                    return LocalTransactionState.COMMIT_MESSAGE;
                }
            });
            producer.start();
            LogHelper.info(logger, String.format("producer is start ! producer_groupName:[%s],namesrvAddr:[%s]", this.producer_groupName, this.namesrvAddr));
            return producer;
        } catch (MQClientException e) {
            LogHelper.error(logger, String.format("producer is error {}", e.getMessage(), e));
            throw new RuntimeException(e);
        }
    }

    @Bean
    public DefaultMQPullConsumer getDefaultMQPullConsumer() {
        try {
            DefaultMQPullConsumer consumer = new DefaultMQPullConsumer(this.groupName);
            //设置消息模型，默认为集群消费
            //consumer.setMessageModel(MessageModel.CLUSTERING);
            consumer.setNamesrvAddr(namesrvAddr);
            consumer.start();
            return consumer;
        } catch (MQClientException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Bean
    @Resource
    public DefaultMQPushConsumer getRocketMqPushConsumer(MessageProcessor messageProcessor) {
        try {
            //Consumer组名
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(producer_groupName);
            //设置消息模型，默认为集群消费
            //consumer.setMessageModel(MessageModel.CLUSTERING);
            consumer.setNamesrvAddr(namesrvAddr);
            //消费线程池数量最小值
            consumer.setConsumeThreadMin(consumeThreadMin);
            //消费线程池数量最大值
            consumer.setConsumeThreadMax(consumeThreadMax);
            MessageListener messageListener = new MessageListener();
            messageListener.setMessageProcessor(messageProcessor);
            //注册监听器
            consumer.registerMessageListener(messageListener);
            //开始订阅topic
            consumer.subscribe(topic, this.tag);
            consumer.start();
            LogHelper.info(logger, "consumer is start !!! producer_groupName:{},topic:{},namesrvAddr:{}", producer_groupName, topic, namesrvAddr);
            return consumer;
        } catch (MQClientException e) {
            LogHelper.error(logger, "consumer is start !!! producer_groupName:{},topic:{},namesrvAddr:{}", producer_groupName, topic, namesrvAddr, e);
            throw new RuntimeException(e);
        }
    }


}
