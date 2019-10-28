package test;

import test.model.Elvis2;

import java.lang.reflect.Constructor;

public class Elvis2Test {
    public static void main(String[] args) throws Exception{
        String a[];
        a = new String[]{"123"};
        Constructor<?> constructor = Elvis2.class.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        Elvis2 instance = (Elvis2)constructor.newInstance();
        instance.leaveTheBuilding();
    }
}
