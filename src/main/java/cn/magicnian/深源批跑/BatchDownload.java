package cn.magicnian.深源批跑;

import cn.magicnian.util.HttpClientPoolingCrawler;
import cn.magicnian.util.HttpCustomResponse;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class BatchDownload {

        private static final String path = "D:\\inda_batch\\bad.txt";
//    private static final String path = "D:\\inda_batch\\good.txt";

//    private static final String imagePath = "D:\\batch\\good\\";
    private static final String imagePath = "D:\\batch\\bad\\";


    public static void main(String[] args) throws Exception {
        List<String> resultUrls = getUrls();
        if (resultUrls != null) {
            for (int i = 0; i < resultUrls.size(); i++) {
                String url = resultUrls.get(i);
                String fileName = imagePath + i + ".jpg";
                downloadFile(fileName, resultUrls.get(i));
                System.out.println(i + " finish");
            }
            System.out.println("all finish!");
        }


    }

    private static List<String> getUrls() {
        List<String> results = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)), "utf-8"));
            String lineText = null;
            int count = 0;
            while ((lineText = br.readLine()) != null) {
                count++;
                results.add(lineText);
                System.out.println(lineText);
            }
            System.out.println("总行数：" + count);
        } catch (Exception e) {
            System.out.println("getUrls exception");
            return null;
        }
        return results;
    }

    private static void downloadFile(String fileName, String url) {
        try {
            HttpClientPoolingCrawler clientPoolingCrawler = HttpClientPoolingCrawler.custom();
            HttpCustomResponse response = clientPoolingCrawler.url(url).get();

            File file = new File(fileName);
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(response.getResponseBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("download get error");
        }

    }
}
