/*
 * UserInfoModel.java 1.0.0 2018/01/24  15:36 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/24  15:36 created by xulihua
 */
package com.xulihuazj.model.user;

import com.xulihuazj.enums.user.UserGenderEnum;
import com.xulihuazj.enums.user.UserStatusEnum;
import lombok.Data;

import java.util.Map;

/**
 * UserInfo Model
 *
 * @author xulihua
 * @description
 * @date 2017年7月14日下午2:20:12
 */
@Data
public class UserInfoModel {

    private Long userId;

    private String firstName;

    private String lastName;

    /*
     * 性别
     */
    private UserGenderEnum sex;

    private String nickName;

    private String email;

    private String phone;

    private String birthday;

    /*
     * 用户状态
     */
    private UserStatusEnum status;

    private Integer userRoleFlag;

    /*
    * 用户头像
    */
    private String userImageHref;

    /*
     * ext信息
     */
    private Map<String, String> extInfo;

    public UserInfoModel() {

    }

    public UserInfoModel(String birthday, String userImageHref, String firstName, String lastName) {
        this.birthday = birthday;
        this.userImageHref = userImageHref;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
