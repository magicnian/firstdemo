package cn.magicnian.russiaTIN;

import cn.magicnian.util.HttpClientPoolingCrawler;
import cn.magicnian.util.HttpCustomResponse;

import java.util.HashMap;
import java.util.Map;

public class russiaTINDemo {

    private static String first_name = "Сватиков";
    private static String middle_name = "Сергеевич";
    private static String last_name = "Роман";
    private static String birthday = "20.11.1987";
    private static String passport_number = "03 07 749118";

    public static void main(String[] args) throws Exception {
        String url = "https://service.nalog.ru/inn-proc.do";

        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json, text/javascript, */*; q=0.01");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept-Language", "ru;q=0.9,en;q=0.8");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("Host", "service.nalog.ru");
        headers.put("Origin", "https://service.nalog.ru");
        headers.put("Referer", "https://service.nalog.ru/inn.do");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36");

        Map<String, String> datas = new HashMap<>();
        datas.put("c", "innMy");
        datas.put("captcha", "");
        datas.put("captchaToken", "");
        datas.put("fam", first_name);
        datas.put("nam", last_name);
        datas.put("otch", middle_name);
        datas.put("bdate", birthday);
        datas.put("bplace", "");
        datas.put("doctype", "21");
        datas.put("docno", passport_number);
        datas.put("docdt", "");

        HttpCustomResponse response = HttpClientPoolingCrawler.custom().url(url).headers(headers).data(datas).post();

        System.out.println(response.getResponseBody());

    }
}
