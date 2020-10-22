package cn.magicnian.controller;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public abstract class BaseRequest {


    @NotNull
    private String requestId;

    private String tag;


    public void nullFieldValidate() throws IllegalAccessException, InvocationTargetException {
        List<Field> result = new ArrayList<>();
        //获取当前类和父类的所有field（包括object类）
        for(Class<?> c = this.getClass();c!=null;c = c.getSuperclass()){
            Field[] superFields = c.getDeclaredFields();
            result.addAll(Arrays.asList(superFields));
        }
        for (Field field : result) {
            String fieldName = field.getName();
            field.setAccessible(true);
            Object fieldValue = field.get(this);
//            Object fieldValue = runGetter(field,this);

            boolean isAnnotationNotNull = field.isAnnotationPresent(NotNull.class);
            if(isAnnotationNotNull && fieldValue==null){
                System.out.println(fieldName + " can't be null");
            }
        }
    }


    // 由于所有子类的属性都是private的，所以必须要找到属性的getter方法
    // 以下代码借鉴[stackoverflow的文章](https://stackoverflow.com/questions/13400075/reflection-generic-get-field-value)
    private Object runGetter(Field field,Object instance){
        // MZ: Find the correct method
        for (Method method : instance.getClass().getDeclaredMethods()) {
            if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3))) {
                if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
                    // MZ: Method found, run it
                    try {
                        return method.invoke(instance);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        System.out.println("Could not determine method: " + method.getName());
                    }
                }
            }
        }
        return null;
    }
}
