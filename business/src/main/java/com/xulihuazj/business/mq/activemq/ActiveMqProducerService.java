/*
 * ProducerService.java 1.0.0 2018/01/08  18:32 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/08  18:32 created by xulihua
 */
package com.xulihuazj.business.mq.activemq;

import org.springframework.boot.CommandLineRunner;

import javax.jms.Destination;

public interface ActiveMqProducerService extends CommandLineRunner {

    /**
     * 生产者发送信息
     *
     * @param msg 信息
     * @author 徐礼华
     * @date 2018/01/08 18:36
     */
    void send(String msg);


    void sendMessage(Destination destination, final String message);

}
