package cn.magicnian.深源批跑;
import cn.magicnian.深源批跑.SYBatchDemo;
import cn.magicnian.深源批跑.BatchDownload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
    static final Logger logger = LoggerFactory.getLogger(Main.class);

    static String url = "https://cloudapi.accuauth.com/image/quality";
    static String apiId = "123";
    static String apiSecret = "123";

    //    private static final String path = "D:\\inda_batch\\bad.txt";
    private static final String path = "D:\\inda_batch\\good.csv";

    public static void main(String[] args) throws Exception{
        String downloadpath = "D:\\inda_batch\\result\\good_download.csv";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)), "utf-8"));
        String lineText = null;
        int count = 0;
        while ((lineText = br.readLine()) != null) {

        }
    }
}
