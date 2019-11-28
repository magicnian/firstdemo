package cn.magicnian.面试;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现一个容器，提供两个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中
 * 线程2实现监控元素的个数
 * 当个数到5个时，线程2给出提示，并结束线程2
 */
public class Container1 {

    private List<String> list = new ArrayList<>();

    public void add(String s){
        list.add(s);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {

        Container1 container = new Container1();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    container.add("value");
                    System.out.println("添加完成");
                }

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(container.size()==5){
                        System.out.println("====元素个数已经达到5");
                        throw new RuntimeException("exit");
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}
