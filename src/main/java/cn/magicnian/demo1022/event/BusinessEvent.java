package cn.magicnian.demo1022.event;

import org.springframework.context.ApplicationEvent;

public class BusinessEvent extends ApplicationEvent {
    public BusinessEvent(Object source) {
        super(source);
    }
}
