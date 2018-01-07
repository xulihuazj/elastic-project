/*
 * BizErrorModel.java 1.0.0 2017/12/16  11:38 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017/12/16  11:38 created by xulihua
 */
package com.xulihuazj.error;

/**
 * @Description:
 * @author: xulihua
 * @date: 2017/12/16 11:38
 */
public enum BizErrorCode implements ErrorCode {

    /**
     * 3位系统码+2位业务码+3位异常码
     *    1.api默认系统码100
     *    2.登录相关系统码200
     *      2.1邮箱业务码10
     *      2.2手机号业务码11
     *      2.3账号业务12
     *      2.4密码13
     *      2.5其他14
     *    3.部门相关系统码300
     *      3.1
     *    4.岗位相关系统码400
     *    5.通用语言系统码500
     *    6 权限相关系统码600
     */

    REQUEST_PARAM_EMPTY_ERROR("10010000", "请求参数错误或缺失"),
    ;

    private String code;

    private String message;

    BizErrorCode(String code, String message) {
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
