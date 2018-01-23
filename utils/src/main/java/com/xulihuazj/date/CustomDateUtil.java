/*
 * CustomDateUtil.java 1.0.0 2018/01/23  14:38 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/23  14:38 created by xulihua
 */
package com.xulihuazj.date;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CustomDateUtil {

    /**
     * 获取未来的时间戳
     *
     * @param expireTime
     * @param timeUnit
     * @return
     */
    public static Date getExpireTime(int expireTime, TimeUnit timeUnit) {
        Calendar calendar = Calendar.getInstance();
        switch (timeUnit) {
            case SECONDS:
                calendar.add(Calendar.SECOND, expireTime);
                break;
            case MINUTES:
                calendar.add(Calendar.MINUTE, expireTime);
                break;
            case HOURS:
                calendar.add(Calendar.HOUR, expireTime);
                break;
            case DAYS:
                calendar.add(Calendar.DAY_OF_WEEK, expireTime);
                break;
            default:
                calendar.add(Calendar.SECOND, expireTime);
        }
        return calendar.getTime();
    }

    /**
     * 获取秒数
     *
     * @param expireTime 量
     * @param timeUnit   单位
     * @return 秒数
     */
    public static int getSecondTime(int expireTime, TimeUnit timeUnit) {
        switch (timeUnit) {
            case SECONDS:
                return expireTime;
            case MINUTES:
                return expireTime * 60;
            case HOURS:
                return expireTime * 60 * 60;
            case DAYS:
                return expireTime * 60 * 60 * 24;
            default:
                return expireTime;
        }
    }
}
