/*
 * OrderNumber.java 1.0.0 2018/01/25  20:34 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  20:34 created by xulihua
 */
package com.xulihuazj.unique;

import lombok.Data;

@Data
public class OrderNumber {

    //25位订单
    private String orderId;

    //较短订单号
    private String orderNo;

    public OrderNumber() {
    }

    public OrderNumber(String orderId, String orderNo) {
        this.orderId = orderId;
        this.orderNo = orderNo;
    }
}
