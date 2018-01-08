/*
 * LogConsumer.java 1.0.0 2018/01/08  17:52 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/08  17:52 created by xulihua
 */
package com.xulihuazj.businessimpl.mq;

import com.xulihuazj.business.mq.ActiveMqConsumerService;
import com.xulihuazj.log.LogHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ActiveMqConsumerImpl implements ActiveMqConsumerService {

    private Logger logger = LogManager.getLogger(ActiveMqConsumerImpl.class);

    @JmsListener(destination = "LOG_QUEUE")
    public void receivedQueue(String msg) {
        LogHelper.info(logger, "Has received from " + "LOG_QUEUE" + ", msg: " + msg);
        System.out.println("LOG_QUEUE" + "：" + msg);
    }

}
