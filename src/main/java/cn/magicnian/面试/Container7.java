package cn.magicnian.面试;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现一个容器，提供两个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中
 * 线程2实现监控元素的个数
 * 当个数到5个时，线程2给出提示，并结束线程2
 */
public class Container7 {

    private List<String> list = new ArrayList<>();

    public void add(String s){
        list.add(s);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {

        Object lock = new Object();
        Container7 container = new Container7();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    for (int i = 0; i < 10; i++) {
                        container.add("value");
                        System.out.println("添加完成" + i);

                        if(container.size() == 5){
                            lock.notify();
                            try {
                                lock.wait();//阻塞
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }


            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    if (container.size() != 5) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    System.out.println("exit");
                    lock.notify();
                }

            }
        });

        t2.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.start();
    }
}
