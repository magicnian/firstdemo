package cn.magicnian.unicom;

import cn.magicnian.util.HttpClientPoolingCrawler;
import cn.magicnian.util.HttpContextUtil;
import cn.magicnian.util.HttpCustomResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class UnicomTest {

    private static final String userName = "13255196995";
    private static final String servicePwd = "688638";
    private static String image = "";
    private static String sms = "";

    public static void main(String[] args) throws Exception {
        String uid = "001";
        checkNeedSms(uid);
        sendSms(uid);
//        getImg(uid);
        login(uid);

        checklogin(uid);
        secondCheckLogin(uid);
        checkMap(uid);
        verifycationSms(uid);


        secondSendSms(uid);
        verifycationSubmit(uid);

//        checklogin(uid);
//        secondCheckLogin(uid);
//        checkMap(uid);


        querySms(uid);
    }

    private static void checkNeedSms(String uid) throws Exception {
        String url = "https://uac.10010.com/portal/Service/CheckNeedVerify?"
                + "callback=" + "jQuery172009550778033418472" + System.currentTimeMillis()
                + "&userName=" + userName
                + "&pwdType=" + "01"
                + "&_=" + System.currentTimeMillis();
        Map<String, String> header = new HashMap<>();
        header.put("Host", "uac.10010.com");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");

        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid))
                .url(url).headers(header).get();


        System.out.println("===================验证是否需要短信===========：" + httpCustomResponse.getResponseBody());
    }

    private static void sendSms(String uid) throws Exception {
        String url = "https://uac.10010.com/portal/Service/SendCkMSG?"
                + "callback=" + "jQuery17202391648691787142_" + System.currentTimeMillis()
                + "&req_time=" + System.currentTimeMillis()
                + "&mobile=" + userName
                + "&_=" + System.currentTimeMillis();

        Map<String, String> headers = new HashMap<>();
        headers.put("Host", "uac.10010.com");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko");
        headers.put("Accept", "text/javascript, application/javascript, application/ecmascript, application/x-ecmascript, */*; q=0.01");
        headers.put("Accept-Language", "zh-Hans-CN,zh-Hans;q=0.8,en-US;q=0.6,en;q=0.4,vi;q=0.2");
        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("X-Requested-With", "XMLHttpRequest");
        headers.put("Referer", "https://uac.10010.com/portal/homeLoginNew");

        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid))
                .url(url).headers(headers).get();

        System.out.println("=================发送短信验证码===================" + httpCustomResponse.getResponseBody());

    }

    private static void getImg(String uid) throws Exception {
        String url = "http://uac.10010.com/portal/Service/CreateImage?" + "t=" + System.currentTimeMillis();
        Map<String, String> header = new HashMap<>();
        header.put("Host", "uac.10010.com");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");

        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid))
                .url(url).headers(header).get();

        String imageResult = Base64.getEncoder().encodeToString(httpCustomResponse.getResponseBytes());

        System.out.println("==============验证码内容============:" + imageResult);
    }

    private static void login(String uid) throws Exception {
        System.out.println("请输入验证码：");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sms = br.readLine();

        String url = "https://uac.10010.com/portal/Service/MallLogin?"
                + "callback=" + "jQuery17204863660364770561_" + System.currentTimeMillis()
                + "&req_time=" + System.currentTimeMillis()
                + "&redirectURL=" + "http://www.10010.com"
                + "&userName=" + userName
                + "&password=" + servicePwd
                + "&pwdType=" + "01"
                + "&productType=" + "01"
                + "&redirectType=" + "01"
                + "&rememberMe=" + "1"
                + "&_=" + System.currentTimeMillis()
                + "&verifyCKCode=" + sms;

        Map<String, String> header = new HashMap<>();
        header.put("Host", "uac.10010.com");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
        header.put("Referer", "https://uac.10010.com/portal/homeLoginNew");

        HttpContext httpContext = HttpContextUtil.getContext(uid);

        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(httpContext)
                .url(url).headers(header).get();

        System.out.println("============登录返回==========" + httpCustomResponse.getResponseBody());


    }

    private static void checklogin(String uid) throws Exception {
        String url = "http://www.10010.com/mall/service/check/checklogin/?_=" + System.currentTimeMillis();

        Map<String, String> header = new HashMap<>();
        header.put("Host", "www.10010.com");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
        header.put("Referer", "http://www.10010.com/net5/034/");
        header.put("Origin", "http://www.10010.com");

        HttpContext httpContext = HttpContextUtil.getContext(uid);
        CookieStore cookieStore = (CookieStore) httpContext.getAttribute(ClientContext.COOKIE_STORE);
        Cookie c1 = cookieStore.getCookies().stream().filter(c -> "_uop_id".equals(c.getName())).findFirst().orElse(null);
        Cookie c2 = cookieStore.getCookies().stream().filter(c -> "JUT".equals(c.getName())).findFirst().orElse(null);

        Map<String, String> datas = new HashMap<>();
        if (null != c2)
            datas.put("jutThird", c2.getValue());
        if (null != c1)
            datas.put("_uop_id", c1.getValue());


        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(httpContext)
                .url(url).headers(header).data(datas).domain("www.10010.com").post();

        System.out.println("=======check login response=========" + httpCustomResponse.getResponseBody());

    }


    private static void secondCheckLogin(String uid) throws Exception {
        String url = "https://iservice.10010.com/e3/static/check/checklogin?_=" + System.currentTimeMillis();

        Map<String, String> header = new HashMap<>();
        header.put("Host", "iservice.10010.com");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
        header.put("Referer", "https://iservice.10010.com/e4/query/calls/call_sms.html?menuId=000100030002&TipsCode=10009");
        header.put("Origin", "https://iservice.10010.com");
        header.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");


        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid))
                .url(url).headers(header).domain("iservice.10010.com").post();

        System.out.println("===========second check login response=========" + httpCustomResponse.getResponseBody());


    }

    private static void checkMap(String uid) throws Exception {
        String url = "https://iservice.10010.com/e3/static/query/checkmapExtraParam?_=" + System.currentTimeMillis();

        Map<String, String> header = new HashMap<>();
        header.put("Host", "iservice.10010.com");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
        header.put("Referer", "https://iservice.10010.com/e4/query/calls/call_sms.html?menuId=000100030002&TipsCode=10009");
        header.put("Origin", "https://iservice.10010.com");
        header.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

        Map<String, String> datas = new HashMap<>();
        datas.put("menuId", "000100030002");

        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid))
                .url(url).headers(header).data(datas).post();

        System.out.println("===========checkMap response=========" + httpCustomResponse.getResponseBody());
    }


    private static void secondSendSms(String uid) throws Exception {
        String url = "https://iservice.10010.com/e3/static/query/sendRandomCode?_=" + System.currentTimeMillis() + "&accessURL=https://iservice.10010.com/e4/query/calls/call_sms-iframe.html&menuid=000100030002";

        Map<String, String> header = new HashMap<>();
        header.put("Host", "iservice.10010.com");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
        header.put("Referer", "https://iservice.10010.com/e4/query/calls/call_sms.html?menuId=000100030002&TipsCode=10009");
        header.put("Origin", "https://iservice.10010.com");

        Map<String, String> datas = new HashMap<>();
        datas.put("menuId", "000100030002");

        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid))
                .url(url).headers(header).data(datas).domain("iservice.10010.com").post();

        System.out.println("========send sms get response========" + httpCustomResponse.getResponseBody());

    }

    private static void verifycationSms(String uid) throws Exception {
        String url = "https://iservice.10010.com/e3/static/query/verificationSms?_=" + System.currentTimeMillis() + "&accessURL=https://iservice.10010.com/e4/query/calls/call_sms-iframe.html";

        Map<String, String> header = new HashMap<>();
        header.put("Host", "iservice.10010.com");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
        header.put("Referer", "https://iservice.10010.com/e4/query/calls/call_sms.html?menuId=000100030002&TipsCode=10009");
        header.put("Origin", "https://iservice.10010.com");

        Map<String, String> datas = new HashMap<>();
        datas.put("menuId", "000100030001");

        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid))
                .url(url).headers(header).data(datas).post();

        System.out.println("============= verifycationSms response =============" + httpCustomResponse.getResponseBody());
    }


    private static void verifycationSubmit(String uid) throws Exception {

        System.out.println("请输入短信验证码：");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sms = br.readLine();

        String url = "https://iservice.10010.com/e3/static/query/verificationSubmit?_=" + System.currentTimeMillis()
                + "&accessURL=https://iservice.10010.com/e4/query/calls/call_sms-iframe.html&menuid=000100030002";

        Map<String, String> header = new HashMap<>();
        header.put("Host", "iservice.10010.com");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
        header.put("Referer", "https://iservice.10010.com/e4/query/calls/call_sms.html?menuId=000100030002&TipsCode=10009");
        header.put("Origin", "https://iservice.10010.com");
        header.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");

        Map<String, String> datas = new HashMap<>();
        datas.put("inputcode", sms);
        datas.put("menuId", "000100030001");

        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid))
                .url(url).headers(header).data(datas).post();



        System.out.println("=========verifycationSubmit response========" + httpCustomResponse.getResponseBody());

        if(httpCustomResponse.getResponseBody().contains("session_fail")){
            verifycationSubmit(uid);
        }
    }


    private static void querySms(String uid) throws Exception {
        String url = "https://iservice.10010.com/e3/static/query/sms?_=" + System.currentTimeMillis()
                + "&accessURL=https://iservice.10010.com/e4/query/calls/call_sms-iframe.html&menuid=000100030002";

        Map<String, String> header = new HashMap<>();
        header.put("Host", "iservice.10010.com");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
        header.put("Referer", "https://iservice.10010.com/e4/query/calls/call_sms.html?menuId=000100030002&TipsCode=10009");
        header.put("Origin", "https://iservice.10010.com");


        Map<String, String> datas = new HashMap<>();
        datas.put("pageNo", "1");
        datas.put("pageSize", "20");
        datas.put("begindate", "20190901");
        datas.put("enddate", "20190930");

        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid))
                .url(url).headers(header).data(datas).post();

        System.out.println("========query sms response=========" + httpCustomResponse.getResponseBody());

    }


}
