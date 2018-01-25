/*
 * ShareRequest.java 1.0.0 2018/01/25  17:20 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  17:20 created by xulihua
 */
package com.xulihuazj.request;

import com.xulihuazj.ToString;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ShareRequest extends ToString {

    private static final long serialVersionUID = 4804866915615063422L;

    @ApiModelProperty(value = "分享数据请求参数请传json字符串类型 url")
    private String requestContent;

}
