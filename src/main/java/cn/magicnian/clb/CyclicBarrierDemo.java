package cn.magicnian.clb;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("扫尾工作");
            }
        });

        for (int i=0;i<2;i++){
            new Thread(new Task(cyclicBarrier)).start();
        }

//        Thread.sleep(5000);
//
//        for(int i=0;i<2;i++){
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        System.out.println("临时线程"+Thread.currentThread().getName()+"开始执行。。。");
//                        Thread.sleep(2000);
//                        cyclicBarrier.await();
//                        System.out.println("临时线程"+Thread.currentThread().getName()+"执行完成。。。");
//                        System.out.println();
//                    }catch (Exception e){
//
//                    }
//                }
//            },"tt").start();
//        }

    }

    static class Task implements Runnable{

        private final CyclicBarrier cyclicBarrier;

        public Task(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                System.out.println("线程"+Thread.currentThread().getName()+" 开始执行第一次。。。");
                Thread.sleep(3000);
                cyclicBarrier.await();
                System.out.println("线程"+Thread.currentThread().getName()+" 第一次执行完成。。。");


//                System.out.println("线程"+Thread.currentThread().getName()+" 开始执行第二次。。。");
//                Thread.sleep(3000);
//                cyclicBarrier.await();
//                System.out.println("线程"+Thread.currentThread().getName()+" 第二次执行完成。。。");


            }catch (Exception e){
                return;
            }
        }
    }
}
