/*
 * TransantionExecuterImpl.java 1.0.0 2018/1/21  14:52 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/1/21  14:52 created by xulihua
 */
package com.xulihuazj.businessimpl.rocketmq;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.producer.LocalTransactionExecuter;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.common.message.Message;
import com.xulihuazj.json.JSONHelper;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 执行本地事务，由客户端回调
 *
 * @Description:
 * @author: xulihua
 * @date: 2018/1/21 14:52
 */
public class TransantionExecuterImpl implements LocalTransactionExecuter {
    @Override
    public LocalTransactionState executeLocalTransactionBranch(Message message, Object object) {
        System.out.println("message=" + new String(message.getBody()));
        System.out.println("arg = " + object);
        String tag = message.getTags();
       /* if (tag.equals("Transaction3")) {
            System.out.println("这里处理业务逻辑，比如操作数据库，失败情况下进行ROLLBaCK");
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }*/
        try {
            JSONObject messageBody = JSONHelper.toObject(new String(message.getBody(), "utf-8"), JSONObject.class);
            Map<String, Object> mapArgs = (Map<String, Object>) object;
            System.out.println("message body=" + messageBody);
            System.out.println("message mapArgs=" + mapArgs);
            System.out.println("message tag=" + message.getTags());
            System.out.println(mapArgs.get("attr1"));
            System.out.println(mapArgs.get("attr2"));

            String userId = messageBody.getString("userid");
            double money = messageBody.getDouble("money");
            String pay_mode = messageBody.getString("pay_mode");

            //调用订单状态更改逻辑
            //持久化数据
            //成功通知MQ消息变更 该消息变为：<确认发送>
            return LocalTransactionState.COMMIT_MESSAGE;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
    }
}
