/*
 * RocketMqProducerService.java 1.0.0 2018/01/16  14:05 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/16  14:05 created by xulihua
 */
package com.xulihuazj.business.mq;

/**
 * 消息生产者，负责产生消息，一般由业务系统负责产生消息
 */
public interface RocketMqProducerService {

    /**
     * RocketMq发送消息
     * @author 徐礼华  
     * @date   2018/01/16 18:02
     */
    void producerSendMessage();
}
