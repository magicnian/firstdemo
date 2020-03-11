package cn.magicnian.tongdun批跑;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TongDunExcelDemo {

    private static final String o_fileName = "C:\\Users\\nian\\Desktop\\tongdun\\advanceai_527.csv";

    private static final String s_fileName = "C:\\Users\\nian\\Desktop\\tongdun\\审核后.csv";

    private static final String b_fileName = "C:\\Users\\nian\\Desktop\\tongdun\\审核前.csv";


    public static void main(String[] args) {

        List<String> o_contents = getContents(o_fileName);
        List<String> s_contents = getContents(s_fileName);
        List<String> b_contents = getContents(b_fileName);

        Map<String, CreditGuard.GuardParamter> s_map = new HashMap<>(600);
        for (String s : s_contents) {
            String[] splits = s.split(",");
            CreditGuard.GuardParamter paramter = new CreditGuard.GuardParamter();
            paramter.setIdNo(splits.length < 4 ? null : splits[2]);
            paramter.setName(splits.length < 4 ? null : splits[3]);
            s_map.put(splits[0], paramter);
        }

        for (String b : b_contents) {
            String[] splits = b.split(",");
            String ugId = splits[0];
            CreditGuard.GuardParamter paramter = s_map.get(ugId);
            if (paramter != null && paramter.getIdNo() != null && paramter.getName() != null) {
                paramter.setPhone(splits.length < 12 ? null : splits[8]);
            }
        }

        Map<String, CreditGuard.GuardParamter> result_map = s_map.entrySet().stream()
                .filter(e-> e.getValue().getName()!=null && e.getValue().getIdNo()!=null&e.getValue().getPhone()!=null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println(result_map.size());

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
