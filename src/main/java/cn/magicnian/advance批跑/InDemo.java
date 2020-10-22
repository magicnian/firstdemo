package cn.magicnian.advance批跑;

import cn.magicnian.util.HttpClientPoolingCrawler;
import cn.magicnian.util.HttpCustomResponse;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class InDemo {

    private static final Logger logger = LoggerFactory.getLogger(InDemo.class);

    private static HttpHost proxy = new HttpHost("127.0.0.1", 8888);

    private static final String url = "https://vn-api.advance.ai/vn/openapi/score/v1/device-risk-index";

    private static final String access_key = "123";

    public static void main(String[] args) {

        logger.info("===============start inDemo =====================");

        Map<String, String> headers = new HashMap<>();
        headers.put("X-ADVAI-KEY", access_key);
        headers.put("Content-Type","application/json");

        JSONObject jo = new JSONObject();
        jo.put("idNumber", "071100046");
        jo.put("phoneNumber","");
        jo.put("gaid","");
        JSONObject subJO = new JSONObject();
        subJO.put("appId","");
        subJO.put("appName","");
        subJO.put("versionName","");
        subJO.put("versionCode","");
        subJO.put("firstInstallTime","");
        subJO.put("lastUpdateTime","");
        subJO.put("systemApp","");

        JSONArray ja = new JSONArray();
        ja.add(subJO);
        jo.put("data",ja);



        System.out.println(jo.toJSONString());


        try {
            HttpCustomResponse response = HttpClientPoolingCrawler.custom().url(url).headers(headers).json(jo.toJSONString()).post();
            if (response.getStatusCode() == HttpStatus.SC_OK) {
                String responseBody = response.getResponseBody();
                logger.info("responseBody:{}", responseBody);
            }else{
                logger.info("not 200 :{}",response.getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
