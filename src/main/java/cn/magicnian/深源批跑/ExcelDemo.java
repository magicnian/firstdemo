package cn.magicnian.深源批跑;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;

public class ExcelDemo {

    public static void main(String[] args) {
        String fileName = "D:\\write.xls";
        try {
            WritableWorkbook writableWorkbook = Workbook.createWorkbook(new File(fileName));
            WritableSheet sheet = writableWorkbook.createSheet("sheet1",0);
            String[] titles = {"序号","返回结果","质量"};
            Label label = null;
            //设置第一行，表头
            for(int i=0;i<titles.length;i++){
                label = new Label(i,0,titles[i]);
                sheet.addCell(label);
            }

            //插入数据
            for(int i=1;i<11;i++){
                label = new Label(0,i,String.valueOf(i+1));
                sheet.addCell(label);

                label = new Label(1,i,null);
                sheet.addCell(label);

                label = new Label(2,i,i+"");
                sheet.addCell(label);
            }

            writableWorkbook.write();
            writableWorkbook.close();
        }catch (Exception e){

        }

    }
}
