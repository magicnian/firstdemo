package cn.magicnian.tongdun批跑;

import cn.magicnian.advance批跑.InDemo;
import cn.magicnian.testjvm.EdenDemo;
import cn.magicnian.util.HttpClientPoolingCrawler;
import cn.magicnian.util.HttpCustomResponse;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class TondunDemo {

    private static final Logger logger = LoggerFactory.getLogger(TondunDemo.class);

    public static void main(String[] args) {

        String url = "https://apitest.tongdun.net/aurora/apply/v1?partner_code=WeShare_vn&partner_key=6390c799f86b4541a10d3d496d9c08b7&app_name=Vtien_and";

        Map<String,String> datas = new HashMap<>();
        datas.put("full_nm","Quan ngọc thăng");
        datas.put("id_num","071100046");
        datas.put("act_mbl","0339735861");

        try {
            HttpCustomResponse response = HttpClientPoolingCrawler.custom().url(url).data(datas).post();

            if (response.getStatusCode() == HttpStatus.SC_OK) {
                String responseBody = response.getResponseBody();
                logger.info("responseBody:{}", responseBody);
            }else{
                logger.info("not 200 :{}",response.getStatusCode());
            }

        }catch (Exception e){
            e.printStackTrace();
        }




    }
}
