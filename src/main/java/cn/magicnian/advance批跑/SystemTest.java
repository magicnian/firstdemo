package cn.magicnian.advance批跑;

public class SystemTest {

    private static volatile boolean stop= false;

    public static void main(String[] args) throws Exception{
        while (System.in.read()!='a'){
            System.out.println("--running--");
            Thread.sleep(1000);
        }

        if(System.in.read()!='a')
            System.out.println("--stop--");
    }
}
