package cn.magicnian.面试;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 99个线程按顺序打印1到99
 * 用阻塞队列解决，将任务按照顺序放入队列，
 * 依次取出来消费
 */
public class PrintNumber1 {

    public static void main(String[] args) {
        LinkedBlockingQueue<Task> queue = new LinkedBlockingQueue<>(99);
        for (int i = 1; i <= 99; i++) {
            Task task = new Task(i, queue);
            queue.offer(task);
        }

        Task firstTask = queue.poll();
        new Thread(firstTask).start();
    }


    static class Task implements Runnable {

        private int number;
        private LinkedBlockingQueue<Task> queue;

        public Task(int number, LinkedBlockingQueue<Task> queue) {
            this.number = number;
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(300);
            } catch (Exception e) {

            }
            System.out.println(number);
            Task task = queue.poll();
            new Thread(task).start();
        }
    }
}
