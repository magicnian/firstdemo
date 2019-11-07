package cn.magicnian.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class NamedRejectedExecutionHandler implements RejectedExecutionHandler {

    private Logger logger = LoggerFactory.getLogger(NamedRejectedExecutionHandler.class);

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        logger.info("rejected by pool, current queue size : {}", executor.getQueue().size());
    }
}
