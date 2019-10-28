package cn.magicnian.mobile;

import cn.magicnian.util.HttpClientPoolingCrawler;
import cn.magicnian.util.HttpContextUtil;
import cn.magicnian.util.HttpCustomResponse;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.HttpContext;

public class CookieTest {

    public static void main(String[] args) throws Exception{
        String url = "https://90f4220f-58c2-4248-abca-f233b5e24b5f.mock.pstmn.io";

        HttpCustomResponse httpCustomResponse = HttpClientPoolingCrawler.custom().context(HttpContextUtil.getContext("001")).url(url).get();

        HttpContext context = HttpContextUtil.getContext("001");
        BasicCookieStore cookieStore = (BasicCookieStore) context.getAttribute("http.cookie-store");
        cookieStore.getCookies().forEach(c->{
            System.out.println(c.getName());
            System.out.println(c.getValue());
        });
    }
}
