/*
 * Language.java 1.0.0 2018/01/25  16:49 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  16:49 created by xulihua
 */
package com.xulihuazj.enums.common;

import com.xulihuazj.enums.BaseEnum;

/**
 * 语言
 */
public enum Language implements BaseEnum {

    ZH_CN("ZH_CN", "中文"),

    EN_US("EN_US", "英语"),

    ;

    private String code;

    private String desc;

    Language(String code, String desc) {
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
