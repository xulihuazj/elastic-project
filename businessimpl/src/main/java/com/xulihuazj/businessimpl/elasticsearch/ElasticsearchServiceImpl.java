/*
 * ElasticsearchServiceImpl.java 1.0.0 2018/1/7  20:43
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/1/7  20:43 created by xulihua
 */
package com.xulihuazj.businessimpl.elasticsearch;

import com.xulihuazj.business.elasticsearch.ElasticsearchService;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.script.mustache.SearchTemplateRequestBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author xulihua
 * @description
 * @date 2018/1/7 20:43
 */
public class ElasticsearchServiceImpl implements ElasticsearchService {


    private TransportClient needTransportClient() {
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch")
                .put("client.transport.sniff", true)
                .build();

        TransportClient client = null;
        try {
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public void indexCarInfo(String id) throws IOException {
        TransportClient client = needTransportClient();
        IndexRequest indexRequest = new IndexRequest("car_shop", "cars", id)
                .source(XContentFactory.jsonBuilder().startObject()
                        .field("brand", "宝马")
                        .field("name", "宝马320")
                        .field("price", 320000)
                        .field("produce_update", "2017-01-01")
                        .endObject());
    }

    @Override
    public void udpateCarInfo(String id, IndexRequest indexRequest) throws IOException {
        TransportClient client = needTransportClient();

        try {
            UpdateRequest updateRequest = new UpdateRequest("car_shop", "cars", id)
                    .doc(XContentFactory.jsonBuilder()
                            .startObject()
                            .field("price", 310000)
                            .endObject())
                    .upsert(indexRequest);

            UpdateResponse updateResponse = client.update(updateRequest).get();
            System.out.println(updateResponse.getVersion());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    //基于mget 实现多辆汽车配置
    public void multiGetCarInfo() {
        TransportClient client = needTransportClient();
        MultiGetResponse bizResponse = client.prepareMultiGet()
                .add("car_shop", "cars", "1")
                .add("car_shop", "cars", "2")
                .get();
        for (MultiGetItemResponse itemResponse : bizResponse) {
            GetResponse response = itemResponse.getResponse();
            if (response.isExists()) {
                String jsonResponse = response.getSourceAsString();
                System.out.println(jsonResponse);
            }
        }
        client.close();
    }

    //基于bulk 实现多4s店销售数据批量操作
    public void bulkCarInfo() throws IOException {
        TransportClient client = needTransportClient();
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        //加入一套
        bulkRequest.add(client.prepareIndex("car_shop", "sales", "3")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject().field("brand", "奔驰")
                        .field("name", "奔驰C200")
                        .field("price", "350000")
                        .field("produce_date", "2017-01-01")
                        .field("sale_price", 340000)
                        .field("sale_date", "2017-02-03")
                        .endObject())
        );

        //更新一条
        bulkRequest.add(client.prepareUpdate("car_shop", "sales", "1")
                .setDoc(XContentFactory.jsonBuilder().startObject()
                        .field("sale_price", "290000")
                        .endObject()));

        //删除一条
        bulkRequest.add(client.prepareDelete("car_shop", "sales", "2"));

        BulkResponse bulkResponse = bulkRequest.get();
        for (BulkItemResponse itemResponse : bulkResponse) {
            System.out.println("version:" + itemResponse.getVersion());
        }
        client.close();
    }

    //对大量数据批量的获取和处理
    private void scrollApiTest() {
        TransportClient client = needTransportClient();
        SearchResponse scrollResponse = client.prepareSearch("car_shop")
                .setTypes("sales")
                .addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.ASC)
                .setScroll(new TimeValue(60000))
                .setQuery(new TermQueryBuilder("brand.keyword", "宝马"))
                .setSize(1)
                .get();
        int batchCount = 0;
        for (SearchHit searchHit : scrollResponse.getHits()) {
            System.out.println("当前批次：" + ++batchCount);
            System.out.println(searchHit.getSourceAsString());
            //每次查询一条数据，比如1000行，然后写入本地的一个excel文件中
        }

        do {
            for (SearchHit searchHit : scrollResponse.getHits().getHits()) {
                System.out.println(searchHit.getSource().toString());
            }
            scrollResponse = client.prepareSearchScroll(scrollResponse.getScrollId())
                    .setScroll(new TimeValue(60000))
                    .execute()
                    .actionGet();
        } while (scrollResponse.getHits().getHits().length != 0);

        client.close();
    }

    //搜索模板的功能，Java api怎么去调用一个模板
    private void templateApiTest() {
        TransportClient client = needTransportClient();
        //1:在es配置下 放入模板脚本
        Map<String, Object> params = new HashMap<>();
        params.put("from", 0);
        params.put("size", 1);
        params.put("band", "宝马");//写入参数 进行分页查询

        SearchResponse searchResponse = new SearchTemplateRequestBuilder(client)
                .setScript("page_query_by_brand")
                .setScriptType(ScriptType.FILE)
                .setScriptParams(params)
                .setRequest(new SearchRequest("car_shop").types("sales"))
                .get()
                .getResponse();
        for (SearchHit searchHit : searchResponse.getHits().getHits()) {
            System.out.println(searchHit.getSourceAsString());
        }
    }

    private void queryApiTest() {
        TransportClient client = needTransportClient();

        //普通的query
        SearchResponse response = client.prepareSearch("car_shop")
                .setTypes("cars")
                .setQuery(QueryBuilders.matchQuery("brand", "宝马"))
                .get();

        //multi 搜索
        SearchResponse response1 = client.prepareSearch("car_shop")
                .setTypes("cars")
                .setQuery(QueryBuilders.multiMatchQuery("宝马", "brand", "name"))
                .get();

        //
        SearchResponse response2 = client.prepareSearch("car_shop")
                .setTypes("cars")
                .setQuery(QueryBuilders.commonTermsQuery("name", "宝马320"))
                .get();

        //前缀搜索
        SearchResponse response3 = client.prepareSearch("car_shop")
                .setTypes("cars")
                .setQuery(QueryBuilders.prefixQuery("name", "宝"))
                .get();

    }

    //多种条件组合搜索
    private void multiConditionApiTest() {
        TransportClient client = needTransportClient();
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("brand", "宝马"))
                .mustNot(QueryBuilders.termQuery("name.raw", "宝马318"))
                .should(QueryBuilders.rangeQuery("produce_date").gte("217-01-01").lte("2017-01-31"))
                .filter(QueryBuilders.rangeQuery("price").gte(280000).lte(350000));

        SearchResponse response = client.prepareSearch("car_shop")
                .setTypes("cars")
                .setQuery(queryBuilder)
                .get();

        for (SearchHit searchHit : response.getHits().getHits()) {
            System.out.println(searchHit.getSourceAsString());
        }
    }

    //基于地理位置
    private void locationApiTest() {

        TransportClient client = needTransportClient();
        //矩形搜索
        SearchResponse searchResponse = client.prepareSearch("car_shop")
                .setTypes("shops")
                .setQuery(QueryBuilders.geoBoundingBoxQuery("pin.location")
                        .setCorners(40.73, -74.1, 40.01, -70.12))
                .get();
        for (SearchHit searchHit : searchResponse.getHits().getHits()) {
            System.out.println(searchHit.getSourceAsString());
        }
        System.out.println("************************************************************");

        //第二个需求：指定一个区域，由三个坐标点组成，比如上海大厦，东方明珠台，上海火车站
        List<GeoPoint> points = new ArrayList<>();
        points.add(new GeoPoint(10.73, -74.1));
        points.add(new GeoPoint(10.01, -71.12));
        points.add(new GeoPoint(50.56, -90.58));
        searchResponse = client.prepareSearch("car_shop")
                .setTypes("shops")
                .setQuery(QueryBuilders.geoPolygonQuery("pin.location", points))
                .get();

        //第三个需求：以点位中心 搜索周边一定距离(20km内)
        searchResponse = client.prepareSearch("car_shop")
                .setTypes("shops")
                .setQuery(QueryBuilders.geoDistanceQuery("pin.location")
                        .point(10, -70)
                        .distance(20, DistanceUnit.KILOMETERS))
                .get();

    }

    //disMaxQuery
    private void disMaxQueryApiTest() {
        //disMaxQuery
        TransportClient client = needTransportClient();
        QueryBuilder disMaxQueryBuilder = QueryBuilders.disMaxQuery()
                .add(QueryBuilders.matchQuery("title", "java solution"))
                .add(QueryBuilders.matchQuery("content", "java solution"));
        SearchResponse searchResponse = client.prepareSearch("my_index").setTypes("my_type")
                .setQuery(disMaxQueryBuilder)
                .get();
    }


}


