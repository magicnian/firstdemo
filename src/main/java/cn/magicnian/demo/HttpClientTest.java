package cn.magicnian.demo;

import cn.magicnian.util.HttpClientFactory;
import cn.magicnian.util.HttpClientPoolingCrawler;
import cn.magicnian.util.HttpCustomResponse;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class HttpClientTest {
    public static void main(String[] args) throws Exception {

        String path = "C:\\Users\\10015133\\Desktop\\ops逆向相关\\SHEIN_returns_05172019_history.csv";

        List<SyncData> syncDataList = new ArrayList<>();

        LineIterator it = FileUtils.lineIterator(new File(path));

        try {
            while(it.hasNext()){
                String line = it.nextLine();
                String[] lineSplit = line.split(";");
                SyncData syncData = new SyncData();
                syncData.setOrderId(lineSplit[0]);
                syncData.setProductId(lineSplit[1]);
                syncData.setOrderDate(lineSplit[2]);
                syncData.setSize(lineSplit[3]);
                syncData.setCode(lineSplit[4]);
                syncData.setCountry(lineSplit[5]);
                syncDataList.add(syncData);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(syncDataList.size());

        String result = JSONObject.toJSONString(syncDataList);

//        FileUtils.write(new File("C:\\Users\\10015133\\Desktop\\ops逆向相关\\sync.txt"),result,"UTF-8");
        HttpClientPoolingCrawler.custom().url("http://localhost:8888/demo/data").json(result).post();


    }
}
