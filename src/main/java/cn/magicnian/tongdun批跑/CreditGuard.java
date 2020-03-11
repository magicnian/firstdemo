package cn.magicnian.tongdun批跑;

import cn.magicnian.advance批跑.EcoBatch;
import cn.magicnian.advance批跑.IdCheckBatch;
import cn.magicnian.util.HttpClientPoolingCrawler;
import cn.magicnian.util.HttpCustomResponse;
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
import java.util.stream.Collectors;

public class CreditGuard {

    private static final Logger logger = LoggerFactory.getLogger(CreditGuard.class);

    private static final String o_fileName = "C:\\Users\\nian\\Desktop\\tongdun\\advanceai_527.csv";

    private static final String s_fileName = "C:\\Users\\nian\\Desktop\\tongdun\\审核后.csv";

    private static final String b_fileName = "C:\\Users\\nian\\Desktop\\tongdun\\审核前.csv";

    private static final String partner_code = "WeShare_vn";
    private static final String partner_key = "23b97770aaaf484eabe58ee63d65d15b";
    private static final String app_name = "Vtien_and";

    public static void main(String[] args) throws Exception {


        WritableWorkbook writableWorkbook = Workbook.createWorkbook(new File("C:\\Users\\nian\\Desktop\\tongdun\\creditguard_result.xls"));
        WritableSheet sheet = writableWorkbook.createSheet("sheet1", 0);
        String[] titles = {"ugid", "idNo", "name", "response"};
        Label label = null;
        //设置第一行，表头
        for (int i = 0; i < titles.length; i++) {
            label = new Label(i, 0, titles[i]);
            sheet.addCell(label);
        }


        StringBuilder urlAppender = new StringBuilder("https://api.tongdun.net/aurora/apply/v1");

        urlAppender.append("?partner_code=" + partner_code);
        urlAppender.append("&partner_key=" + partner_key);
        urlAppender.append("&app_name=" + app_name);

        String url = urlAppender.toString();

        List<String> o_contents = getContents(o_fileName);
        List<String> s_contents = getContents(s_fileName);
        List<String> b_contents = getContents(b_fileName);

        Map<String, GuardParamter> s_map = new HashMap<>();
        for (String s : s_contents) {
            String[] splits = s.split(",");
            GuardParamter paramter = new GuardParamter();
            String idNo = null;
            if (splits.length >= 4) {
                idNo = splits[2];
                if (idNo.contains("real_idcard"))
                    continue;
                //不足9位，前面补0至9位，大于9位，小于12位，前面补0至12位
                if (idNo.length() < 9) {
                    while (idNo.length() < 9) {
                        idNo = "0" + idNo;
                    }
                } else if (idNo.length() > 9 && idNo.length() < 12) {
                    while (idNo.length() < 12) {
                        idNo = "0" + idNo;
                    }
                }
            }
            paramter.setUgid(splits[0]);
            paramter.setIdNo(idNo);
            paramter.setName(splits.length < 4 ? null : splits[3]);
            s_map.put(splits[0], paramter);
        }

        for (String b : b_contents) {
            String[] splits = b.split(",");
            String ugId = splits[0];
            GuardParamter paramter = s_map.get(ugId);
            if (paramter != null && paramter.getIdNo() != null && paramter.getName() != null) {
                paramter.setPhone(splits.length < 12 ? null : splits[8]);
            }
        }

        Map<String, GuardParamter> result_map = s_map.entrySet().stream()
                .filter(e -> e.getValue().getName() != null && e.getValue().getIdNo() != null & e.getValue().getPhone() != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


        int i = 1;

        for (Map.Entry<String, GuardParamter> e : result_map.entrySet()) {

            Map<String, String> datas = new HashMap<>();
            datas.put("full_nm", e.getValue().getName());
            datas.put("id_num", e.getValue().getIdNo());
            datas.put("act_mbl", e.getValue().getPhone());

            try {
                HttpCustomResponse response = HttpClientPoolingCrawler.custom().url(url).data(datas).post();
                if (response.getStatusCode() == HttpStatus.SC_OK) {
                    String responseBody = response.getResponseBody();
                    logger.info("responseBody:{}", responseBody);
                } else {
                    logger.info("not 200 :{}", response.getStatusCode());
                }


                label = new Label(0, i, e.getValue().getUgid());
                sheet.addCell(label);

                label = new Label(1, i, e.getValue().getIdNo());
                sheet.addCell(label);

                label = new Label(2, i, e.getValue().getName());
                sheet.addCell(label);

                label = new Label(3, i, response.getResponseBody());
                sheet.addCell(label);

            } catch (Exception ex) {
                logger.error("get exception:{}", ex);
            }

            i++;

        }

        writableWorkbook.write();
        writableWorkbook.close();

        logger.info("==========finish===========");

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
    public static class GuardParamter {
        private String ugid;
        private String name;
        private String idNo;
        private String phone;
    }
}
