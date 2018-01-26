/*
 * UniqueGenerationUtil.java 1.0.0 2018/01/25  20:34 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  20:34 created by xulihua
 */
package com.xulihuazj.unique;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * UniqueId 生成
 */
public class UniqueGenerationUtil {

    /**
     * 生成UniqueId
     *
     * @param primaryKey   主键
     * @param userId       用户ID
     * @param createTime   主键创建时间
     * @param businessCode 业务码
     * @param randomNo     随机码位数
     * @return UniqueId
     */
    public static OrderNumber orderNoGenerate(Long primaryKey, Long userId, Date createTime, UniqueBusinessCode businessCode, Integer randomNo) {
        if (null == primaryKey || null == createTime || null == businessCode || null == randomNo) {
            return null;
        } else {
            String formatKey = String.format("%06d", primaryKey);
            long euid = userId % 2;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            //order_id 生成规则：时间戳 +2位业务吗（01）+6位主键（000001）+1位（用户ID模2）+2位随机码
            String orderNo = businessCode.getCode() + formatKey + String.valueOf(euid) + String.valueOf(getRandom(randomNo));
            String orderId = dateFormat.format(createTime) + orderNo;
            return new OrderNumber(orderId, orderNo);
        }
    }

    private static String getRandom(int n) {
        Random random = new Random();
        String[] randomType = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= n; i++) {
            sb.append(randomType[random.nextInt(10)]);
        }
        return sb.toString();
    }


}