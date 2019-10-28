package cn.magicnian.mobile;

import cn.magicnian.util.HttpClientPoolingCrawler;
import cn.magicnian.util.HttpContextUtil;
import cn.magicnian.util.HttpCustomResponse;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.protocol.HttpContext;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MobileTest {

    private static final String userName = "18851631386";


    public static void main(String[] args) throws Exception{
        loadFlag("001");
        checkNeedSms("001");
        String xa = loadToken("001");
        fistSendSms("001",xa);

    }

    private static void loadFlag(String uid) throws Exception {
        String url = "https://login.10086.cn/loadSendflag.htm?timestamp=";

        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "image/webp,image/apng,image/*,*/*;q=0.8");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        headers.put("Host", "login.10086.cn");
        headers.put("Referer", "https://login.10086.cn/login.html?channelID=12003");
        headers.put("Sec-Fetch-Mode", "no-cors");
        headers.put("Sec-Fetch-Site", "same-origin");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");


        HttpCustomResponse response = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid)).url(url).headers(headers).get();

        HttpContext context = HttpContextUtil.getContext(uid);

        log.info("loadFlag response:{}", response.getResponseBody());

    }

    private static void checkNeedSms(String uid) throws Exception {
        String url = "https://login.10086.cn/needVerifyCode.htm?accountType=01" +
                "&account=" + userName + "&timestamp=" + System.currentTimeMillis();

        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "image/webp,image/apng,image/*,*/*;q=0.8");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        headers.put("Host", "login.10086.cn");
        headers.put("Referer", "https://login.10086.cn/login.html?channelID=12003");
        headers.put("Sec-Fetch-Mode", "no-cors");
        headers.put("Sec-Fetch-Site", "same-origin");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
        headers.put("X-Requested-With", "XMLHttpRequest");

        HttpCustomResponse response = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid)).url(url).headers(headers).get();

        log.info("checkNeedSms response:{}", response.getResponseBody());
    }


    private static String loadToken(String uid) throws Exception {
        String url = "https://login.10086.cn/loadToken.action";

        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "image/webp,image/apng,image/*,*/*;q=0.8");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        headers.put("Host", "login.10086.cn");
        headers.put("Referer", "https://login.10086.cn/login.html?channelID=12003");
        headers.put("Sec-Fetch-Mode", "no-cors");
        headers.put("Sec-Fetch-Site", "same-origin");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
        headers.put("X-Requested-With", "XMLHttpRequest");
        headers.put("Origin", "https://login.10086.cn");

        Map<String, String> datas = new HashMap<>();
        datas.put("userName", userName);

        HttpCustomResponse response = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid)).url(url).headers(headers).data(datas).post();

        log.info("loadToken response:{}", response.getResponseBody());

        LoadTokenResponse loadTokenResponse = JSONObject.parseObject(response.getResponseBody(), LoadTokenResponse.class);

        return loadTokenResponse.getResult();
    }


    private static void fistSendSms(String uid, String xa) throws Exception {
        String url = "https://login.10086.cn/sendRandomCodeAction.action";

        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "image/webp,image/apng,image/*,*/*;q=0.8");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        headers.put("Host", "login.10086.cn");
        headers.put("Referer", "https://login.10086.cn/login.html?channelID=12003");
        headers.put("Sec-Fetch-Mode", "no-cors");
        headers.put("Sec-Fetch-Site", "same-origin");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
        headers.put("X-Requested-With", "XMLHttpRequest");
        headers.put("Origin", "https://login.10086.cn");
        headers.put("Xa-before", xa);

        Map<String, String> datas = new HashMap<>();
        datas.put("userName", userName);
        datas.put("type", "01");
        datas.put("channelID", "12003");

        HttpCustomResponse response = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid)).url(url).headers(headers).data(datas).post();

        log.info("sendSms response:{}", response.getResponseBody());

    }

    private static class LoadTokenResponse {
        private String result;
        private String code;
        private String desc;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
