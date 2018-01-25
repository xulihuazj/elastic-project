/*
 * MsgTemplateType.java 1.0.0 2018/01/25  16:51 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  16:51 created by xulihua
 */
package com.xulihuazj.enums.common;

import com.xulihuazj.enums.BaseEnum;

/**
 * 类型：SMS-短信，EMAIL-邮件
 *
 * @author xulihua
 * @Description
 * @Date 2017年7月13日上午9:35:18
 */
public enum MsgTemplateType implements BaseEnum {

    SMS("SMS", "短信"),

    EMAIL("EMAIL", "邮件"),;

    String code;

    String desc;

    MsgTemplateType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

}
