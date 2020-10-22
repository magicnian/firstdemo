package cn.magicnian.advance批跑;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CompareDemo {

    private static final String fileName1 = "C:\\Users\\nian\\Desktop\\审核前.csv";
    private static final String fileName2 = "C:\\Users\\nian\\Desktop\\审核后.csv";


    public static void main(String[] args) {
        List<String> beforeResults = new ArrayList<>();
        List<String> afterResults = new ArrayList<>();
        List<String> befores = getContents(fileName1);
        List<String> afters = getContents(fileName2);

        for (int i = 1; i < befores.size(); i++) {
            for (int j = 1; j < afters.size(); j++) {
                String[] beforeStrs = befores.get(i).split(",");
                String[] afterStrs = afters.get(j).split(",");
                if (beforeStrs.length < 12 || afterStrs.length < 4)
                    continue;
                if (beforeStrs[0].equals(afterStrs[0])) {
                    if (!beforeStrs[9].equals(afterStrs[2])) {
                        beforeResults.add(befores.get(i));
                        afterResults.add(afters.get(j));
                    }
                }
            }
        }

        System.out.println(beforeResults.size());

        for (String s : beforeResults) {
            System.out.println(s);
        }

        System.out.println("=======================================");

        for (String s : afterResults) {
            System.out.println(s);
        }

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
