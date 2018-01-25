/*
 * APIRequest.java 1.0.0 2017年6月27日 下午5:10:16
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017年6月27日 下午5:10:16 created by xulihua
 */
package com.xulihuazj.potting;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xulihuazj.exception.ParameterException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * api请求公共基类
 *
 * @author yinqiang
 */
@ApiModel
public class APIRequest<T> extends APIRequestHeader {

    private static final long serialVersionUID = -3014710292555344040L;

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @ApiModelProperty(value = "业务请求(Get请求忽略)")
    @Valid
    private T bizRequest;

    public static <T> APIRequest<T> instance(APIRequestHeader originRequest, T bizRequest) {
        APIRequest<T> request = new APIRequest<T>();
        request.setLanguage(originRequest.getLanguage());
        request.setToken(originRequest.getToken());
        request.setUserId(originRequest.getUserId());
        request.setSource(originRequest.getSource());
        request.setDeviceId(originRequest.getDeviceId());
        request.setDtMonitor(originRequest.getDtMonitor());
        request.setVersion(originRequest.getVersion());
        request.setBizRequest(bizRequest);
        return request;
    }

    /**
     * 根据类型获取bizRequest
     */
    public T getBizRequest(Class bizRequestClass) {
        if (bizRequest != null && bizRequest.getClass().equals(bizRequestClass)) {
            return bizRequest;
        }
        return null;
    }

    public T getBizRequest() {
        return bizRequest;
    }

    public void setBizRequest(T bizRequest) {
        this.bizRequest = bizRequest;
    }

    public T getBizRequest(Class type, Class<?>... groups) {
        this.validate(bizRequest, groups);
        return getBizRequest(type);
    }

    private void validate(Object obj, Class<?>... groups) {
        String errorMsg = null;
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj, groups);
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            Map<String, String> errorMap = new HashMap<>();
            for (ConstraintViolation<Object> cv : constraintViolations) {
                errorMap.put(cv.getPropertyPath().toString(), cv.getMessage());
            }
            try {
                errorMsg = new ObjectMapper().writeValueAsString(errorMap);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("系统内部业务处理异常");
            }
        }
        if (StringUtils.isNotBlank(errorMsg)) {
            throw new ParameterException(10010000, errorMsg);
        }
    }


}

