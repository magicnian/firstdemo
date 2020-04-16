package cn.magicnian.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

@Component
public class AnnotationMethondExecutor implements ApplicationContextAware, SmartInitializingSingleton {

    private static final Logger logger = LoggerFactory.getLogger(AnnotationMethondExecutor.class);

    private ApplicationContext applicationContext;

    @Override
    public void afterSingletonsInstantiated() {
        String[] beanDefinitionNames = applicationContext.getBeanNamesForType(Object.class, false, true);
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = applicationContext.getBean(beanDefinitionName);

            // referred to ：org.springframework.context.event.EventListenerMethodProcessor.processBean
            Map<Method, MagicAround> annotatedMethods = null;
            try {
                annotatedMethods = MethodIntrospector.selectMethods(bean.getClass(),
                        new MethodIntrospector.MetadataLookup<MagicAround>() {
                            @Override
                            public MagicAround inspect(Method method) {
                                return AnnotatedElementUtils.findMergedAnnotation(method, MagicAround.class);
                            }
                        });
            } catch (Throwable ex) {
                logger.error("MagicAround resolve error for bean [{}]. exception : {}", beanDefinitionName, ex);
            }
            if (annotatedMethods == null || annotatedMethods.isEmpty()) {
                continue;
            }
            for (Map.Entry<Method, MagicAround> methodMagicAroundEntry : annotatedMethods.entrySet()) {
                Method method = methodMagicAroundEntry.getKey();
                MagicAround magicAround = methodMagicAroundEntry.getValue();

                if (magicAround == null) {
                    continue;
                }
                String name = magicAround.value();
                //TODO deal with clazz
                Class[] releationClazz = magicAround.releationClazz();

                if (name.trim().length() == 0) {
                    throw new RuntimeException("magicaround name invalide, for[" + bean.getClass() + "#" + method.getName() + "] .");
                }

                method.setAccessible(true);

                try {
                    //执行该方法
                    Object returnValue = method.invoke(bean, new Object[]{"magicnian"});
                    logger.info("returnValue : {}", returnValue);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
