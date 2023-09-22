package zircon.example;

import zircon.ExMethod;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExArray {
    @ExMethod
    public static <T> T[] add(T[] array, T... add) {
        final T[] nArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length + add.length);
        System.arraycopy(array, 0, nArray, 0, array.length);
        System.arraycopy(add, 0, nArray, array.length, add.length);
        return nArray;
    }

    @ExMethod
    public static <T> List<T> toList(T[] array) {
        List<T> list = new ArrayList<T>();
        Collections.addAll(list, array);
        return list;
    }
    @ExMethod
    public static <T> List<T> list(T[] array) {
        return toList(array);
    }

    @ExMethod
    public static <T> T get(T[] array, int index) {
        return array[index];
    }
}
