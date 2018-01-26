/*
 * UniqueBusinessCode.java 1.0.0 2018/01/25  20:35 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  20:35 created by xulihua
 */
package com.xulihuazj.unique;

public enum UniqueBusinessCode {

    INLAND_ORDER_SERVICE("01", "国内月租订单创建业务码"),

    INLAND_ORDER_REFUND("02", "国内月租订单退款业务码"),

    ;

    private String code;
    private String desc;

    UniqueBusinessCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
