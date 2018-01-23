/*
 * ActiveMqConsumer.java 1.0.0 2018/01/08  18:33 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/08  18:33 created by xulihua
 */
package com.xulihuazj.business.mq.activemq;


import javax.jms.Destination;

public interface ActiveMqConsumerService {

    String receiveMessage(Destination destination, Destination replyDestination);

}
