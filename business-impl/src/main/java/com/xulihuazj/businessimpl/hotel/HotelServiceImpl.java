/*
 * HotelServiceImpl.java 1.0.0 2018/01/08  16:08 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/08  16:08 created by xulihua
 */
package com.xulihuazj.businessimpl.hotel;

import com.xulihuazj.business.hotel.HotelService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HotelServiceImpl implements HotelService {

    /**
     * 缓存的key
     */
    public static final String THING_ALL_KEY = "\"thing_all\"";
    /**
     * value属性表示使用哪个缓存策略，缓存策略在ehcache.xml
     */
    public static final String DEMO_CACHE_NAME = "demo";


    private Logger logger = LogManager.getLogger(HotelServiceImpl.class);

    //@CacheEvict(value = DEMO_CACHE_NAME, key = THING_ALL_KEY)
    // @Cacheable(value = "demo",key = "#aa")
    @Override
    public int selectListTest(String aa) {
        long startTime = System.currentTimeMillis();
        //List<HotelInfoDO> hotelInfoDOS = hotelInfoDOMapper.searchListByHotelName(null);
        List<HotelImageDO> hotelImageDOList = hotelImageDOMapper.selectByHotelIdsAndStatus(null, null, aa);
        System.out.println(hotelImageDOList.size());
        LogHelper.info(logger, "最总时间：{0}", System.currentTimeMillis() - startTime);
        return hotelImageDOList.size();
    }

}
