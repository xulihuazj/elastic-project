package com.xulihuazj.business.elasticsearch;

import org.elasticsearch.action.index.IndexRequest;

import java.io.IOException;

/**
 * @Description:
 * @author: xulihua
 * @date: 2018/1/7 20:43
 */
public interface ElasticsearchService {


    void indexCarInfo(String id) throws IOException;

    /**
     * 更新汽车的信息
     */
    void udpateCarInfo(String id, IndexRequest indexRequest) throws IOException;


}
