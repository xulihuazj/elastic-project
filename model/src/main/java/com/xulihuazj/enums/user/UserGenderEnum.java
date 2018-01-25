/*
 * UserGenderEnum.java 1.0.0 2018/01/24  15:38 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/24  15:38 created by xulihua
 */
package com.xulihuazj.enums.user;

/**
 * 用户性别枚举
 *
 * @author xulihua
 * @Description
 * @Date 2017年7月7日下午1:06:04
 */
public enum UserGenderEnum {

    MALE("MALE", "男性"),

    FEMALE("FEMALE", "女性"),;

    String code;

    String desc;

    UserGenderEnum(String code, String desc) {
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
