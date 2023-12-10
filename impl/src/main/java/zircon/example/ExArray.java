package zircon.example;

import zircon.ExMethod;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ExArray {
    @ExMethod
    public static <T> T[] add(T[] array, T... add) {
        final T[] nArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length + add.length);
        System.arraycopy(array, 0, nArray, 0, array.length);
        System.arraycopy(add, 0, nArray, array.length, add.length);
        return nArray;
    }
    @ExMethod
    public static <T> T[] copy(T[] array) {
        final T[] nArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length);
        System.arraycopy(array, 0, nArray, 0, array.length);
        return nArray;
    }

    @ExMethod
    public static int[] copy(int[] array) {
        final int[] nArray=new int[array.length];
        System.arraycopy(array, 0, nArray, 0, array.length);
        return nArray;
    }
    @ExMethod
    public static double[] copy(double[] array) {
        final double[] nArray=new double[array.length];
        System.arraycopy(array, 0, nArray, 0, array.length);
        return nArray;
    }
    @ExMethod
    public static byte[] copy(byte[] array) {
        final byte[] nArray=new byte[array.length];
        System.arraycopy(array, 0, nArray, 0, array.length);
        return nArray;
    }
    @ExMethod
    public static boolean[] copy(boolean[] array) {
        final boolean[] nArray=new boolean[array.length];
        System.arraycopy(array, 0, nArray, 0, array.length);
        return nArray;
    }
    public static long[] copy(long[] array) {
        final long[] nArray=new long[array.length];
        System.arraycopy(array, 0, nArray, 0, array.length);
        return nArray;
    }

    @ExMethod
    public static <T> List<T> toList(T[] array) {
        List<T> list = new ArrayList<T>();
        Collections.addAll(list, array);
        return list;
    }
    @ExMethod
    public static <E> E find(E[] collection, Predicate<E> predicate) {
        for (E e : collection) {
            if (predicate.test(e))
                return e;
        }
        return null;
    }
    @ExMethod
    public static int find(int[] collection, IntPredicate predicate, int defaultValue) {
        for (int e : collection) {
            if (predicate.test(e))
                return e;
        }
        return defaultValue;
    }

    @ExMethod
    public static double find(double[] collection, DoublePredicate predicate,double defaultValue) {
        for (double e : collection) {
            if (predicate.test(e))
                return e;
        }
        return defaultValue;
    }
    @ExMethod
    public static long find(long[] collection, LongPredicate predicate,long defaultValue) {
        for (long e : collection) {
            if (predicate.test(e))
                return e;
        }
        return defaultValue;
    }


    @ExMethod
    public static <E> E[] findAll(E[] collection, Predicate<E> predicate) {
        final Object nAr = Array.newInstance(collection.getClass().getComponentType(), 0);
        return Arrays.asList(collection).filter(predicate).toArray((E[])nAr);
    }
    @ExMethod
    public static int[] findAll(int[] collection, IntPredicate predicate) {
        return IntStream.of(collection).filter(predicate).toArray();
    }
    @ExMethod
    public static long[] findAll(long[] collection, LongPredicate predicate) {
        return LongStream.of(collection).filter(predicate).toArray();
    }
    @ExMethod
    public static double[] findAll(double[] collection, DoublePredicate predicate) {
        return DoubleStream.of(collection).filter(predicate).toArray();
    }
    @ExMethod
    public static <E> E[] filter(E[] collection, Predicate<E> predicate) {
        return findAll(collection,predicate);
    }
    @ExMethod
    public static int[] filter(int[] collection, IntPredicate predicate) {
        return findAll(collection,predicate);
    }
    @ExMethod
    public static double[] filter(double[] collection, DoublePredicate predicate) {
        return findAll(collection,predicate);
    }
    @ExMethod
    public static long[] filter(long[] collection, LongPredicate predicate) {
        return findAll(collection,predicate);
    }
    @ExMethod
    public static <E> E[] createObjectArray(Class<E> clazz, int length) {
        final Object nAr = Array.newInstance(clazz, length);
        return (E[]) nAr;
    }
    @ExMethod
    public static <T> List<T> list(T[] array) {
        return toList(array);
    }

    @ExMethod
    public static <T> Stream<T> stream(T[] array) {
        return Arrays.stream(array);
    }
    @ExMethod
    public static <T> T get(T[] array, int index) {
        return array[index];
    }
}
