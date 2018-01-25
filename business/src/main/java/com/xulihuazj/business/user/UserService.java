/*
 * UserService.java 1.0.0 2018/01/23  12:31 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/23  12:31 created by xulihua
 */
package com.xulihuazj.business.user;

import com.xulihuazj.request.UserLoginRequest;
import com.xulihuazj.response.UserLoginResponse;

public interface UserService {

    /**
     * 用户登录接口
     *
     * @param userLoginRequest request
     * @return response
     */
    UserLoginResponse userLogin(UserLoginRequest userLoginRequest);
}
