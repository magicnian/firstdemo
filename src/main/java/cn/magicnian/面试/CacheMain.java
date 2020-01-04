package cn.magicnian.面试;

public class CacheMain {

    public static void main(String[] args) {
        CacheData cacheData = new CacheData();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                String result = cacheData.doCache("key1");
                System.out.println("t1---结果： " + result);
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                String result = cacheData.doCache("key1");
                System.out.println("t2---结果： " + result);
            }
        }, "t2");

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                String result = cacheData.doCache("key1");
                System.out.println("t3---结果： " + result);
            }
        }, "t3");


        t1.start();
//        t2.start();
//        t3.start();
    }
}
