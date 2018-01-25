/*
 * UserLoginRequest.java 1.0.0 2018/01/25  12:02 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  12:02 created by xulihua
 */
package com.xulihuazj.request;

import com.xulihuazj.ToString;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 登录Request
 *
 * @author xulihua
 * @description
 * @date 2017年7月10日下午2:40:01
 */
@Data
public class UserLoginRequest extends ToString {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账户（必传）", required = true)
    @NotEmpty(message = "账号为空")
    private String account;

    @ApiModelProperty(value = "密码（必传）", required = true)
    @NotEmpty(message = "密码为空")
    @Size(min = 6, max = 18, message = "密码长度为6~18位")
    @Pattern(regexp = "^[0-9a-zA-Z_]{6,18}$", message = "密码格式不正确：6-18位数字和字母，下划线。不支持：特殊字符，空格")
    private String password;

}
