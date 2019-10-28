package cn.magicnian.schedule;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheCleanTask {

    Logger logger = LogManager.getLogger(CacheCleanTask.class);

    @Scheduled(cron = "0 0/1 * * * ?")
    public void clean() {
        TaskContext taskContext = TaskContext.getInstance();
        taskContext.removeCurrentIndexTasks();
        taskContext.plusCurrenIndex();
        logger.info("============清理完成,当前索引：" + taskContext.getCurrent_index() + "===========");
    }


}
