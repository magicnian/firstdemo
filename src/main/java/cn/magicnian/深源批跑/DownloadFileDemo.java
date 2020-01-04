package cn.magicnian.深源批跑;

import cn.magicnian.util.HttpClientPoolingCrawler;
import cn.magicnian.util.HttpCustomResponse;

import java.io.File;
import java.io.FileOutputStream;

public class DownloadFileDemo {

    public static void main(String[] args) throws Exception{
        HttpClientPoolingCrawler clientPoolingCrawler = HttpClientPoolingCrawler.custom();

        String url = "https://s3a.rupeebus.com/zzidps/india/20191213/panImg/84fae899ff674e0596119c8daaa33d54.jpeg?AWSAccessKeyId=AICKIBFHOSRTIRMHKBVA&Expires=1891595486&Signature=ewK0ey1qjoeX30MN2mZfDr%2BneLI%3D";

        HttpCustomResponse httpCustomResponse = clientPoolingCrawler.url(url).get();
        byte[] respnseBody = httpCustomResponse.getResponseBytes();
        File file = new File("D:\\test.jpg");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(respnseBody);
        fos.close();
    }
}
