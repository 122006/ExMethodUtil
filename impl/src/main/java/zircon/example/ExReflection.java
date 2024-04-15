package zircon.example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

import zircon.ExMethod;
import zircon.example.ExArray;
import zircon.example.ExClass;
import zircon.example.ExCollection;

public class ExReflection {
    @ExMethod
    public static <T, R> R getStaticFieldValue(Class<T> clazz, String fieldName) {
        try {
            final Field declaredField = clazz.getDeclaredField(fieldName);
            declaredField.setAccessible(true);
            return (R) declaredField.get(null);
        } catch (Exception e) {
            return null;
        }
    }

    @ExMethod
    public static <T, R> R reflectionFieldValue(T obj, String fieldName) {
        try {
            Class<?> clazz = obj.getClass();
            do {
                for (Field field : clazz.getDeclaredFields()) {
                    if (Objects.equals(field.getName(), fieldName)) {
                        field.setAccessible(true);
                        return (R) field.get(obj);
                    }
                }
            } while ((clazz = clazz.getSuperclass()) != Object.class);
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @ExMethod
    public static <T, R> R invokeStaticMethod(Class<T> clazz, String methodName, Object... args) {
        try {
            final List<Method> methods = clazz.getDeclaredMethods().toList()
                                              .filter(method -> Objects.equals(method.getName(), methodName) && method.getParameterTypes().length == args.length);
            if (methods.isEmpty()) return null;
            me:
            for (Method method : methods) {
                final Class<?>[] parameterTypes = method.getParameterTypes();
                for (int i = 0; i < parameterTypes.length; i++) {
                    final Object arg = args[i];
                    if (!parameterTypes[i].isAssignableFrom(arg.getClass())) {
                        continue me;
                    }
                }
                method.setAccessible(true);
                return (R) method.invoke(null, args);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @ExMethod
    public static <T, R> R reflectionInvokeMethod(T obj, String methodName, Object... args) {
        try {
            Class<?> clazz = obj.getClass();
            do {
                final List<Method> methods = clazz.getDeclaredMethods().toList()
                                                  .filter(method -> Objects.equals(method.getName(), methodName) && method.getParameterTypes().length == args.length);
                if (methods.isEmpty()) continue;
                me:
                for (Method method : methods) {
                    final Class<?>[] parameterTypes = method.getParameterTypes();
                    for (int i = 0; i < parameterTypes.length; i++) {
                        final Object arg = args[i];
                        if (!parameterTypes[i].primToWrap().isAssignableFrom(arg.getClass().primToWrap())) {
                            continue me;
                        }
                    }
                    method.setAccessible(true);
                    return (R) method.invoke(obj, args);
                }
            } while ((clazz = clazz.getSuperclass()) != Object.class);
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
