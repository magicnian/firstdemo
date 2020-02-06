package cn.magicnian.parallel;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public abstract class AbstractTask implements Callable<Object> {

    protected CountDownLatch cdl;

    public AbstractTask(CountDownLatch cdl) {
        this.cdl = cdl;
    }
}
