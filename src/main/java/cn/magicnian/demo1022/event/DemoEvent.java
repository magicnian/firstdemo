package cn.magicnian.demo1022.event;

import org.springframework.context.ApplicationEvent;

public class DemoEvent extends ApplicationEvent {

    public DemoEvent(Object source) {
        super(source);
    }
}
