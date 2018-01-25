/*
 * TaskSubBiz.java 1.0.0 2018/01/25  17:02 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  17:02 created by xulihua
 */
package com.xulihuazj.business.task;

import com.xulihuazj.task.TaskInfoModel;

public interface TaskSubBiz {

    /**
     * 任务执行
     *
     * @param taskInfoModel taskInfoModel
     */
    void execute(TaskInfoModel taskInfoModel);
}