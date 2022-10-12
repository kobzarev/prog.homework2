package task2;

import annotation.SaveTo;
import annotation.Saver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Task2 {
    public static void save(Container container) {
        Class<?> cls = container.getClass();
        if (cls.isAnnotationPresent(SaveTo.class)) {
            String path = cls.getAnnotation(SaveTo.class).path();
            Method[] methods = cls.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Saver.class)) {
                    try {
                        method.invoke(container, path);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    public static boolean check(Container container) {
        Class<?> cls = container.getClass();
        if (cls.isAnnotationPresent(SaveTo.class)) {
            String path = cls.getAnnotation(SaveTo.class).path();
            File file = new File(path);
            if (!file.exists() || !file.isFile()) {
                return false;
            }
            Field field = null;
            String text = "";
            try {
                Field[] fields = cls.getDeclaredFields();
                field = cls.getDeclaredField("text");
                field.setAccessible(true);
                text = (String)field.get(container);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                return false;
            }

            String stringInFile = "";
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                stringInFile = reader.readLine();
            } catch (IOException e) {
                return false;
            }
            return stringInFile.equals(text);
        }
        return false;
    }
}
