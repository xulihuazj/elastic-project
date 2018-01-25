/*
 * MqRabbitConfig.java 1.0.0 2018/01/23  11:37
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/23  11:37 created by xulihua
 */
package com.xulihuazj.boot.mq;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.xulihuazj.log.LogHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Configuration
@RabbitListener(queues = "study")//启用Rabbit队列监听study key.
@ConditionalOnProperty(value = "rabbitmq.config.enabled", havingValue = "true", matchIfMissing = true)
public class RabbitMqConfig {

    private final Logger logger = LogManager.getLogger(RabbitMqConfig.class);

    private final String exChange = "xulihua-item-exchange";

    @Value("${spring.rabbitmq.host}")
    private String rabbitmqHost;

    @Value("${spring.rabbitmq.username}")
    private String rabbitmqUserName;

    @Value("${spring.rabbitmq.password}")
    private String rabbitmqPassword;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Bean
    public Queue Queue() {
        return new Queue("hello");
    }

    @Bean
    public Connection instanceRabbitMq() {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(rabbitmqHost);
            connectionFactory.setVirtualHost(virtualHost);
            connectionFactory.setUsername(rabbitmqUserName);
            connectionFactory.setPassword(rabbitmqPassword);
            return connectionFactory.newConnection();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
            LogHelper.exception(logger, e, "RabbitMQ实例初始化失败！");
            throw new RuntimeException(e);
        }
    }

    @Bean
    @Resource
    public Channel channelInstance(Connection connection) {
        try {
            Channel channel = connection.createChannel();
            //声明（创建）队列
            channel.queueDeclare("test_queue", false, false, false, null);

            String message = "hello world!";

            channel.basicPublish("", "test_name", null, message.getBytes());
            //关闭通道和连接
            channel.close();
            connection.close();
            return null;
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
            LogHelper.exception(logger, e, "RabbitMQ发送消息失败！");
            throw new RuntimeException(e);
        }
    }

    @Bean
    public Channel consumerChannelInstance(Connection connection) {
        try {
            Channel channel = connection.createChannel();
            channel.queueDeclare("test_queue", false, false, false, null);
            QueueingConsumer consumer = new QueueingConsumer(channel);
            //true 消费自动确认，
            channel.basicConsume("test_name", true, consumer);
            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                System.out.println(message);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public Queue AMessage() {
        return new Queue("fanout.A");
    }

    @Bean
    public Queue BMessage() {
        return new Queue("fanout.B");
    }

    @Bean
    public Queue CMessage() {
        return new Queue("fanout.C");
    }

    //定义交换机
    @Bean
    FanoutExchange fanoutExchange() {
        FanoutExchange fanoutExchange = new FanoutExchange("fanoutExchange");
        return fanoutExchange;
    }

    //绑定消息到交换机
    @Bean
    @Resource(name = "AMessage")
    Binding bindingExchangeA(Queue message, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(message).to(fanoutExchange);
    }

    //绑定消息到交换机
    @Bean
    @Resource(name = "BMessage")
    Binding bindingExchangeB(Queue message, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(message).to(fanoutExchange);
    }

    //绑定消息到交换机
    @Bean
    @Resource(name = "CMessage")
    Binding bindingExchangeC(Queue message, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(message).to(fanoutExchange);
    }

    //定义 消息模板
    @Bean
    @Resource
    public RabbitTemplate initRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange(exChange);
        rabbitTemplate.setEncoding("UTF-8");
        return rabbitTemplate;

    }

}
