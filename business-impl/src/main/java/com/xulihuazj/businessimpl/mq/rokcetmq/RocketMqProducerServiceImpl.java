/*
 * RocketMqProducerServiceImpl.java 1.0.0 2018/01/16  14:06 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/16  14:06 created by xulihua
 */
package com.xulihuazj.businessimpl.mq.rokcetmq;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.LocalTransactionExecuter;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.xulihuazj.business.mq.rocketmq.RocketMqProducerService;
import com.xulihuazj.log.LogHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;

@Service
public class RocketMqProducerServiceImpl implements RocketMqProducerService {

    private Logger logger = LogManager.getLogger(RocketMqProducerServiceImpl.class);

    @Resource
    private DefaultMQProducer defaultMQProducer;

    @Override
    public void producerSendMessage() {
        try {
            Message message = new Message("TEST",// topic
                    "TEST",// tag
                    UUID.randomUUID().toString(),//key用于标识业务的唯一性
                    ("Hello RocketMQ !!!!!!!!!!").getBytes()// body 二进制字节数组
            );
            SendResult result = defaultMQProducer.send(message);
            LogHelper.info(logger, "结果：{0}", result);
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            LogHelper.exception(logger, e, "RocketMq创建消息失败", e);
            e.printStackTrace();
        }
    }

    @Override
    public void setTransanctionMessage(Message message, LocalTransactionExecuter localTransactionExecuter, Map<String, Object> map) {

    }
}
