package cn.magicnian.reflect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyMethodTest {

    public void test1(String p1,String p2){
        System.out.println(p1+p2);
    }

    private String test2(List<String> values,String key)throws RuntimeException{
        return "success";
    }

    private List<String> test3(Map<String,Object> store)throws Exception{
        List<String> restult = new ArrayList<>();
        return restult;
    }
}
