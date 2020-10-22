package cn.magicnian.advance批跑;

import cn.magicnian.util.HttpClientPoolingCrawler;
import cn.magicnian.util.HttpCustomResponse;
import com.alibaba.fastjson.JSONObject;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import lombok.Data;
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

public class IdCheckBatch {
    private static final Logger logger = LoggerFactory.getLogger(IdCheckBatch.class);

    private static final String o_fileName = "C:\\Users\\nian\\Desktop\\advanceai\\advanceai_527.csv";

    private static final String s_fileName = "C:\\Users\\nian\\Desktop\\advanceai\\审核后.csv";

    private static final String url = "https://vn-api.advance.ai/vn/openapi/verification/v2/identity-check";

    private static final String access_key = "123";

    public static void main(String[] args) throws Exception {
        WritableWorkbook writableWorkbook = Workbook.createWorkbook(new File("C:\\Users\\nian\\Desktop\\advanceai\\idcheck_result.xls"));
        WritableSheet sheet = writableWorkbook.createSheet("sheet1", 0);
        String[] titles = {"ugid", "idNo", "name", "response"};
        Label label = null;
        //设置第一行，表头
        for (int i = 0; i < titles.length; i++) {
            label = new Label(i, 0, titles[i]);
            sheet.addCell(label);
        }

        List<String> o_contents = getContents(o_fileName);
        List<String> s_contents = getContents(s_fileName);

        Map<String, idCheckParam> s_map = new HashMap<>(600);
        for (String s : s_contents) {
            String[] splits = s.split(",");
            idCheckParam idCheckParam = new idCheckParam();
            idCheckParam.setIdNumber(splits.length < 4 ? null : splits[2]);
            idCheckParam.setName(splits.length < 4 ? null : splits[3]);
            s_map.put(splits[0], idCheckParam);
        }

        logger.info("===============start idCheck =====================");

        for (int i = 1; i < o_contents.size(); i++) {
            idCheckParam idCheckParam = s_map.get(o_contents.get(i));

            if (idCheckParam.getIdNumber() == null || idCheckParam.getName() == null) {
                continue;
            } else {
                String s = idCheckParam.getIdNumber();
                if (s.contains("real_idcard"))
                    continue;
                //不足9位，前面补0至9位，大于9位，小于12位，前面补0至12位
                if (s.length() < 9) {
                    while (s.length() < 9) {
                        s = "0" + s;
                    }
                } else if (s.length() > 9 && s.length() < 12) {
                    while (s.length() < 12) {
                        s = "0" + s;
                    }
                }
                idCheckParam.setIdNumber(s);
            }

            //调用接口

            Map<String, String> headers = new HashMap<>();
            headers.put("X-ADVAI-KEY", access_key);
            headers.put("Content-Type", "application/json");

            Map<String, String> params = new HashMap<>();
            params.put("idNumber", idCheckParam.getIdNumber());
            params.put("name", idCheckParam.getName());
            String json = JSONObject.toJSONString(params);

            try {
                HttpCustomResponse response = HttpClientPoolingCrawler.custom().url(url).headers(headers).json(json).post();
                if (response.getStatusCode() == HttpStatus.SC_OK) {
                    String responseBody = response.getResponseBody();
                    logger.info("uGid : {},idNo : {},name : {},response : {}", o_contents.get(i), idCheckParam.getIdNumber(), idCheckParam.getName(), responseBody);
                    if (responseBody.contains("OVER_QUERY_LIMIT")) {
                        logger.info("over query limit!");
                        break;
                    }
                } else {
                    logger.info("not 200 :{}", response.getStatusCode());
                }

                label = new Label(0, i, o_contents.get(i));
                sheet.addCell(label);

                label = new Label(1, i, idCheckParam.getIdNumber());
                sheet.addCell(label);

                label = new Label(2, i, idCheckParam.getName());
                sheet.addCell(label);

                label = new Label(3,i,response.getResponseBody());
                sheet.addCell(label);
            } catch (Exception e) {
                logger.error("get exception:{}", e);
            }

        }

        writableWorkbook.write();
        writableWorkbook.close();

        logger.info("===============idCheck finish===============");

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

    @Data
    static class idCheckParam {
        private String idNumber;
        private String name;
    }
}
