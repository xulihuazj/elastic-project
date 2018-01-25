/*
 * TaskTypeEnum.java 1.0.0 2018/01/25  17:05 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  17:05 created by xulihua
 */
package com.xulihuazj.enums.task;

import com.xulihuazj.enums.BaseEnum;

public enum TaskTypeEnum implements BaseEnum {

    IMAGE("IMAGE", "图片异步任务"),

    REFUND("REFUND","退款失败后自动在发起退款"),

    ;

    TaskTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    String code;

    String desc;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}