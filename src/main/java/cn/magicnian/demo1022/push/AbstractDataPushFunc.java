package cn.magicnian.demo1022.push;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEvent;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractDataPushFunc implements InitializingBean {

    private static Logger logger = LoggerFactory.getLogger(AbstractDataPushFunc.class);

    private static final Map<Class<? extends ApplicationEvent>, AbstractDataPushFunc> DATA_PUSH_FUNC_MAP = new ConcurrentHashMap<>();

    protected void register(Class<? extends ApplicationEvent> eventClass, AbstractDataPushFunc dataPushFunc) {
        DATA_PUSH_FUNC_MAP.put(eventClass, dataPushFunc);
    }

    public static AbstractDataPushFunc getDataPushFunc(Class<? extends ApplicationEvent> eventClass) {
        Assert.isTrue(eventClass != null, "事件class不能为null!");
        return DATA_PUSH_FUNC_MAP.get(eventClass);
    }

    /**
     * 数据推送-自由处理
     * 可以是简单的http接口，或者是rpc调用，自定义报文协议通过netty发送等
     * @param param
     */
    public abstract void doDataPush(String... param);
}
