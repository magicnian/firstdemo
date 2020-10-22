package cn.magicnian.timewheel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestMain {

    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        MyTimeWheel wheel = new MyTimeWheel(executorService);

        MyTimeTask  task = new MyJob(5,5);
        wheel.addTask(task);

    }

    static class MyJob extends MyTimeTask {
        private int num;

        public MyJob(int delays, int num) {
            super(delays);
            this.num = num;
        }

        @Override
        public void run() {
            logger.info("nuk:{}", num);
        }
    }
}
