package cn.magicnian.demo1022.group.impl;

import cn.magicnian.demo1022.event.DemoEvent;
import cn.magicnian.demo1022.group.AbstractDataGroupFunc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * 示例数据组装实现类
 */
@Component
public class DemoDataGroupFunc extends AbstractDataGroupFunc {

    private static Logger logger = LoggerFactory.getLogger(DemoDataGroupFunc.class);

    @Override
    public String doDataGroup(ApplicationEvent event) {
        //TODO 自由的组装数据
        // 复杂数据报文建议做方法拆分，简单的如对象转json字符串可以几段代码搞定
        // 这里同样也可以利用设计模式做简化，如模板模式，工厂模式，针对不同的event做不同的处理
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        register(DemoEvent.class, this);
        logger.info("{} | dataGroup register ==>", this.getClass().getName());
    }
}
