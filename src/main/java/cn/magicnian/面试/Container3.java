package cn.magicnian.面试;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Container3 {
    private List<Object> cache = new ArrayList<>();

    private Object get(int i){return cache.get(i);}

    private void set(Object o){cache.add(o);}

    public static void main(String[] args) {
        Container3 container3 = new Container3();

        CountDownLatch countDownLatch = new CountDownLatch(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    try{
                        Thread.sleep(1000);
                    }catch (Exception e){

                    }
                    container3.set(new Object());
                    System.out.println("add "+i);

                    if(container3.cache.size()==5){
                        countDownLatch.countDown();
                    }
                }
            }
        },"t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    countDownLatch.await();
                    System.out.println("t2结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"t2").start();
    }
}
