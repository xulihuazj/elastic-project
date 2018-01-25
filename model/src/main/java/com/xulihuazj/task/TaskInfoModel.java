/*
 * TaskInfoModel.java 1.0.0 2018/01/25  17:03 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  17:03 created by xulihua
 */
package com.xulihuazj.task;

import lombok.Data;

import java.util.Date;

@Data
public class TaskInfoModel<T> {

    private Long id;
    /*
    * 用户存放业务属性  可将对象序列化为string 存进数据库
     * 必须要实现业务基类 TaskBizContext  并且在里面写自己的业务数据
    */
    private String taskBizContext;

    private String taskType;

    private String taskStatus;

    private Date createTime;

    private Date lastModifyTime;

    private String extInfo;

    private String correlationId;

    private Integer taskPriority;

    private String taskName;

    private String taskKey;
}
