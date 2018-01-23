/*
 * MessageProcessor.java 1.0.0 2018/01/16  18:37 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/16  18:37 created by xulihua
 */
package com.xulihuazj.business.mq.rocketmq;

import com.alibaba.rocketmq.common.message.MessageExt;

public interface MessageProcessor {

    /**
     * 处理消息的接口
     * @param message 消息
     * @return
     */
    boolean handleMessage(MessageExt message);

}
