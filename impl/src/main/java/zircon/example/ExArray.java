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
        if (array == null) return null;
        final T[] nArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length + add.length);
        System.arraycopy(array, 0, nArray, 0, array.length);
        System.arraycopy(add, 0, nArray, array.length, add.length);
        return nArray;
    }

    @ExMethod
    public static byte[] add(byte[] array, byte... add) {
        if (array == null) return null;
        final byte[] nArray = (byte[]) Array.newInstance(array.getClass()
                                                              .getComponentType(), array.length + add.length);
        System.arraycopy(array, 0, nArray, 0, array.length);
        System.arraycopy(add, 0, nArray, array.length, add.length);
        return nArray;
    }

    @ExMethod
    public static int[] add(int[] array, int... add) {
        if (array == null) return null;
        final int[] nArray = new int[array.length + add.length];
        System.arraycopy(array, 0, nArray, 0, array.length);
        System.arraycopy(add, 0, nArray, array.length, add.length);
        return nArray;
    }

    @ExMethod
    public static long[] add(long[] array, long... add) {
        if (array == null) return null;
        final long[] nArray = new long[array.length + add.length];
        System.arraycopy(array, 0, nArray, 0, array.length);
        System.arraycopy(add, 0, nArray, array.length, add.length);
        return nArray;
    }

    @ExMethod
    public static double[] add(double[] array, double... add) {
        if (array == null) return null;
        final double[] nArray = new double[array.length + add.length];
        System.arraycopy(array, 0, nArray, 0, array.length);
        System.arraycopy(add, 0, nArray, array.length, add.length);
        return nArray;
    }

    @ExMethod
    public static float[] add(float[] array, float... add) {
        if (array == null) return null;
        final float[] nArray = new float[array.length + add.length];
        System.arraycopy(array, 0, nArray, 0, array.length);
        System.arraycopy(add, 0, nArray, array.length, add.length);
        return nArray;
    }

    @ExMethod
    public static char[] add(char[] array, char... add) {
        if (array == null) return null;
        final char[] nArray = new char[array.length + add.length];
        System.arraycopy(array, 0, nArray, 0, array.length);
        System.arraycopy(add, 0, nArray, array.length, add.length);
        return nArray;
    }

    @ExMethod
    public static <T> T[] copy(T[] array) {
        if (array == null) return null;
        final T[] nArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length);
        System.arraycopy(array, 0, nArray, 0, array.length);
        return nArray;
    }

    @ExMethod
    public static int[] copy(int[] array) {
        if (array == null) return null;
        final int[] nArray = new int[array.length];
        System.arraycopy(array, 0, nArray, 0, array.length);
        return nArray;
    }

    @ExMethod
    public static double[] copy(double[] array) {
        if (array == null) return null;
        final double[] nArray = new double[array.length];
        System.arraycopy(array, 0, nArray, 0, array.length);
        return nArray;
    }

    @ExMethod
    public static byte[] copy(byte[] array) {
        if (array == null) return null;
        final byte[] nArray = new byte[array.length];
        System.arraycopy(array, 0, nArray, 0, array.length);
        return nArray;
    }

    @ExMethod
    public static boolean[] copy(boolean[] array) {
        if (array == null) return null;
        final boolean[] nArray = new boolean[array.length];
        System.arraycopy(array, 0, nArray, 0, array.length);
        return nArray;
    }

    public static long[] copy(long[] array) {
        if (array == null) return null;
        final long[] nArray = new long[array.length];
        System.arraycopy(array, 0, nArray, 0, array.length);
        return nArray;
    }

    @ExMethod
    public static <T> List<T> toList(T[] array) {
        if (array == null) return null;
        List<T> list = new ArrayList<T>();
        Collections.addAll(list, array);
        return list;
    }

    @ExMethod
    public static <E> E find(E[] collection, Predicate<E> predicate) {
        if (collection == null) return null;
        for (E e : collection) {
            if (predicate.test(e)) return e;
        }
        return null;
    }

    @ExMethod
    public static int find(int[] collection, IntPredicate predicate, int defaultValue) {
        for (int e : collection) {
            if (predicate.test(e)) return e;
        }
        return defaultValue;
    }

    @ExMethod
    public static double find(double[] collection, DoublePredicate predicate, double defaultValue) {
        for (double e : collection) {
            if (predicate.test(e)) return e;
        }
        return defaultValue;
    }

    @ExMethod
    public static long find(long[] collection, LongPredicate predicate, long defaultValue) {
        for (long e : collection) {
            if (predicate.test(e)) return e;
        }
        return defaultValue;
    }


    @ExMethod
    public static <E> E[] findAll(E[] collection, Predicate<E> predicate) {
        if (collection == null) return null;
        final Object nAr = Array.newInstance(collection.getClass().getComponentType(), 0);
        return Arrays.asList(collection).filter(predicate).toArray((E[]) nAr);
    }

    @ExMethod
    public static int[] findAll(int[] collection, IntPredicate predicate) {
        if (collection == null) return null;
        return IntStream.of(collection).filter(predicate).toArray();
    }

    @ExMethod
    public static long[] findAll(long[] collection, LongPredicate predicate) {
        if (collection == null) return null;
        return LongStream.of(collection).filter(predicate).toArray();
    }

    @ExMethod
    public static double[] findAll(double[] collection, DoublePredicate predicate) {
        if (collection == null) return null;
        return DoubleStream.of(collection).filter(predicate).toArray();
    }

    @ExMethod
    public static <E> E[] filter(E[] collection, Predicate<E> predicate) {
        if (collection == null) return null;
        return findAll(collection, predicate);
    }

    @ExMethod
    public static int[] filter(int[] collection, IntPredicate predicate) {
        if (collection == null) return null;
        return findAll(collection, predicate);
    }

    @ExMethod
    public static double[] filter(double[] collection, DoublePredicate predicate) {
        if (collection == null) return null;
        return findAll(collection, predicate);
    }

    @ExMethod
    public static long[] filter(long[] collection, LongPredicate predicate) {
        if (collection == null) return null;
        return findAll(collection, predicate);
    }

    @ExMethod
    public static <E> E[] createObjectArray(Class<E> clazz, int length) {
        final Object nAr = Array.newInstance(clazz, length);
        return (E[]) nAr;
    }

    @ExMethod
    public static <T> List<T> list(T[] array) {
        if (array == null) return null;
        return toList(array);
    }

    @ExMethod
    public static List<Integer> list(int[] array) {
        if (array == null) return null;
        List<Integer> list = new ArrayList<>();
        for (int i : array) {
            list.add(i);
        }
        return list;
    }

    @ExMethod
    public static List<Long> list(long[] array) {
        if (array == null) return null;
        List<Long> list = new ArrayList<>();
        for (long i : array) {
            list.add(i);
        }
        return list;
    }

    @ExMethod
    public static List<Double> list(double[] array) {
        if (array == null) return null;
        List<Double> list = new ArrayList<>();
        for (double i : array) {
            list.add(i);
        }
        return list;
    }

    @ExMethod
    public static List<Float> list(float[] array) {
        if (array == null) return null;
        List<Float> list = new ArrayList<>();
        for (float i : array) {
            list.add(i);
        }
        return list;
    }

    @ExMethod
    public static List<Character> list(char[] array) {
        if (array == null) return null;
        List<Character> list = new ArrayList<>();
        for (char i : array) {
            list.add(i);
        }
        return list;
    }

    @ExMethod
    public static List<Boolean> list(boolean[] array) {
        if (array == null) return null;
        List<Boolean> list = new ArrayList<>();
        for (boolean i : array) {
            list.add(i);
        }
        return list;
    }


    @ExMethod
    public static IntStream stream(int[] array) {
        return Arrays.stream(array);
    }

    @ExMethod
    public static <T> Stream<T> stream(T[] array) {
        return Arrays.stream(array);
    }

    @ExMethod
    public static DoubleStream stream(double[] array) {
        return Arrays.stream(array);
    }

    @ExMethod
    public static LongStream stream(long[] array) {
        return Arrays.stream(array);
    }


    @ExMethod
    public static <T> T get(T[] array, int index) {
        if (array == null) return null;
        return array[index];
    }
}
