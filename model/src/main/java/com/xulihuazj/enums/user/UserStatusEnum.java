/*
 * UserStatusEnum.java 1.0.0 2018/01/24  15:39 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/24  15:39 created by xulihua
 */
package com.xulihuazj.enums.user;

/**
 * @author xulihua
 * @description
 * @date 2017年7月13日上午10:40:46
 */
public enum UserStatusEnum {

    EFFECTIVE("EFFECTIVE", "有效"),

    LOCK("LOCK", "锁定"),

    INVALID("INVALID", "无效"),;

    String code;

    String desc;

    UserStatusEnum(String code, String desc) {
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
