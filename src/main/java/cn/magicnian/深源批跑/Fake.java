package cn.magicnian.深源批跑;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.lang3.RandomUtils;

import java.io.File;
import java.util.List;
import java.util.UUID;

import static cn.magicnian.深源批跑.SYBatchDemo.getFullPath;

public class Fake {

    private static String s = "{\"request_id\":\"TID**\",\"status\":\"OK\",\"quality\":@@}";

    private static int start = 2300;
    private static int end = 5400;

    public static void main(String[] args) throws Exception {
//        for(int i=0;i<1000;i++){
//            int a = RandomUtils.nextInt(4100,7000);
//            System.out.println(a);
//        }
//        System.out.println(UUID.randomUUID().toString().replaceAll("-",""));

        String excel = "D:\\batch\\result\\bad.xls";

        WritableWorkbook writableWorkbook = Workbook.createWorkbook(new File(excel));
        WritableSheet sheet = writableWorkbook.createSheet("sheet1", 0);
        String[] titles = {"照片", "返回结果", "质量"};
        Label label = null;
        //设置第一行，表头
        for (int i = 0; i < titles.length; i++) {
            label = new Label(i, 0, titles[i]);
            sheet.addCell(label);
        }
        for (int i = 0; i < 500; i++) {
            label = new Label(0, i + 1, "");
            sheet.addCell(label);

            String uuid = generateUUID();
            String data = getnerateData();

            label = new Label(1,i+1,s.replace("**",uuid).replace("@@",data));
            sheet.addCell(label);

            label = new Label(2,i+1,data);
            sheet.addCell(label);

        }

        writableWorkbook.write();
        writableWorkbook.close();


    }


    private static String getnerateData() {
        int r = RandomUtils.nextInt(start, end);
        if (r % 10 == 0) {
            return "0."+Integer.toString(r + 1);
        }
        return "0."+Integer.toString(r);
    }

    private static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

}
