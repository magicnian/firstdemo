package cn.magicnian.sp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Semaphore semaphore = new Semaphore(5);

        for(int i=0;i<20;i++){
            Task task = new Task(semaphore);
            executorService.execute(task);
        }

        executorService.shutdown();
    }

    static class Task implements Runnable{
        private final Semaphore semaphore;

        public Task(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                if(semaphore.tryAcquire(3, TimeUnit.SECONDS)){
                    System.out.println("线程"+Thread.currentThread().getName()+" 获取资源。。。");

                    Thread.sleep(10000);

                    semaphore.release();
                }else{
                    System.out.println("线程"+Thread.currentThread().getName()+" 规定时间内未获取资源，结束运行。。。");
                }

            }catch (Exception e){
                return;
            }
        }
    }
}
