package cn.magicnian.parallel;

import java.util.concurrent.CountDownLatch;

public class TaskTwo extends AbstractTask{

    public TaskTwo(CountDownLatch cdl) {
        super(cdl);
    }

    @Override
    public Object call() throws Exception {
        try {
            Thread.sleep(5000);
            return "world";
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.cdl.countDown();
        }
        return null;
    }
}
