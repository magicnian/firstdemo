package cn.magicnian.timewheel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class MyTimeWheel {

    private Logger logger = LoggerFactory.getLogger(MyTimeWheel.class);

    /**
     * 默认时间轮圈数
     */
    private int ticks = 60;
    /**
     * 默认扫描间隔时间，单位：秒
     */
    private int druation = 1;

    private Object[] buffers = new Object[ticks];

    /**
     * 当前指针指向的tick
     */
    private volatile int currenttick;

    private AtomicBoolean isRunning = new AtomicBoolean(false);

    private volatile boolean stop;

    /**
     * 用于执行任务的线程池
     */
    private ExecutorService executorService;

    private ReentrantLock lock = new ReentrantLock();

    public MyTimeWheel(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public MyTimeWheel(int ticks, int druation, ExecutorService executorService) {
        this.ticks = ticks;
        this.druation = druation;
        this.executorService = executorService;
        this.buffers = new Object[ticks];
    }

    public boolean addTask(MyTimeTask task) {
        try {
            lock.lock();
            int delays = task.getDelays();
            //计算当前任务的圈数和下标
            int allSeconds = ticks * druation;
            int cycles = delays / allSeconds;
            int index = ((delays % allSeconds) / druation) + currenttick;
            task.setCycles(cycles);
            task.setIndex(index);

            Set<MyTimeTask> tasks = (Set<MyTimeTask>) buffers[index];
            if (tasks != null) {
                tasks.add(task);
            } else {
                Set<MyTimeTask> taskSet = new HashSet<>();
                taskSet.add(task);
                buffers[index] = taskSet;
            }

            //开始任务
            start();
            return true;
        } catch (Exception e) {
            logger.error("add task get error:{}", e);
            return false;
        } finally {
            lock.unlock();
        }

    }

    /**
     * 启动一个后台线程取任务去执行
     */
    private void start() {
        if (!isRunning.get()) {
            if (isRunning.compareAndSet(isRunning.get(), true)) {
                logger.info("delay task is starting");
                Thread t = new Thread(new MyTriggerJob());
                t.setName("consumer task thread");
                t.start();
            }
        }
    }

    public Set<MyTimeTask> getCurrentTasks() {
        return (Set<MyTimeTask>) buffers[currenttick];
    }

    protected void submit(MyTimeTask task) {
        executorService.submit(task);
    }

    /**
     * 指针向前移动一个槽位
     *
     * @return
     */
    protected int tick() {
        currenttick = (currenttick + 1) % ticks;
        return currenttick;
    }


    private class MyTriggerJob implements Runnable {

        @Override
        public void run() {
            while (!stop) {
                try {
                    logger.info("current tick:{}", currenttick);
                    Set<MyTimeTask> taskSet = getCurrentTasks();
                    if (taskSet != null){
                        Iterator<MyTimeTask> iterators = taskSet.iterator();
                        while (iterators.hasNext()) {
                            MyTimeTask task = iterators.next();
                            if (task.getCycles() == 0) {
                                submit(task);
                                iterators.remove();
                            } else {
                                task.setCycles(task.getCycles() - 1);
                            }
                        }
                    }

                    tick();

                    TimeUnit.SECONDS.sleep(druation);

                } catch (Exception e) {
                    logger.error("Exception", e);
                }

            }
        }
    }

}
