package cn.magicnian.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public class MethodParameterSpy {

    public static void main(String[] args) throws Exception {
        Class<?> c = Class.forName("cn.magicnian.reflect.MyMethodTest");
        Method[] allMethods = c.getDeclaredMethods();

        for (Method method : allMethods) {
            System.out.println("================================================");
            System.out.println("formatString: " + method.toGenericString());

            System.out.println("ReturnType: " + method.getReturnType());

            System.out.println("GenericReturnType: " + method.getGenericReturnType());

            System.out.println("Modifier: "+ Modifier.toString(method.getModifiers()));

            Class<?>[] pType = method.getParameterTypes();
            Type[] gType = method.getGenericParameterTypes();

            for (int i = 0; i < pType.length; i++) {
                System.out.println("ParameterType: " + pType[i]);

                System.out.println("GenericParameterType: " + gType[i]);
            }

            Class<?>[] xType = method.getExceptionTypes();
            Type[] gxType = method.getGenericExceptionTypes();
            for (int i = 0; i < xType.length; i++) {
                System.out.println("ExceptionType: " + xType[i]);

                System.out.println("GenericException: " + gxType[i]);
            }

        }
    }
}
