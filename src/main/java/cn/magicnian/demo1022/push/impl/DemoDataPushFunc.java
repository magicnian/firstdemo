package cn.magicnian.demo1022.push.impl;

import cn.magicnian.demo1022.event.DemoEvent;
import cn.magicnian.demo1022.push.AbstractDataPushFunc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DemoDataPushFunc extends AbstractDataPushFunc {

    private static Logger logger = LoggerFactory.getLogger(DemoDataPushFunc.class);

    @Override
    public void doDataPush(String... param) {
        //TODO 具体的推送数据操作，调用rpc模块的发送组件
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        register(DemoEvent.class, this);
        logger.info("{} | dataPush register ==>", this.getClass().getName());
    }
}
