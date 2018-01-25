/*
 * ImageTaskService.java 1.0.0 2018/01/25  17:06 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  17:06 created by xulihua
 */
package com.xulihuazj.business.task;

import com.xulihuazj.business.task.TaskSubBiz;
import com.xulihuazj.task.TaskInfoModel;

public interface ImageTaskService extends TaskSubBiz {

    @Override
    void execute(TaskInfoModel taskInfoModel);
}
