/*
 * WeekEnum.java 1.0.0 2018/01/25  16:53 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  16:53 created by xulihua
 */
package com.xulihuazj.enums.common;

import com.xulihuazj.enums.BaseEnum;

public enum WeekEnum implements BaseEnum {

    MONDAY("MONDAY", "星期一"),

    TUESDAY("TUESDAY", "星期二"),

    WEDNESDAY("WEDNESDAY", "星期三"),

    THURSDAY("THURSDAY", "星期四"),

    FRIDAY("FRIDAY", "星期五"),

    SATURDAY("SATURDAY", "星期六"),

    SUNDAY("SUNDAY", "星期日"),

    ;

    private String code;
    private String desc;

    WeekEnum(String code, String desc) {
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