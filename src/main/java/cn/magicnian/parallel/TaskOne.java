package cn.magicnian.parallel;

import java.util.concurrent.CountDownLatch;

public class TaskOne extends AbstractTask{

    public TaskOne(CountDownLatch cdl) {
        super(cdl);
    }

    @Override
    public Object call() throws Exception {
        try {
            Thread.sleep(5000);
            return "hello";
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.cdl.countDown();
        }
        return null;
    }
}
