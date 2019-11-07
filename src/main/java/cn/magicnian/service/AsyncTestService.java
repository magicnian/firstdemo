package cn.magicnian.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
public class AsyncTestService {

    private Logger logger = LoggerFactory.getLogger(AsyncTestService.class);


    @Async(value = "namedTaskExecutor")
    public Future<String> testFunc(){
        try {
            TimeUnit.SECONDS.sleep(5);
            logger.info("test async log");
        }catch (Exception e){
            e.printStackTrace();
        }

        return new AsyncResult<>("test");
    }
}
