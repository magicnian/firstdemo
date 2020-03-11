package cn.magicnian.advance批跑;

import cn.magicnian.util.HttpClientPoolingCrawler;
import cn.magicnian.util.HttpCustomResponse;
import com.alibaba.fastjson.JSONObject;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EcoBatch {

    private static final Logger logger = LoggerFactory.getLogger(EcoBatch.class);

    private static final String o_fileName = "C:\\Users\\nian\\Desktop\\advanceai\\advanceai_527.csv";

    private static final String s_fileName = "C:\\Users\\nian\\Desktop\\advanceai\\审核后.csv";

    private static final String b_fileName = "C:\\Users\\nian\\Desktop\\advanceai\\审核前.csv";

    private static final String url = "https://vn-api.advance.ai/vn/openapi/verification/v1/ecommerce-account-detection";

    private static final String access_key = "123";

    public static void main(String[] args) throws Exception {
        WritableWorkbook writableWorkbook = Workbook.createWorkbook(new File("C:\\Users\\nian\\Desktop\\advanceai\\eco_result.xls"));
        WritableSheet sheet = writableWorkbook.createSheet("sheet1", 0);
        String[] titles = {"ugid", "phoneNo", "response"};
        Label label = null;
        //设置第一行，表头
        for (int i = 0; i < titles.length; i++) {
            label = new Label(i, 0, titles[i]);
            sheet.addCell(label);
        }

        List<String> o_contents = getContents(o_fileName);
        List<String> b_contents = getContents(b_fileName);

        Map<String, String> s_map = new HashMap<>(600);
        for (String b : b_contents) {
            String[] splits = b.split(",");
            s_map.put(splits[0], splits.length < 12 ? null : splits[8]);
        }

        logger.info("===============start facebook =====================");

        for (int i = 1; i < o_contents.size(); i++) {
            String s = s_map.get(o_contents.get(i));

            if (StringUtils.isEmpty(s) || s.contains("real_idcard") || s.length() < 9 || s.length() > 10)
                continue;

            if (s.startsWith("0"))
                s = s.substring(1);

            //调用接口

            Map<String, String> headers = new HashMap<>();
            headers.put("X-ADVAI-KEY", access_key);
            headers.put("Content-Type", "application/json");

            Map<String, String> params = new HashMap<>();
            params.put("countryCode", "+84");
            params.put("number", s);
            String json = JSONObject.toJSONString(params);

            try {
                HttpCustomResponse response = HttpClientPoolingCrawler.custom().url(url).headers(headers).json(json).post();
                if (response.getStatusCode() == HttpStatus.SC_OK) {
                    String responseBody = response.getResponseBody();
                    logger.info("uGid : {},phoneNo : {},response : {}", o_contents.get(i), s, responseBody);
                    if (responseBody.contains("OVER_QUERY_LIMIT")) {
                        logger.info("over query limit!");
                        break;
                    }
                } else {
                    logger.info("not 200 :{}", response.getStatusCode());
                }
                label = new Label(0, i, o_contents.get(i));
                sheet.addCell(label);

                label = new Label(1, i, s);
                sheet.addCell(label);

                label = new Label(2, i, response.getResponseBody());
                sheet.addCell(label);
            } catch (Exception e) {
                logger.error("get exception:{}", e);
            }
        }

        writableWorkbook.write();
        writableWorkbook.close();

        logger.info("===============eco finish===============");


    }

    static List<String> getContents(String fileName) {
        List<String> stores = new ArrayList<>();
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader bf = new BufferedReader(fr);
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                stores.add(str);
            }
            bf.close();
            fr.close();
            return stores;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
