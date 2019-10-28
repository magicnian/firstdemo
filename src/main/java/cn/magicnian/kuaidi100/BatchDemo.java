package cn.magicnian.kuaidi100;

import cn.magicnian.util.HttpClientPoolingCrawler;
import cn.magicnian.util.HttpCustomResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BatchDemo {

    private static String[] locations = new String[5];

    private static String json = "{\"latitude\":##,\"longitude\":@@,\"addressinfo\":\"江苏省南京市秦淮区罗廊巷2号小区\",\"appid\":\"com.Kingdee.Express\",\"versionCode\":531,\"os_version\":\"android9\",\"os_name\":\"TNY-AL00\",\"t\":++,\"tra\":\"0d81f02d-fe90-4ef3-b212-5badc724d082\",\"uchannel\":\"null\",\"nt\":\"wifi\",\"mType\":\"mars\",\"mLatitude\":##,\"mLongitude\":@@,\"adcode\":\"320104\",\"address\":\"江苏省南京市秦淮区侯家桥417-1号靠近环宇商务中心\",\"apiversion\":8}";

    static{
        locations[0] = "32.038593,118.771877";
        locations[1] = "39.891965,116.303443";
        locations[2] = "38.299458,116.820777";
        locations[3] = "36.659575,117.148245";
        locations[4] = "34.382638,108.985307";

    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        while(true){
            try {
                executorService.execute(BatchDemo::task);
                Thread.sleep(500);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
//        System.out.println(getRandomJson());
    }


    private static void task() {
        try {
            String url = "https://p.kuaidi100.com/apicenter/kdmkt.dox";

            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/x-www-form-urlencoded");
            headers.put("Host", "p.kuaidi100.com");
            headers.put("Connection", "Keep-Alive");
            headers.put("Accept-Encoding", "gzip");
            headers.put("User-Agent", "okhttp/3.8.1");

            Map<String, String> paramData = new HashMap<>();
            paramData.put("Name", "value");
            paramData.put("method", "queryMyMkt");
            paramData.put("json",getRandomJson());
            paramData.put("userid", "0");
            paramData.put("hash", "1D33BF9B287B0A6E00358695DA8398C8");
            paramData.put("token", "");

            HttpCustomResponse response = HttpClientPoolingCrawler.custom().url(url).headers(headers).data(paramData).post();

            System.out.println(Thread.currentThread().getName() + ": response==>" + response.getResponseBody());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getRandomJson(){
        Random r = new Random();
        int index = r.nextInt(4);
        String lat = locations[index].split(",")[0];
        String lgt = locations[index].split(",")[1];
        String timestampstr = String.valueOf(System.currentTimeMillis());

        return json.replace("##",lat).replace("@@",lgt).replace("++",timestampstr);
    }
}
