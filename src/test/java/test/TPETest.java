package test;

public class TPETest {

    public static void main(String[] args) {
        int a = Integer.SIZE-3;
        int v = -1 << a;
        System.out.println(Integer.toBinaryString(-1));
        //左移29位，低位补0
        System.out.println(Integer.toBinaryString(v));

        System.out.println(Integer.toBinaryString(8));
        System.out.println(~8);
    }
}
