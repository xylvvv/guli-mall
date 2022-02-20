package cn.xylvvv.search;

import cn.xylvvv.search.config.MallElasticSearchConfig;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class MallSearchApplicationTest {
    @Autowired
    RestHighLevelClient client;

    @Test
    void contextLoads() {
        System.out.println(client);
    }

    /**
     * 测试存储数据到 es
     * source 方法用于保存数据，数据的格式为键值对形式的类型
     *  - json 字符串
     *  - Map
     *  - XContentBuilder
     *  - KV 键值对
     *  - 实体类对象转json
     */
    @Test
    void indexData() throws IOException {
        IndexRequest indexRequest = new IndexRequest("users");
        indexRequest.id("1");
        // json 字符串
        indexRequest.source("{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}", XContentType.JSON);

        // KV 键值对
        // indexRequest.source("username", "zhangsan", "age", 12, "address", "sz");

        // 同步执行
        client.index(indexRequest, MallElasticSearchConfig.COMMON_OPTIONS);
    }
}
