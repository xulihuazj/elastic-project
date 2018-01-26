/*
 * ExternalUserControllerTest.java 1.0.0 2017/12/17  22:28 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017/12/17  22:28 created by xulihua
 */
package com.xulihuazj.api.externalapi;

import com.xulihuazj.business.user.UserService;
import com.xulihuazj.model.user.UserInfoModel;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.*;

import javax.annotation.Resource;
import javax.swing.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * 使用Mock工具进行测试
 *
 * @Description:
 * @author: xulihua
 * @date: 2017/12/17 22:28
 */

public class ExternalUserControllerTest {

    @Resource
    private UserService userServiceImpl;

    @Before
    public void setUp() throws Exception {
        //模拟PersonDao对象
       /* UserInfoModel mockUser = mock(UserInfoModel.class);
        when(mockUser.getPerson(1)).thenReturn(new Person(1, "Person1"));
        when(mockDao.update(isA(Person.class))).thenReturn(true);

        UserService = new userServiceImpl(mockDao);*/
    }

}
