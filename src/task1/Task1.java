package task1;

import annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Task1 {
    public static void executeMethods(Object obj) {
        Class<?> cls = obj.getClass();
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                System.out.println("find method " + method.getName());
                Test test = method.getDeclaredAnnotation(Test.class);
                int a = test.a();
                int b = test.b();
                System.out.println("param a = " + a + " param b = " + b);
                try {
                    int result = (int)method.invoke(obj, a, b);
                    System.out.println("Result of executing method " + result);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
