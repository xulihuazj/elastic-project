/*
 * ActiveMqConfig.java 1.0.0 2018/01/08  17:44 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/08  17:44 created by xulihua
 */
package com.xulihuazj.boot.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import javax.annotation.Resource;
import javax.jms.Queue;

@Configuration
public class ActiveMqConfig {

    private final String brokerUrl = "tcp://localhost:61616";

    @Bean
    public Queue logQueue() {
        return new ActiveMQQueue("LOG_QUEUE");
    }

    @Bean
    public ActiveMQConnectionFactory targetConnectionFactory() {
        // 真正可以产生Connection的ConnectionFactory，由对应的 JMS服务厂商提供
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrl);
        return activeMQConnectionFactory;
    }

    @Bean
    @Resource
    //定义连接池
    public PooledConnectionFactory pooledConnectionFactory(ActiveMQConnectionFactory activeMQConnectionFactory) {
        // 通过往PooledConnectionFactory注入一个ActiveMQConnectionFactory可以用来将Connection，
        // Session和MessageProducer池化这样可以大大减少我们的资源消耗，
        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        pooledConnectionFactory.setConnectionFactory(activeMQConnectionFactory);
        //最大连接数
        pooledConnectionFactory.setMaxConnections(10);
        return pooledConnectionFactory;
    }

    @Bean
    @Resource
    public SingleConnectionFactory connectionFactory(PooledConnectionFactory pooledConnectionFactory) {
        return new SingleConnectionFactory(pooledConnectionFactory);
    }

    @Bean
    @Resource
    public JmsTemplate createJmsTemplate(SingleConnectionFactory connectionFactory) {
        return new JmsTemplate(connectionFactory);
    }

    @Bean
    public ActiveMQQueue queueDestination() {
        return new ActiveMQQueue("NTF_MOCK_INPUT");
    }

    @Bean
    public ActiveMQQueue responseQueue() {
        return new ActiveMQQueue("NTF_MOCK_OUTPUT");
    }

}
