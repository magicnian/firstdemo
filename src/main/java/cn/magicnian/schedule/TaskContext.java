package cn.magicnian.schedule;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class TaskContext {

    Logger logger = LogManager.getLogger(TaskContext.class);

    private static volatile TaskContext taskContext = null;

    private Integer ticks = 30;

    private volatile Integer current_index = 0;

    private Map<Integer, Set<String>> slots = new ConcurrentHashMap<Integer, Set<String>>();


    private TaskContext() {
        init();
    }

    public static TaskContext getInstance() {
        if (taskContext == null) {
            synchronized (TaskContext.class) {
                if (taskContext == null) {
                    taskContext = new TaskContext();
                }
            }
        }
        return taskContext;
    }


    private void init() {
        for (int i = 0; i < ticks; i++) {
            slots.put(i, new CopyOnWriteArraySet<String>());
        }
    }

    public synchronized void addTask(Integer index,String uid) {
        Set<String> uids = slots.get(index);
        uids.add(uid);
    }

    public void removeCurrentIndexTasks() {
        Set<String> uids = slots.get(current_index);
        logger.info("========准备清理，当前索引:" + current_index + ",总计清理：" + uids.size());
        uids.clear();
    }

    public Integer cutDownIndex(){
        if (current_index == 0) {
            return ticks - 1;
        } else {
            return current_index - 1;
        }
    }


    public synchronized void plusCurrenIndex() {
        if (current_index == ticks - 1) {
            current_index = 0;
        } else {
            current_index += 1;
        }
    }

    public Integer getCurrent_index() {
        return current_index;
    }
}
