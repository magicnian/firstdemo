package cn.magicnian.tongdun批跑;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import lombok.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TongDunExcelHandler {

    private static final String s_fileName = "C:\\Users\\nian\\Desktop\\tongdun\\审核后.csv";

    private static final String b_fileName = "C:\\Users\\nian\\Desktop\\tongdun\\审核前.csv";


    public static void main(String[] args) throws Exception {
        WritableWorkbook writableWorkbook = Workbook.createWorkbook(new File("C:\\Users\\nian\\Desktop\\tongdun\\offline_result.xls"));
        WritableSheet sheet = writableWorkbook.createSheet("sheet1", 0);


        List<String> s_contents = getContents(s_fileName);
        List<String> b_contents = getContents(b_fileName);

        Map<String, offlineParamter> s_map = new HashMap<>(600);
        for (String s : s_contents) {
            String[] splits = s.split(",");
            offlineParamter paramter = new offlineParamter();
            String idNo = null;
            if(splits.length>=4){
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
            paramter.setIdNo(idNo);
            paramter.setName(splits.length < 4 ? null : splits[3]);
            s_map.put(splits[0], paramter);
        }

        for (String b : b_contents) {
            String[] splits = b.split(",");
            String ugId = splits[0];
            offlineParamter paramter = s_map.get(ugId);
            if (paramter != null && paramter.getIdNo() != null && paramter.getName() != null) {
                paramter.setPhone(splits.length < 12 ? null : splits[8]);
                paramter.setAppTime(splits.length<12 ? null : splits[1]);
            }
        }

        Map<String, offlineParamter> result_map = s_map.entrySet().stream()
                .filter(e -> e.getValue().getName() != null && e.getValue().getIdNo() != null & e.getValue().getPhone() != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


        int i=0;

        for(Map.Entry<String,offlineParamter> e:result_map.entrySet()){
            Label label = null;
            label = new Label(0,i,e.getValue().getName());
            sheet.addCell(label);

            label = new Label(1,i,e.getValue().getIdNo());
            sheet.addCell(label);

            label = new Label(2,i,e.getValue().getPhone());
            sheet.addCell(label);

            label = new Label(3,i,e.getValue().getAppTime());
            sheet.addCell(label);
            i++;
        }


        writableWorkbook.write();
        writableWorkbook.close();

        System.out.println("finish");
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
    static class offlineParamter{
        private String name;
        private String idNo;
        private String phone;
        private String appTime;
    }
}
