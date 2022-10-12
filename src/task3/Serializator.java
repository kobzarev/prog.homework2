package task3;

import annotation.Save;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Serializator {
    private final String filename = "file.txt";
    public void serialize(Object o) {
        StringBuilder string = new StringBuilder();
        try {
            Class<?> cls = o.getClass();
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Save.class)) {
                    string.append(String.format("%s=%s;", field.getName(), field.get(o)));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        save(string.toString());
    }
    public Object deserialize() {
        String string = read();
        Map<String, String> params = moveParamsToMap(string);
        User user = null;
        try {
            Class<?> cls = User.class;
            user = (User)getDefaultConstructor(cls).newInstance();
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Save.class)) {
                    if (field.getType() == int.class) {
                        field.setInt(user, Integer.parseInt(params.get(field.getName())));
                    } else {
                        field.set(user, params.get(field.getName()));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return user;
    }
    private void save(String string) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(string);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private String read() {
        String string = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            string = reader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return string;
    }
    private Map<String, String> moveParamsToMap(String params) {
        String[] parts =  params.split("[=;]");
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < parts.length; i++) {
            String key = parts[i];
            i++;
            Object value = parts[i];
            map.put(key.trim(), (String)value);
        }
        return map;
    }
    private Constructor<?> getDefaultConstructor(Class<?> cls) {
        Constructor<?>[] c = cls.getDeclaredConstructors();
        for (Constructor<?> item : c) {
            if (item.getParameterCount() == 0) {
                return item;
            }
        }
        throw new RuntimeException();
    }
}
