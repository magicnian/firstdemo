package cn.magicnian.面试;

import com.sun.org.apache.xpath.internal.objects.XNumber;

import java.util.concurrent.CyclicBarrier;

/**
 * 99个线程依次打印1至99
 */
public class PrintNumber3 {

    public volatile int number;


    public static void main(String[] args) {
        PrintNumber3 n = new PrintNumber3();
        Object lock = new Object();


        CyclicBarrier cyclicBarrier = new CyclicBarrier(99);

        for (int i = 0; i < 99; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cyclicBarrier.await();
                        synchronized (lock) {
                            System.out.println(++n.number);
                        }
                    } catch (Exception e) {

                    }
                }
            }).start();
        }

    }
}
