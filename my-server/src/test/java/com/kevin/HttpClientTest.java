package com.kevin;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

// 联系发请求
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpClientTest {
    @Test
    public void testGetRequest() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 2. 创建 GET 请求对象
        String url = "http://localhost:8080/shop/status";
        HttpGet request = new HttpGet(url);
        // 3. 发送请求并获取响应
        CloseableHttpResponse response = httpClient.execute(request);
        // 4. 读取响应内容

        Integer statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode:" + statusCode);
        String result = EntityUtils.toString(response.getEntity());
        System.out.println("!!!" + result);
    }


    @Test
    public void testPostRequest() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 2. 创建 GET 请求对象
        String url = "http://localhost:8080/shop/0";
        HttpPut request = new HttpPut(url);
        // 3. 发送请求并获取响应
        CloseableHttpResponse response = httpClient.execute(request);
        // 4. 读取响应内容
        Integer statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode:" + statusCode);
        String result = EntityUtils.toString(response.getEntity());
        System.out.println("!!!" + result);
        httpClient.close();
        response.close();
    }
}
