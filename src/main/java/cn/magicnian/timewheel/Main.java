package cn.magicnian.timewheel;

import cn.magicnian.service.AsyncTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        RingBufferWheel wheel = new RingBufferWheel(executorService, 512);
//        for (int i = 0; i < 65; i++) {
//            RingBufferWheel.Task task = new MyJob(i);
//            task.setKey(i);
//            wheel.addTask(task);
//        }
        RingBufferWheel.Task task = new MyJob(20);
        task.setKey(20);
        wheel.addTask(task);

//        wheel.start();
        logger.info("task size:{}", wheel.taskSize());

        wheel.stop(false);

    }

    public static class MyJob extends RingBufferWheel.Task {
        private int num;

        public MyJob(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            logger.info("num:{}", num);
        }
    }
}
