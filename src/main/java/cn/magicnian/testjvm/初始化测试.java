package cn.magicnian.testjvm;

public class 初始化测试 {

    static{
        i = 0;
//        System.out.println(i);
    }
    static int i = 1;

    public static void main(String[] args) {
        System.out.println(i);
    }
}
