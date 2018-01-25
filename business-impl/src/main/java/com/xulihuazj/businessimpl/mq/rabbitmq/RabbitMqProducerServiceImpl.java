/*
 * RabbitMqProducerServiceImpl.java 1.0.0 2018/01/23  12:25 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/23  12:25 created by xulihua
 */
package com.xulihuazj.businessimpl.mq.rabbitmq;

import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xulihuazj.business.mq.rabbitmq.RabbitMqProducerService;
import com.xulihuazj.model.user.UserInfoModel;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RabbitMqProducerServiceImpl implements RabbitMqProducerService {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void sendMessage(String message) {
        System.out.println("消息 : " + message);
        this.rabbitTemplate.convertAndSend("helloQueue", message);

    }

    @Override
    public void sendMessage(UserInfoModel userInfoModel) {
        System.out.println(userInfoModel.getFirstName());
    }

    public Boolean updateUser() throws JsonProcessingException {
        Map<String, Object> message = new HashMap<>();
        message.put("userId", 1);
        String messageJson = JSON.toJSONString(message);
        String messageJson2 = objectMapper.writeValueAsString(message);
        this.rabbitTemplate.convertAndSend("item.update", messageJson);
        this.rabbitTemplate.convertAndSend("item2.update", messageJson2);
        return Boolean.TRUE;
    }
}
