/*
 * ShareResponse.java 1.0.0 2018/01/25  17:19 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  17:19 created by xulihua
 */
package com.xulihuazj.response;

import com.xulihuazj.ToString;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ShareResponse extends ToString {

    private static final long serialVersionUID = 1514376327554663778L;

    @ApiModelProperty(value = "分享加密字符串结果返回 result")
    private String result;

    public ShareResponse() {
    }

    public ShareResponse(String result) {
        this.result = result;
    }

}
