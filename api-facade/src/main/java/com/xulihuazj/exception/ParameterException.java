package com.xulihuazj.exception;/*
 * ParameterException.java 1.0.0 2017/10/17  11:45
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017/10/17  11:45 created by xulihua
 */

import com.xulihuazj.ResConstant;

public class ParameterException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errorCode = ResConstant.STATUSERRORCODE.toString();

    private Integer httpStatus;

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        if (errorCode != null) {
            this.errorCode = errorCode.toString();
        }
    }

    public ParameterException(Integer errorCode, String message) {
        super(message);
        if (errorCode != null) {
            this.errorCode = errorCode.toString();
        }
    }

    public ParameterException(Integer httpStatus, Integer errorCode, String message) {
        super(message);
        this.httpStatus = httpStatus;
        if (errorCode != null) {
            this.errorCode = errorCode.toString();
        }
    }
}
