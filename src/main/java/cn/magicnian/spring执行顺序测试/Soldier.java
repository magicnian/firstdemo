package cn.magicnian.spring执行顺序测试;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.*;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Soldier implements ApplicationContextAware, DisposableBean, BeanNameAware,
        BeanFactoryAware, MessageSourceAware, ApplicationEventPublisherAware, ResourceLoaderAware, InitializingBean {

    public Soldier() {
        System.out.println("Soldier 构造方法...");
    }

    @PostConstruct
    public void postPostConstruct(){
        System.out.println("@PostConstruct");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("@PreDestroy");
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware.setBeanFactory...");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("BeanNameAware.setBeanName:" + name + "...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean.destroy...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean.afterPropertiesSet...");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContextAware.setApplicationContext.......");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("ApplicationEventPublisherAware.setApplicationEventPublisher...");
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        System.out.println("MessageSourceAware.setMessageSource...");
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("ResourceLoaderAware.setResourceLoader...");
    }
}
