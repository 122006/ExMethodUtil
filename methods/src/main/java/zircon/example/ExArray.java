package zircon.example;

import zircon.ExMethod;

import java.lang.reflect.Array;

public class ExArray {
    @ExMethod
    public static <T> T[] add(T[] array,T... add) {
        final T[] nArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length + add.length);
        System.arraycopy(array,0,nArray,0,array.length);
        System.arraycopy(add,0,nArray,array.length,add.length);
        return nArray;
    }
}
