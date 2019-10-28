package cn.magicnian.kuaidi100;

import cn.magicnian.util.HttpClientPoolingCrawler;
import cn.magicnian.util.HttpCustomResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Demo {
    public static void main(String[] args) throws  Exception{
        String url = "https://p.kuaidi100.com/apicenter/kdmkt.dox";

        Map<String,String> headers = new HashMap<>();
        headers.put("Content-Type","application/x-www-form-urlencoded");
        headers.put("Host","p.kuaidi100.com");
        headers.put("Connection","Keep-Alive");
        headers.put("Accept-Encoding","gzip");
        headers.put("User-Agent","okhttp/3.8.1");

        Map<String,String> paramData = new HashMap<>();
        paramData.put("Name","value");
        paramData.put("method","queryMyMkt");
        paramData.put("json","{\"latitude\":32.038593,\"longitude\":118.771877,\"addressinfo\":\"江苏省南京市秦淮区罗廊巷2号小区\",\"appid\":\"com.Kingdee.Express\",\"versionCode\":531,\"os_version\":\"android9\",\"os_name\":\"TNY-AL00\",\"t\":1550198483079,\"tra\":\"0d81f02d-fe90-4ef3-b212-5badc724d082\",\"uchannel\":\"null\",\"nt\":\"wifi\",\"mType\":\"mars\",\"mLatitude\":32.038593,\"mLongitude\":118.771877,\"adcode\":\"320104\",\"address\":\"江苏省南京市秦淮区侯家桥417-1号靠近环宇商务中心\",\"apiversion\":8}");
        paramData.put("userid","0");
        paramData.put("hash","1D33BF9B287B0A6E00358695DA8398C8");
        paramData.put("token","");

        HttpCustomResponse response =  HttpClientPoolingCrawler.custom().url(url).headers(headers).data(paramData).post();

        log.info("statusCode:{}", response.getStatusCode());

        log.info("responseBody:{}", response.getResponseBody());
    }
}
