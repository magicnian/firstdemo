package cn.magicnian.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MagicAround {

    String value();

    Class[] releationClazz();
}
