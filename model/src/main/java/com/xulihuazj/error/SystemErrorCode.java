/*
 * SystemErrorCode.java 1.0.0 2017/12/16  11:41 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017/12/16  11:41 created by xulihua
 */
package com.xulihuazj.error;

/**
 * @Description:
 * @author: xulihua
 * @date: 2017/12/16 11:41
 */
public enum SystemErrorCode implements ErrorCode {

    /**
     * 1.api默认系统码100
     */

    TOKEN_EXPIRE("50010002", "token过期"),

    TOKEN_NULL("50010009", "请求头中未带入token为空"),

    TOKEN_ERROR("50010004", "token异常请重新登录"),

    TOKEN_PASSWORD_UPDATE("50010005", "token异常密码被修改请重新登录"),

    TOKEN_LOSE_EFFICACY("50010006", "该账号已在其他设备登录请重新登录"),

    NOT_SUPPORT("50000001", "不支持的操作"),

    SYSTEM_SERVICE_HANDLE_ERROR("50010008", "系统内部业务处理异常"),

    MAMAHOEMERROR("50010010", "登录异常获取信息"),;

    private String code;

    private String message;

    SystemErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
