package cn.magicnian.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableAsync
public class NamedSpringAsyncConfig {

    @Bean(name = "namedTaskExecutor")
    public Executor namedTaskExecutor(){
        return new ThreadPoolExecutor(2,4,2000, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(2),new NamedThreadFactory(),new NamedRejectedExecutionHandler());
    }
}
