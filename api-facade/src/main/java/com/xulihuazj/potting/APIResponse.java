/*
 * APIResponse.java 1.0.0 2017年6月27日 下午5:14:38
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017年6月27日 下午5:14:38 created by xulihua
 */
package com.xulihuazj.potting;

import com.xulihuazj.ResConstant;
import com.xulihuazj.ToString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xulihua
 */
@ApiModel
@Data
public class APIResponse<T> extends ToString {

    private static final long serialVersionUID = 1L;

    /**
     * 业务返回状态码
     */
    @ApiModelProperty(value = "业务返回状态码")
    private String statusCode;

    /**
     * 状态码描述
     */
    @ApiModelProperty(value = "状态描述")
    private String message;

    @ApiModelProperty(value = "业务响应")
    private T bizResponse;

    public static <T> APIResponse<T> instance(T res) {
        APIResponse<T> response = new APIResponse<>();
        response.setBizResponse(res);
        response.setStatusCode(ResConstant.STATUSCODE.toString());
        response.setMessage(ResConstant.MESSAGE);
        return response;
    }

    public <T> APIResponse() {
        this.statusCode = ResConstant.STATUSCODE.toString();
        this.setMessage(ResConstant.MESSAGE);
    }

}
