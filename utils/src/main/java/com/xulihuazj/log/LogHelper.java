/*
 * LogHelper.java 1.0.0 2018/01/08  18:12
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/08  18:12 created by xulihua
 */
package com.xulihuazj.log;

import java.text.MessageFormat;

import org.apache.logging.log4j.Logger;

public class LogHelper {

    public static String getMessage(String template, Object... params) {
        try {
            return MessageFormat.format(template, params);
        } catch (Exception ex) {
            return template + "【日志打印异常】" + ex.getMessage();
        }
    }

    /**
     * 输出debug日志
     *
     * @param logger   logger
     * @param template 模板
     * @param params   可变参数
     * @author 徐礼华
     * @date 2018/01/08 18:21
     */
    public static void debug(final Logger logger, String template, Object... params) {
        if (logger.isDebugEnabled()) {
            logger.debug(getMessage(template, params));
        }
    }

    /**
     * 输出warn日志
     *
     * @param logger   logger
     * @param template 模板
     * @param params   可变参数
     * @author 徐礼华
     * @date 2018/01/08 18:26
     */
    public static void warn(final Logger logger, String template, Object... params) {
        if (logger.isWarnEnabled()) {
            logger.warn(getMessage(template, params));
        }
    }

    /**
     * 输出info日志
     *
     * @param logger   logger
     * @param template 模板
     * @param params   可变参数
     * @author 徐礼华
     * @date 2018/01/08 18:27
     */
    public static void info(final Logger logger, String template, Object... params) {
        if (logger.isInfoEnabled()) {
            logger.info(getMessage(template, params));
        }
    }

    /**
     * 输出error日志
     *
     * @param logger   logger
     * @param template 模板
     * @param params   可变参数
     * @author 徐礼华
     * @date 2018/01/08 18:27
     */
    public static void error(final Logger logger, String template, Object... params) {
        if (logger.isInfoEnabled()) {
            logger.error(getMessage(template, params));
        }
    }

    /**
     * 输出exception日志，并打印异常
     *
     * @param logger   logger
     * @param template 模板
     * @param params   可变参数
     * @author 徐礼华
     * @date 2018/01/08 18:28
     */
    public static void exception(final Logger logger, Exception ex, String template, Object... params) {
        logger.error(getMessage(template, params), ex);
    }

}
