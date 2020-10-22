package cn.magicnian.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyMagicAroudTest {

    private Logger logger = LoggerFactory.getLogger(MyMagicAroudTest.class);


    @MagicAround(value = "function1",releationClazz = String.class)
    public String function1(String key) {
        logger.info("......pring key:{}......", key);
        return "success";
    }
}
