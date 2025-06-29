package zircon.example;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import zircon.ExMethod;
import zircon.data.ThrowFunction;
import zircon.example.ExCollection;

@SuppressWarnings("unchecked")
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
    public static List<Integer> toList(int[] array) {
        if (array == null) return null;
        List<Integer> list = new ArrayList<Integer>();
        for (int i : array) {
            list.add(i);
        }
        return list;
    }

    @ExMethod
    public static List<Long> toList(long[] array) {
        if (array == null) return null;
        List<Long> list = new ArrayList<Long>();
        for (long i : array) {
            list.add(i);
        }
        return list;
    }

    @ExMethod
    public static List<Short> toList(short[] array) {
        if (array == null) return null;
        List<Short> list = new ArrayList<Short>();
        for (short i : array) {
            list.add(i);
        }
        return list;
    }

    @ExMethod
    public static List<Boolean> toList(boolean[] array) {
        if (array == null) return null;
        List<Boolean> list = new ArrayList<Boolean>();
        for (boolean i : array) {
            list.add(i);
        }
        return list;
    }

    @ExMethod
    public static List<Double> toList(double[] array) {
        if (array == null) return null;
        List<Double> list = new ArrayList<Double>();
        for (double i : array) {
            list.add(i);
        }
        return list;
    }

    @ExMethod
    public static List<Float> toList(float[] array) {
        if (array == null) return null;
        List<Float> list = new ArrayList<Float>();
        for (float i : array) {
            list.add(i);
        }
        return list;
    }

    @ExMethod
    public static List<Character> toList(char[] array) {
        if (array == null) return null;
        List<Character> list = new ArrayList<Character>();
        for (char i : array) {
            list.add(i);
        }
        return list;
    }

    @ExMethod
    public static List<Byte> toList(byte[] array) {
        if (array == null) return null;
        List<Byte> list = new ArrayList<Byte>();
        for (byte i : array) {
            list.add(i);
        }
        return list;
    }

    @ExMethod
    public static List<Integer> wrap(int[] array) {
        if (array == null) return null;
        List<Integer> list = new ArrayList<Integer>();
        for (int i : array) {
            list.add(i);
        }
        return list;
    }

    @ExMethod
    public static Integer[] wrap2Array(int[] array) {
        if (array == null) return null;
        Integer[] nArray = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            nArray[i] = array[i];
        }
        return nArray;
    }

    @ExMethod
    public static List<Long> wrap(long[] array) {
        if (array == null) return null;
        List<Long> list = new ArrayList<Long>();
        for (long i : array) {
            list.add(i);
        }
        return list;
    }

    @ExMethod
    public static Long[] wrap2Array(long[] array) {
        if (array == null) return null;
        Long[] nArray = new Long[array.length];
        for (int i = 0; i < array.length; i++) {
            nArray[i] = array[i];
        }
        return nArray;
    }

    @ExMethod
    public static List<Short> wrap(short[] array) {
        if (array == null) return null;
        List<Short> list = new ArrayList<Short>();
        for (short i : array) {
            list.add(i);
        }
        return list;
    }

    @ExMethod
    public static Short[] wrap2Array(short[] array) {
        if (array == null) return null;
        Short[] nArray = new Short[array.length];
        for (int i = 0; i < array.length; i++) {
            nArray[i] = array[i];
        }
        return nArray;
    }

    @ExMethod
    public static List<Boolean> wrap(boolean[] array) {
        if (array == null) return null;
        List<Boolean> list = new ArrayList<Boolean>();
        for (boolean i : array) {
            list.add(i);
        }
        return list;
    }

    @ExMethod
    public static Boolean[] wrap2Array(boolean[] array) {
        if (array == null) return null;
        Boolean[] nArray = new Boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            nArray[i] = array[i];
        }
        return nArray;
    }

    @ExMethod
    public static List<Double> wrap(double[] array) {
        if (array == null) return null;
        List<Double> list = new ArrayList<Double>();
        for (double i : array) {
            list.add(i);
        }
        return list;
    }

    @ExMethod
    public static Double[] wrap2Array(double[] array) {
        if (array == null) return null;
        Double[] nArray = new Double[array.length];
        for (int i = 0; i < array.length; i++) {
            nArray[i] = array[i];
        }
        return nArray;
    }

    @ExMethod
    public static List<Float> wrap(float[] array) {
        if (array == null) return null;
        List<Float> list = new ArrayList<Float>();
        for (float i : array) {
            list.add(i);
        }
        return list;
    }

    @ExMethod
    public static Float[] wrap2Array(float[] array) {
        if (array == null) return null;
        Float[] nArray = new Float[array.length];
        for (int i = 0; i < array.length; i++) {
            nArray[i] = array[i];
        }
        return nArray;
    }

    @ExMethod
    public static List<Character> wrap(char[] array) {
        if (array == null) return null;
        List<Character> list = new ArrayList<Character>();
        for (char i : array) {
            list.add(i);
        }
        return list;
    }

    @ExMethod
    public static Character[] wrap2Array(char[] array) {
        if (array == null) return null;
        Character[] nArray = new Character[array.length];
        for (int i = 0; i < array.length; i++) {
            nArray[i] = array[i];
        }
        return nArray;
    }

    @ExMethod
    public static List<Byte> wrap(byte[] array) {
        if (array == null) return null;
        List<Byte> list = new ArrayList<Byte>();
        for (byte i : array) {
            list.add(i);
        }
        return list;
    }

    @ExMethod
    public static Byte[] wrap2Array(byte[] array) {
        if (array == null) return null;
        Byte[] nArray = new Byte[array.length];
        for (int i = 0; i < array.length; i++) {
            nArray[i] = array[i];
        }
        return nArray;
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
        return ExCollection.filter(Arrays.asList(collection), predicate).toArray((E[]) nAr);
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
        return ExArray.findAll(collection, predicate);
    }

    @ExMethod
    public static int[] filter(int[] collection, IntPredicate predicate) {
        if (collection == null) return null;
        return ExArray.findAll(collection, predicate);
    }

    @ExMethod
    public static double[] filter(double[] collection, DoublePredicate predicate) {
        if (collection == null) return null;
        return ExArray.findAll(collection, predicate);
    }

    @ExMethod
    public static long[] filter(long[] collection, LongPredicate predicate) {
        if (collection == null) return null;
        return ExArray.findAll(collection, predicate);
    }

    @ExMethod
    public static <E, R> R[] map(E[] array, Class<R> clazz, ThrowFunction<E, ? extends R> function) {
        if (array == null) return null;
        final R[] nArray = (R[]) Array.newInstance(clazz, array.length);
        for (int i = 0; i < array.length; i++) {
            try {
                nArray[i] = function.apply(array[i]);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return nArray;
    }

    @ExMethod
    public static <E, R> R[] map(E[] array, ThrowFunction<E, ? extends R> function) {
        if (array == null) return null;
        final R[] nArray = (R[]) new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            try {
                nArray[i] = function.apply(array[i]);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return nArray;
    }

    @ExMethod
    public static <E, R> R[] map(E[] array, R[] tArray, ThrowFunction<E, ? extends R> function) {
        if (array == null) return null;
        final R[] nArray;
        if (tArray.length != array.length) {
            nArray = tArray.expandArray(array.length);
        } else {
            nArray = tArray;
        }
        for (int i = 0; i < array.length; i++) {
            try {
                nArray[i] = function.apply(array[i]);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return nArray;
    }

    @ExMethod
    public static <T> T[] expandArray(T[] originalArray, int newSize) {
        // 检查新大小是否合法
        if (newSize <= originalArray.length) {
            throw new IllegalArgumentException("新大小必须大于原数组长度");
        }
        // 创建一个新数组
        @SuppressWarnings("unchecked")
        T[] newArray = (T[]) new Object[newSize]; // 注意：这里使用了 Object 数组
        // 将原数组的内容复制到新数组
        System.arraycopy(originalArray, 0, newArray, 0, originalArray.length);

        return newArray;
    }


    @ExMethod
    public static <E> int[] mapToInt(E[] array, ThrowFunction<E, Integer> predicate) {
        if (array == null) return null;
        if (array.length == 0) return new int[0];
        int[] nArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            try {
                nArray[i] = predicate.apply(array[i]);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return nArray;
    }

    @ExMethod
    public static <E> double[] mapToDouble(E[] array, ThrowFunction<E, Double> predicate) {
        if (array == null) return null;
        if (array.length == 0) return new double[0];
        double[] nArray = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            try {
                nArray[i] = predicate.apply(array[i]);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return nArray;
    }

    @ExMethod
    public static <E> long[] mapToLong(E[] array, ThrowFunction<E, Long> predicate) {
        if (array == null) return null;
        if (array.length == 0) return new long[0];
        long[] nArray = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            try {
                nArray[i] = predicate.apply(array[i]);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return nArray;
    }

    @ExMethod
    public static <E> E[] createObjectArray(Class<E> clazz, int length) {
        final Object nAr = Array.newInstance(clazz, length);
        return (E[]) nAr;
    }

    @ExMethod
    public static <T> List<T> list(T[] array) {
        if (array == null) return null;
        return ExArray.toList(array);
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
        if (index >= array.length) {
            return null;
        }
        if (index < 0) {
            index = array.length + index;
        }
        return array[index];
    }

    @ExMethod
    public static int size(int[] array) {
        return array.length;
    }

    @ExMethod
    public static int size(long[] array) {
        return array.length;
    }

    @ExMethod
    public static int size(short[] array) {
        return array.length;
    }

    @ExMethod
    public static int size(byte[] array) {
        return array.length;
    }

    @ExMethod
    public static int size(boolean[] array) {
        return array.length;
    }

    @ExMethod
    public static int size(double[] array) {
        return array.length;
    }

    @ExMethod
    public static int size(float[] array) {
        return array.length;
    }

    @ExMethod
    public static <E> int size(E[] array) {
        return array.length;
    }


    @ExMethod
    public static boolean contains(int[] collection, int value) {
        if (collection == null) return false;
        for (int i : collection) {
            if (i == value) return true;
        }
        return false;
    }

    @ExMethod
    public static boolean contains(long[] collection, long value) {
        if (collection == null) return false;
        for (long i : collection) {
            if (i == value) return true;
        }
        return false;
    }

    @ExMethod
    public static boolean contains(byte[] collection, byte value) {
        if (collection == null) return false;
        for (byte i : collection) {
            if (i == value) return true;
        }
        return false;
    }

    @ExMethod
    public static boolean contains(double[] collection, double value) {
        if (collection == null) return false;
        for (double i : collection) {
            if (i == value) return true;
        }
        return false;
    }

    @ExMethod
    public static boolean contains(float[] collection, float value) {
        if (collection == null) return false;
        for (float i : collection) {
            if (i == value) return true;
        }
        return false;
    }

    @ExMethod
    public static <T> boolean contains(T[] collection, T value) {
        if (collection == null) return false;
        for (T i : collection) {
            if (Objects.equals(i, value)) return true;
        }
        return false;
    }


}
