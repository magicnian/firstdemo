package cn.magicnian.demo;

import cn.magicnian.util.HttpClientFactory;
import cn.magicnian.util.HttpClientPoolingCrawler;
import cn.magicnian.util.HttpCustomResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpClientTest {
    public static void main(String[] args) throws Exception {
        String url = "https://www.baidu.com";

        HttpCustomResponse response = HttpClientPoolingCrawler.custom().url(url).get();

        log.info("statusCode:{}", response.getStatusCode());

        log.info("responseBody:{}", response.getResponseBody());
    }
}
