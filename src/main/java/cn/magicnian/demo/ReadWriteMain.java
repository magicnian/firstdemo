package cn.magicnian.demo;

public class ReadWriteMain {

    public static void main(String[] args) {

        //写-写互斥
//        ReadWriteLockTest test = new ReadWriteLockTest();
//
//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                test.put("key1","value1");
//            }
//        });
//
//        Thread t2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                test.put("key1","value1");
//            }
//        });
//
//        Thread t3 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                test.put("key1","value1");
//            }
//        });

//        t1.setName("t1");
//        t2.setName("t2");
//        t3.setName("t3");
//
//        t1.start();
//        t2.start();
//        t3.start();

        //读-写互斥,读-读不互斥
        ReadWriteLockTest test = new ReadWriteLockTest();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.put("key1","value1");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.get("key1");
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.get("key1");
            }
        });


        t1.setName("t1");
        t2.setName("t2");
        t3.setName("t3");

        t1.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        t3.start();



    }
}
