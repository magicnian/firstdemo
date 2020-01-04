package cn.magicnian.tl;

public class Demo {

    public static void main(String[] args) {
        String s = "bb";
        change(s);
        System.out.println(s);

        int i = 0;
        change(i);
        System.out.println(i);


        StringBuilder sb = new StringBuilder("00");
        change(sb);
        System.out.println(sb.toString());
    }

    private static void change(StringBuilder sb){
        sb.append("11");
    }

    private static void change(String s){
        s = "aa";
    }

    private static void change(int i){
        i = 12;
    }
}
