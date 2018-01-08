/*
 * LogProducer.java 1.0.0 2018/01/08  17:49
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/08  17:49 created by xulihua
 */
package com.xulihuazj.businessimpl.mq;

import com.xulihuazj.business.mq.ActiveMqProducerService;
import com.xulihuazj.log.LogHelper;
import org.apache.activemq.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.Queue;

@Service
public class ActiveMqProducerServiceImpl implements ActiveMqProducerService {

    private Logger logger = LogManager.getLogger(ActiveMqProducerServiceImpl.class);

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;


    /**
     * 注入JmsTemplate
     */
    @Resource(name = "jmsTemplate")
    private JmsTemplate jTemplate;

    @Autowired
    private Queue logQueue;

    @Override
    public void run(String... args) throws Exception {
        send("This is a log message.");
        LogHelper.info(logger, "Log Message was sent to the Queue named sample.log");
    }

    @Override
    public void send(String msg) {
        this.jmsMessagingTemplate.convertAndSend(this.logQueue, msg);
    }

    @Override
    public void sendMessage(Destination receivedestination, String message) {
        System.out.println("================生产者创建了一条消息==============");
        jTemplate.send(receivedestination, session -> session.createTextMessage("hello activeMQ:" + message));
        LogHelper.info(logger, "生产者创建了一条消息,消息是：{0}", message);
    }

}
