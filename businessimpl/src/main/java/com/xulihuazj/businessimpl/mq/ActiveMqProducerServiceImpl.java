/*
 * LogProducer.java 1.0.0 2018/01/08  17:49
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/08  17:49 created by xulihua
 */
package com.xulihuazj.businessimpl.mq;

import com.xulihuazj.business.mq.ActiveMqProducerService;
import com.xulihuazj.log.LogHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;

@Service
public class ActiveMqProducerServiceImpl implements ActiveMqProducerService {

    private Logger logger = LogManager.getLogger(ActiveMqProducerServiceImpl.class);

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

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

}
