package com.xulihuazj.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import com.xulihuazj.error.BizErrorCode;
import com.xulihuazj.log.LogHelper;
import com.xulihuazj.potting.APIResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.text.MessageFormat;
import java.util.List;

/**
 * GlobalExceptionHandler
 *
 * @author liwei
 * @date 2017/3/29
 * @description
 * 统一的异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<APIResponse> errorHandler(HttpServletRequest req, Exception e) throws Exception {
        APIResponse model = new APIResponse();
        HttpStatus httpStatus;
        if (e instanceof BusinessException) {
            LogHelper.error(logger, "GlobalExceptionHandler异常,errorcode={0}, message={1}", ((BusinessException) e).getErrorCode(), e.getMessage());
            model.setStatusCode(((BusinessException) e).getErrorCode());
            model.setMessage(e.getMessage());
            if (((BusinessException) e).getHttpStatus() != null) {

                try {
                    httpStatus = HttpStatus.valueOf(((BusinessException) e).getHttpStatus());
                } catch (Exception ex) {
                    httpStatus = HttpStatus.EXPECTATION_FAILED;
                }
            } else {
                //如果业务异常并且没有定义http状态吗 这里默认是200 尽量不要使用500 否则前端拿到这个状态码会认为是不允许跨域
                httpStatus = HttpStatus.OK;
            }
        }else if (e instanceof BindException) {
            List<FieldError> fieldErrors = ((BindException)e).getBindingResult().getFieldErrors();
            for (FieldError error : fieldErrors) {
                model.setStatusCode(BizErrorCode.REQUEST_PARAM_EMPTY_ERROR.getCode());
                model.setMessage(error.getDefaultMessage());
                logger.info(error.getField() + ":" + error.getDefaultMessage());
            }
            return new ResponseEntity<>(model, HttpStatus.OK);
        }else if (e instanceof MethodArgumentNotValidException) {
            List<FieldError> fieldErrors = ((MethodArgumentNotValidException)e).getBindingResult().getFieldErrors();
            for (FieldError error : fieldErrors) {
                model.setStatusCode(BizErrorCode.REQUEST_PARAM_EMPTY_ERROR.getCode());
                model.setMessage(error.getDefaultMessage());
                logger.info(error.getField() + ":" + error.getDefaultMessage());
            }
            return new ResponseEntity<>(model, HttpStatus.OK);
        }else if (e instanceof HttpMessageConversionException || e instanceof JsonMappingException ) {
            InvalidFormatException ex = (InvalidFormatException)e.getCause();
            String[] exNames = StringUtils.split(ex.getTargetType().getName(), ".");
            String exName = exNames[exNames.length-1].toLowerCase();
            model.setStatusCode(BizErrorCode.REQUEST_PARAM_EMPTY_ERROR.getCode());
            model.setMessage(MessageFormat.format("枚举类型传参错误:参数名：{0}，参数值：{1}",exName,ex.getValue()));
            LogHelper.error(logger, "JSONException异常，errorcode={0}", BizErrorCode.REQUEST_PARAM_EMPTY_ERROR.getCode());
            return new ResponseEntity<>(model,HttpStatus.OK);
        }else if (e instanceof HttpRequestMethodNotSupportedException) {
            LogHelper.exception(e, logger, "请求方式不支持，异常信息为：{0}", e);
            model.setMessage("此API的请求方式不正确，请转换正确的请求方式！");
            return new ResponseEntity<>(model,HttpStatus.OK);
        }else if(e instanceof ParameterException){
            LogHelper.error( logger, "发生运行时异常，请求入参错误：{0}",e.getMessage());
            model.setStatusCode(BizErrorCode.REQUEST_PARAM_EMPTY_ERROR.getCode());
            model.setMessage(e.getMessage());
            return new ResponseEntity<>(model,HttpStatus.OK);
        } else {
            LogHelper.exception(e, logger, "发生运行时异常");
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            model.setMessage("服务器处理错误");
        }
        return new ResponseEntity<>(model, httpStatus);
    }

}
