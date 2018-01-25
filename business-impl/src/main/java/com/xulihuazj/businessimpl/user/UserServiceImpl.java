/*
 * UserServiceImpl.java 1.0.0 2018/01/25  17:29 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  17:29 created by xulihua
 */
package com.xulihuazj.businessimpl.user;

import com.xulihuazj.business.user.UserService;
import com.xulihuazj.log.LogHelper;
import com.xulihuazj.request.UserLoginRequest;
import com.xulihuazj.response.UserLoginResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {

    private final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public UserLoginResponse userLogin(UserLoginRequest userLoginRequest) {
        LogHelper.info(logger, "【UserService】【userLogin】用户登录，请求参数={0}", userLoginRequest);

        return null;
    }
}
