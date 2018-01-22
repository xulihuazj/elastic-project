/*
 * RocketMqProducerServiceImpl.java 1.0.0 2018/01/16  14:06 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/16  14:06 created by xulihua
 */
package com.xulihuazj.businessimpl.rocketmq;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.LocalTransactionExecuter;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.TransactionSendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.xulihuazj.business.rocketmq.RocketMqProducerService;
import com.xulihuazj.log.LogHelper;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;

public class RocketMqProducerServiceImpl implements RocketMqProducerService {

    private Logger logger = LogManager.getLogger(RocketMqProducerServiceImpl.class);

    @Resource
    private DefaultMQProducer defaultMQProducer;

    @Resource
    private LocalTransactionExecuter localTransactionExecuterImpl;

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

    public void test1() {
        System.out.println(this.defaultMQProducer);
        System.out.println(this.localTransactionExecuterImpl);
        try {
            Message message = new Message();
            message.setTopic("pay");
            message.setTags("tag");
            String uuid = UUID.randomUUID().toString();
            System.out.println("key:" + uuid);
            message.setKeys(uuid);
            JSONObject body = new JSONObject();
            body.put("userId", "z3");
            body.put("money", "10000");
            body.put("pay_mode", "OUT");
            body.put("balance_mode", "IN");
            message.setBody(JSON.toJSONString(body).getBytes());
            Map<String, Object> mapArgs = new HashedMap();
            mapArgs.put("attr1", "111");
            mapArgs.put("attr2", "222");
            this.defaultMQProducer.sendMessageInTransaction(message, this.localTransactionExecuterImpl, mapArgs);
        } catch (MQClientException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setTransanctionMessage(Message message, LocalTransactionExecuter localTransactionExecuter, Map<String, Object> map) {
        try {
            //调用原来回调
            TransactionSendResult result = defaultMQProducer.sendMessageInTransaction(message, localTransactionExecuter, map);
            //执行成功
            System.out.println("send返回内容：" + result.toString());
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
}
