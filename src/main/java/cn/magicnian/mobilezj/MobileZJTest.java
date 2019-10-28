package cn.magicnian.mobilezj;

import cn.magicnian.util.HttpClientPoolingCrawler;
import cn.magicnian.util.HttpContextUtil;
import cn.magicnian.util.HttpCustomResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.protocol.HttpContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class MobileZJTest {

    private static final String userName = "13758287445";
    private static final String servicePwd = "445026";

    private static String sms = "";
    private static String imageCode = "";
    private static String smalart = "";
    private static String smalartRequest = "";


    public static void main(String[] args) throws Exception {
//        System.out.println("请输入：");
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("你输入的是："+br.readLine());
        String uid = "001";
        loginPage(uid);
        getImage(uid);
        verifyImage(uid);
        login(uid);
        fistjump(uid);
        assertTion(uid);
        getSMALRequest(uid);
        checkSMALRequest(uid);
        checkSmalart(uid);
        sendSms(uid);
        sendSms(uid);
        checkSms(uid);
        getUserInfo(uid);
//        queryCall(uid);
//        queryBill(uid);
    }

    private static void loginPage(String uid) throws Exception {
        String url = "https://zj.ac.10086.cn/login";

        Map<String, String> header = new HashMap<>();
        header.put("Host", "zj.ac.10086.cn");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");

        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid))
                .url(url).headers(header).get();

    }


    private static void getImage(String uid) throws Exception {
        String url = "https://zj.ac.10086.cn/common/image.jsp";

        Map<String, String> header = new HashMap<>();
        header.put("Host", "zj.ac.10086.cn");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
        header.put("referer", "https://zj.ac.10086.cn/login");

        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid))
                .url(url).headers(header).get();

        String image = Base64.getEncoder().encodeToString(httpCustomResponse.getResponseBytes());
        System.out.println("==========image=========:" + image);
    }

    private static boolean verifyImage(String uid) throws Exception {
        System.out.println("请输入验证码：");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        imageCode = br.readLine();

        String url = "https://zj.ac.10086.cn/validImageCode?r_0.47750521527423206&imageCode=" + imageCode;
        Map<String, String> header = new HashMap<>();
        header.put("Host", "zj.ac.10086.cn");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
        header.put("referer", "https://zj.ac.10086.cn/login");

        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid))
                .url(url).headers(header).get();

        if ("1".equals(httpCustomResponse.getResponseBody().replaceAll("\r|\n", ""))) {
            System.out.println("==== 图像验证码校验通过 =====");
            return true;
        } else {
            System.out.println("===== 图像验证码校验失败 =====");
            return false;
        }
    }

    private static void login(String uid) throws Exception {
        String url = "https://zj.ac.10086.cn/Login";

        Map<String, String> header = new HashMap<>();
        header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        header.put("Host", "zj.ac.10086.cn");
        header.put("Content-Type", "application/x-www-form-urlencoded");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
        header.put("referer", "https://zj.ac.10086.cn/login");
        header.put("Origin", "https://zj.ac.10086.cn");

        Map<String, String> datas = new HashMap<>();
        datas.put("type", "B");
        datas.put("backurl", "https://zj.ac.10086.cn/login/backPage.jsp");
        datas.put("warnurl", "https://zj.ac.10086.cn/login/warnPage.jsp");
        datas.put("errorurl", "https://zj.ac.10086.cn/login/errorPage.jsp");
        datas.put("spid", "8ace47be5dbc4890015dbfe2e4c80004");
        datas.put("RelayState", "type=B;backurl=http://www.zj.10086.cn/my/servlet/assertion;nl=6;loginFromUrl=http%3A%2F%2Fwww.zj.10086.cn%2Fmy%2Findex.do;callbackurl=/servlet/assertion;islogin=true");
        datas.put("mobileNum", "6009892E46CA806DAE3A0A1E0C4465DA9E86B21121E691C6");
        datas.put("loginmodel", "");
        datas.put("validCode", imageCode);
        datas.put("smsValidCode", "");
        datas.put("servicePassword", "2C5D7C1F505B315690F96C74C03973D5");
        datas.put("login_pwd_type", "");
        datas.put("cardId", "");

        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid))
                .url(url).headers(header).data(datas).post();

        String response = httpCustomResponse.getResponseBody();

        Document document = Jsoup.parse(response);
        if (response.contains("SAMLart")) {
            Elements attributeValue = document.getElementsByAttributeValue("name", "SAMLart");
            if (null != attributeValue) {
                smalart = attributeValue.val();
                System.out.println("===== smlart is ======" + smalart);
            }
        }

    }


    private static void fistjump(String uid) throws Exception {
        String url = "https://zj.ac.10086.cn/login/backPage.jsp";

        Map<String, String> header = new HashMap<>();
        header.put("Host", "zj.ac.10086.cn");
        header.put("Content-Type", "application/x-www-form-urlencoded");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
        header.put("referer", "https://zj.ac.10086.cn/login");
        header.put("Origin", "https://zj.ac.10086.cn");

        Map<String, String> datas = new HashMap<>();
        datas.put("AppSessionId", "NotExist");
        datas.put("SAMLart", smalart);
        datas.put("isEncodePassword", "2");
        datas.put("displayPic", "1");
        datas.put("RelayState", "type=B;backurl=http://www.zj.10086.cn/my/servlet/assertion;nl=6;loginFromUrl=http%3A%2F%2Fwww.zj.10086.cn%2Fmy%2Findex.do;callbackurl=/servlet/assertion;islogin=true");
        datas.put("isEncodeMobile", "2");
        datas.put("displayPics", "mobile_sms_login:0===sendSMS:0===mobile_servicepasswd_login:0");

        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid))
                .url(url).charset("GBK").headers(header).data(datas).post();

        String response = httpCustomResponse.getResponseBody();

        System.out.println("===== first jump response ======" + response);

    }

    private static void assertTion(String uid) throws Exception {
        String url = "http://www.zj.10086.cn/my/servlet/assertion";

        Map<String, String> header = new HashMap<>();
        header.put("Host", "www.zj.10086.cn");
        header.put("Content-Type", "application/x-www-form-urlencoded");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");

        Map<String, String> datas = new HashMap<>();
        datas.put("SAMLart", smalart);
        datas.put("RelayState", "type=B;backurl=http://www.zj.10086.cn/my/servlet/assertion;nl=6;loginFromUrl=http%3A%2F%2Fwww.zj.10086.cn%2Fmy%2Findex.do;callbackurl=/servlet/assertion;islogin=true");

        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid))
                .url(url).headers(header).data(datas).post();

        if (httpCustomResponse.getStatusCode() == 302) {
            System.out.println("assertTion get 302");
        }
    }


    private static void myIndex(String uid) throws Exception {
        String url = "http://www.zj.10086.cn/my/index.do";

        Map<String, String> header = new HashMap<>();
        header.put("Host", "www.zj.10086.cn");
        header.put("Content-Type", "application/x-www-form-urlencoded");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");

        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid))
                .url(url).headers(header).get();
        System.out.println("======== myIndex response code======" + httpCustomResponse.getStatusCode());
    }


    private static void getSMALRequest(String uid) throws Exception {
        String url = "http://service.zj.10086.cn/yw/detail/queryHisDetailBill.do?menuId=13009&bid=BD399F39E69148CFE044001635842131";

        Map<String, String> header = new HashMap<>();
        header.put("Host", "www.zj.10086.cn");
        header.put("Referer", "http://www.zj.10086.cn/my/index.do");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");

        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid))
                .url(url).headers(header).get();

        String response = httpCustomResponse.getResponseBody();
        System.out.println("======= getSMALRequest response========" + response);

        int index = response.indexOf("SAMLRequest");
        int start = response.indexOf('=', index) + 2;
        int end = response.indexOf('/', start) - 1;
        if (end > start) {
            smalartRequest = response.substring(start, end);
            System.out.println("====== smalrtRequest is======" + smalartRequest);
        }

    }


    private static void checkSMALRequest(String uid) throws Exception {
        String url = "https://zj.ac.10086.cn/POST";

        Map<String, String> header = new HashMap<>();
        header.put("Host", "zj.ac.10086.cn");
        header.put("Origin", "http://service.zj.10086.cn");
        header.put("Referer", "http://service.zj.10086.cn/yw/detail/queryHisDetailBill.do?menuId=13009&bid=BD399F39E69148CFE044001635842131");
        header.put("Content-Type", "application/x-www-form-urlencoded");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");

        Map<String, String> datas = new HashMap<>();
        datas.put("SAMLRequest", smalartRequest);
        datas.put("RelayState", "backurl=http%3A%2F%2Fservice.zj.10086.cn%2Fservlet%2Fassertion;nl=6;spid=8ace47be5dbc4890015dbf86a5d60001;type=A;callbackurl=%2Fservlet%2Fassertion;loginFromUrl=http%3A%2F%2Fservice.zj.10086.cn%3A80%2Fyw%2Fdetail%2FqueryHisDetailBill.do%3FmenuId%3D13009%26bid%3DBD399F39E69148CFE044001635842131;errorUrl=http%3A%2F%2Fservice.zj.10086.cn%2Flogin%2FwarnPage.jsp;bid=BD399F39E69148CFE044001635842131;menuId=13009");

        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid))
                .url(url).headers(header).data(datas).post();

        String response = httpCustomResponse.getResponseBody();

        System.out.println("====== checkSMALRequest response =========" + response);

        int index = response.indexOf("SAMLart");
        int start = response.indexOf('=', index) + 2;
        int end = response.indexOf('/', start) - 1;
        if (end > start) {
            smalart = response.substring(start, end);
            System.out.println("===== smalrt is ======" + smalart);
        }

    }


    private static void checkSmalart(String uid) throws Exception {
        String url = "http://service.zj.10086.cn/servlet/assertion";

        Map<String, String> header = new HashMap<>();
        header.put("Host", "service.zj.10086.cn");
        header.put("Origin", "null");
        header.put("Content-Type", "application/x-www-form-urlencoded");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");

        Map<String, String> datas = new HashMap<>();
        datas.put("AppSessionId", "NotExist");
        datas.put("SAMLart", smalart);
        datas.put("isEncodePassword", "2");
        datas.put("displayPic", "0");
        datas.put("RelayState", "backurl=http%3A%2F%2Fservice.zj.10086.cn%2Fservlet%2Fassertion;nl=6;spid=8ace47be5dbc4890015dbf86a5d60001;type=A;callbackurl=%2Fservlet%2Fassertion;loginFromUrl=http%3A%2F%2Fservice.zj.10086.cn%3A80%2Fyw%2Fdetail%2FqueryHisDetailBill.do%3FmenuId%3D13009%26bid%3DBD399F39E69148CFE044001635842131;errorUrl=http%3A%2F%2Fservice.zj.10086.cn%2Flogin%2FwarnPage.jsp;bid=BD399F39E69148CFE044001635842131;menuId=13009");
        datas.put("isEncodeMobile", "2");
        datas.put("displayPics", "");


        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid))
                .url(url).headers(header).data(datas).post();

        System.out.println("===== checkSmalart response =======" + httpCustomResponse.getResponseBody());

        if (httpCustomResponse.getStatusCode() == 302) {
            System.out.println("===== checkSmalart get 302");
        }
    }


    private static void sendSms(String uid) throws Exception {
        String url = "http://service.zj.10086.cn/yw/detailbill/sendValidateCode.do?bid=";

        Map<String, String> header = new HashMap<>();
        header.put("Accept", "*/*");
        header.put("Accept-Encoding", "gzip, deflate");
        header.put("Accept-Language", "zh-CN,zh;q=0.9");
        header.put("X-Requested-With", "XMLHttpRequest");
        header.put("Host", "service.zj.10086.cn");
        header.put("Origin", "http://service.zj.10086.cn");
        header.put("Referer", "http://service.zj.10086.cn/yw/detail/queryHisDetailBill.do?menuId=13009&bid=BD399F39E69148CFE044001635842131");
        header.put("Content-Type", "application/x-www-form-urlencoded");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");

//        Map<String, String> datas = new HashMap<>();
//        datas.put("bid", "");

        HttpContext httpContext = HttpContextUtil.getContext(uid);
        BasicClientCookie newCookie1 = new BasicClientCookie("CmLocation", "571|571");
        newCookie1.setDomain("10086.cn");
        newCookie1.setAttribute("path", "/");
        newCookie1.setAttribute("domain", ".10086.cn");
        newCookie1.setPath("/");
        BasicClientCookie newCookie2 = new BasicClientCookie("CmProvid", "zj");
        newCookie2.setDomain("10086.cn");
        newCookie2.setPath("/");
        newCookie2.setAttribute("path", "/");
        newCookie2.setAttribute("domain", ".10086.cn");

        CookieStore cookieStore = (CookieStore) httpContext.getAttribute(ClientContext.COOKIE_STORE);
        cookieStore.addCookie(newCookie1);
        cookieStore.addCookie(newCookie2);
//        Cookie cookie = cookieStore.getCookies().stream().filter(c->c.getName().equals("CmWebtokenid")).findFirst().orElse(null);
//        if(null!=cookie){
//            BasicClientCookie newCookie = new BasicClientCookie(cookie.getName(),cookie.getValue());
//            newCookie.setValue("13758287445,zj");
//            newCookie.setDomain(cookie.getDomain());
//            newCookie.setPath(cookie.getPath());
//            newCookie.setExpiryDate(cookie.getExpiryDate());
//            cookieStore.addCookie(newCookie);
//        }

        httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);

        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(httpContext)
                .url(url).headers(header).post();

        if (!StringUtils.isEmpty(httpCustomResponse.getResponseBody())) {
            System.out.println("====短信发送成功=====");
        } else if (httpCustomResponse.getResponseBody().contains("请稍后再试")) {
            System.out.println("====短信发送失败 =====");
        }

    }


    private static void checkSms(String uid) throws Exception {

        System.out.println("请输入短信验证码：");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sms = br.readLine();


        String url = "http://service.zj.10086.cn/yw/detail/secondPassCheck.do";

        Map<String, String> header = new HashMap<>();
        header.put("Host", "service.zj.10086.cn");
        header.put("Origin", "http://service.zj.10086.cn");
        header.put("Referer", "http://service.zj.10086.cn/yw/detail/queryHisDetailBill.do?menuId=13009&bid=BD399F39E69148CFE044001635842131");
        header.put("Content-Type", "application/x-www-form-urlencoded");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");

        Map<String, String> datas = new HashMap<>();
        datas.put("validateCode", sms);
        datas.put("bid", "BC5CC0A69BC10482E044001635842132");

        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid))
                .url(url).headers(header).data(datas).post();

        if ("12".equals(httpCustomResponse.getResponseBody())) {
            System.out.println("======= 验证通过 =======");
        }
    }

    private static void getUserInfo(String uid) throws Exception{
        String url = "http://www.zj.10086.cn/my/userinfo/queryUserYdInfo.do";

        Map<String, String> datas = new HashMap<>();
        datas.put("fromFlag", "");
        datas.put("secPwd", sms);

        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("Host", "www.zj.10086.cn");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");

        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid))
                .url(url).headers(headers).data(datas).charset("gb2312").post();

        System.out.println("======用户信息======"+httpCustomResponse.getResponseBody());
    }


    private static void queryCall(String uid) throws Exception {
        String url = "http://service.zj.10086.cn/yw/detail/queryHisDetailBill.do?bid=&menuId=13009&listtype=1&month=10-2019";

        Map<String, String> header = new HashMap<>();
        header.put("Host", "service.zj.10086.cn");
        header.put("Origin", "http://service.zj.10086.cn");
        header.put("Referer", "http://service.zj.10086.cn/yw/detail/queryHisDetailBill.do?menuId=13009&bid=BD399F39E69148CFE044001635842131");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");

        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid))
                .url(url).charset("gb2312").headers(header).get();


        System.out.println("===== 通话详单 =====" + httpCustomResponse.getResponseBody());

    }

    public static void queryBill(String uid) throws Exception {
        String url = "http://service.zj.10086.cn/yw/bill/billDetail.do" +
                "?menuId=13003&bid=BD399F39E69148CFE044001635842132&month=09-2019";

        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        headers.put("Host", "service.zj.10086.cn");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");

        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext(uid))
                .url(url).headers(headers).charset("gb2312").get();

        System.out.println("======历史账单======" + httpCustomResponse.getResponseBody());
    }

}
