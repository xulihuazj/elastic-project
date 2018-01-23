/*
 * MessageProcessorImpl.java 1.0.0 2018/01/16  18:35 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/16  18:35 created by xulihua
 */
package com.xulihuazj.businessimpl.mq.rokcetmq;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.xulihuazj.business.mq.rocketmq.MessageProcessor;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessorImpl implements MessageProcessor{

    @Override
    public boolean handleMessage(MessageExt messageExt) {
        System.out.println("receive : " + messageExt.toString());
        return true;
    }
}
