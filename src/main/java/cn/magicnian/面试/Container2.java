package cn.magicnian.面试;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Container2 {

    private List<Object> cache = new ArrayList<>();

    private Object get(int i){return cache.get(i);}

    private void set(Object o){cache.add(o);}


    public static void main(String[] args) {
        Container2 container2 = new Container2();

        ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock wl = rwl.writeLock();
        ReentrantReadWriteLock.ReadLock rl = rwl.readLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                wl.lock();
                for(int i=0;i<10;i++){
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){

                    }
                    container2.set(new Object());
                    System.out.println("add "+i);

                    if(container2.cache.size()==5){
                        wl.unlock();
                    }
                }

                System.out.println("t1结束");
            }
        },"t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                rl.lock();
                System.out.println("t2结束");
            }
        },"t2").start();
    }
}
