package cn.magicnian.ucloud;

import cn.magicnian.util.HttpClientPoolingCrawler;
import cn.magicnian.util.HttpCustomResponse;

import java.util.HashMap;
import java.util.Map;

public class SwitchIpTest {

    private static final String ak = "JeM4uPfyLannGeRIyqo3FBIUHBiZETPo";
    private static final String sk = "czfeH6FdohtJt5g0t4jsRyQdei5FfvmU";

    public static void main(String[] args) throws Exception{
        long ttTime = System.currentTimeMillis() / 1000 + 1800;
        WeshareTokenHelper weshareTokenHelper = new WeshareTokenHelper(ak,sk);
        String token = weshareTokenHelper.generateToken("/changan/ucloud/sae/sae-sp-06a/renew_ip/","GET","",null,Integer.parseInt(ttTime + ""));
        String url = "https://devops.weshare.com.cn/changan/ucloud/sae/sae-sp-06a/renew_ip/";
        Map<String,String> headers = new HashMap<>();
//        headers.put("Content-Type","application/json");
        headers.put("X-WeshareAuth-Token",token);

        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().url(url).headers(headers).get();

        System.out.println(httpCustomResponse.getResponseBody());
    }
}
