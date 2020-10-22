package cn.magicnian.demo1022.group;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEvent;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractDataGroupFunc implements InitializingBean {

    private static Logger logger = LoggerFactory.getLogger(AbstractDataGroupFunc.class);

    private static final Map<Class<? extends ApplicationEvent>, AbstractDataGroupFunc> DATA_GROUP_MAP = new ConcurrentHashMap<>();

    //初始化
    protected void register(Class<? extends ApplicationEvent> eventClass, AbstractDataGroupFunc dataGroup) {
        DATA_GROUP_MAP.put(eventClass, dataGroup);
    }

    public static AbstractDataGroupFunc getDataGroupFunc(Class<? extends ApplicationEvent> eventClass) {
        Assert.isTrue(eventClass != null, "事件class不能为null!");
        return DATA_GROUP_MAP.get(eventClass);
    }

    /**
     * 数据组装-按需自由处理
     * 如可以组装成json字符串或者是自定义报文格式
     * @param event
     * @return
     */
    public abstract String doDataGroup(ApplicationEvent event);
}
