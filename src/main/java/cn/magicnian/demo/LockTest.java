package cn.magicnian.demo;

import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    static ReentrantLock reentrantLock = new ReentrantLock(true);

    public static void testSync(){
//        reentrantLock.lock();
        String name = Thread.currentThread().getName();
        if(name.equals("t2"))
            Thread.currentThread().interrupt();
        try {
            reentrantLock.lockInterruptibly();
        } catch (InterruptedException e) {
            System.out.println("xxxxxxxxxxxxxxxxxxxx");
            e.printStackTrace();
        }
        System.out.println(name);
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reentrantLock.unlock();
        System.out.println(name+" end");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run(){
                testSync();
            }
        };
        t1.setName("t1");

        Thread t2 = new Thread(){
            @Override
            public void run(){
                testSync();
            }
        };
        t2.setName("t2");

        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main");

//        t2.interrupt();
    }
}
