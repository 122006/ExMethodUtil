package zircon.example;

import java.util.LinkedHashMap;
import java.util.Map;

import zircon.ExMethod;
import zircon.example.ExObject;

public class ExClass {
    static Map<Class<?>, Class<?>> primToWrap = new LinkedHashMap<Class<?>, Class<?>>(16).let(map -> {
        map.put(boolean.class, Boolean.class);
        map.put(byte.class, Byte.class);
        map.put(char.class, Character.class);
        map.put(double.class, Double.class);
        map.put(float.class, Float.class);
        map.put(int.class, Integer.class);
        map.put(long.class, Long.class);
        map.put(short.class, Short.class);
        map.put(void.class, Void.class);
    });

    @ExMethod
    public static <T> Class<?> primToWrap(Class<T> clazz) {
        return primToWrap.getOrDefault(clazz, clazz);
    }

}
