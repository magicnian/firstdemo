package cn.magicnian.cdl;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo1 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(2);

        for (int i = 0; i < 2; i++) {
            new Thread(new Worker(startSignal,doneSignal)).start();
        }

        System.out.println("主线程开始执行。。。");
        Thread.sleep(2000);
        startSignal.countDown();
        doneSignal.await();
        System.out.println("主线程继续执行。。。");


    }


    static class Worker implements Runnable {

        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;

        public Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        @Override
        public void run() {
            try {
                startSignal.await();
                System.out.println("线程" + Thread.currentThread().getName() + " 开始执行");
                Thread.sleep(3000);
                doneSignal.countDown();
            } catch (Exception e) {
                return;
            }

        }
    }

}
