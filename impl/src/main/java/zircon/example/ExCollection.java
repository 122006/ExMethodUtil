package zircon.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collectors;

import zircon.ExMethod;
import zircon.data.ThrowFunction;
import zircon.example.ExCollection;
import zircon.example.ExObject;
import zircon.example.ExStream;

public class ExCollection {
    @SafeVarargs
    @ExMethod(ex = {List.class})
    public static <E> List<E> create(E... data) {
        final ArrayList<E> es = new ArrayList<>();
        Collections.addAll(es, data);
        return es;
    }


    public static class ExSet {
        @SafeVarargs
        @ExMethod(ex = {Set.class})
        public static <E> Set<E> create(E... data) {
            final HashSet<E> es = new HashSet<>();
            Collections.addAll(es, data);
            return es;
        }
    }

    @SafeVarargs
    @ExMethod
    public static <E, L extends Collection<E>> L addVarargs(L collection, E... es) {
        if (collection == null) return null;
        Collections.addAll(collection, es);
        return collection;
    }


    @ExMethod
    public static <E, M extends List<E>> List<E> flat(Collection<M> collection) {
        if (collection == null) return null;
        List<E> list = new ArrayList<E>();
        for (List<E> e : collection) {
            list.addAll(e);
        }
        return list;
    }

    @ExMethod
    public static <E> E find(Collection<E> collection, Predicate<E> predicate) {
        if (collection == null) return null;
        for (E e : collection) {
            if (predicate.test(e))
                return e;
        }
        return null;
    }

    @ExMethod
    public static <E> List<E> findAll(List<E> collection, Predicate<E> predicate) {
        List<E> list = new ArrayList<>();
        for (E e : collection) {
            if (predicate.test(e)) {
                list.add(e);
            }
        }
        return list;
    }

    @ExMethod
    public static <E> Set<E> findAll(Set<E> collection, Predicate<E> predicate) {
        if (collection == null) return null;
        Set<E> set = new HashSet<>();
        for (E e : collection) {
            if (predicate.test(e)) {
                set.add(e);
            }
        }
        return set;
    }

    @ExMethod
    public static <E, R> List<E> findAllEquals(List<E> collection, ThrowFunction<E, ? extends R> part, R with) {
        if (collection == null) return null;
        List<E> list = new ArrayList<>();
        for (E e : collection) {
            try {
                final R apply = part.apply(e);
                if (Objects.equals(apply, with)) {
                    list.add(e);
                }
            } catch (Exception ex) {
                throw (RuntimeException) ex;
            }
        }
        return list;
    }

    @ExMethod
    public static <E, R> E findEquals(List<E> collection, ThrowFunction<E, ? extends R> part, R with) {
        if (collection == null) return null;
        for (E e : collection) {
            try {
                final R apply = part.apply(e);
                if (Objects.equals(apply, with)) {
                    return e;
                }
            } catch (Exception ex) {
                throw (RuntimeException) ex;
            }
        }
        return null;
    }

    @ExMethod
    public static <E, R> Set<E> findAllEquals(Set<E> collection, ThrowFunction<E, ? extends R> part, R with) {
        if (collection == null) return null;
        Set<E> set = new HashSet<>();
        for (E e : collection) {
            try {
                final R apply = part.apply(e);
                if (Objects.equals(apply, with)) {
                    set.add(e);
                }
            } catch (Exception ex) {
                throw (RuntimeException) ex;
            }
        }
        return set;
    }

    @ExMethod
    public static <E, R> E findEquals(Set<E> collection, ThrowFunction<E, ? extends R> part, R with) {
        if (collection == null) return null;
        for (E e : collection) {
            try {
                final R apply = part.apply(e);
                if (Objects.equals(apply, with)) {
                    return e;
                }
            } catch (Exception ex) {
                throw (RuntimeException) ex;
            }
        }
        return null;
    }

    @ExMethod
    public static <E, R> List<E> filterContains(Collection<E> collection, List<R> collection2, BiPredicate<E, R> predicate) {
        if (collection == null) return new ArrayList<>();
        if (collection2 == null) return new ArrayList<>();
        List<E> list = new ArrayList<>();
        for (E e : collection) {
            for (R r : collection2) {
                if ((e == null && r != null) || (e != null && r == null)) continue;
                if (predicate.test(e, r)) {
                    list.add(e);
                    break;
                }
            }
        }
        return list;
    }

    @ExMethod
    public static <T> T[] toArray(Collection<T> list) {
        if (list == null) return null;
        final T[] objectArray = (T[]) new Object[list.size()];
        System.arraycopy(list.toArray(), 0, objectArray, 0, list.size());
        return objectArray;
    }

    @ExMethod
    public static <T> T[] toArray(Collection<T> list, Class<T> tClass) {
        if (list == null) return null;
        final T[] objectArray = ExArray.createObjectArray(tClass, list.size());
        System.arraycopy(list.toArray(), 0, objectArray, 0, list.size());
        return objectArray;
    }

    @ExMethod
    public static int[] toIntArray(Collection<Integer> list) {
        if (list == null) return null;
        final int[] objectArray = new int[list.size()];
        final Iterator<Integer> iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            objectArray[i] = iterator.next();
            i++;
        }
        return objectArray;
    }

    @ExMethod
    public static long[] toLongArray(Collection<Long> list) {
        if (list == null) return null;
        final long[] objectArray = new long[list.size()];
        final Iterator<Long> iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            objectArray[i] = iterator.next();
            i++;
        }
        return objectArray;
    }

    @ExMethod
    public static short[] toShortArray(Collection<Short> list) {
        if (list == null) return null;
        final short[] objectArray = new short[list.size()];
        final Iterator<Short> iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            objectArray[i] = iterator.next();
            i++;
        }
        return objectArray;
    }

    @ExMethod
    public static byte[] toByteArray(Collection<Byte> list) {
        if (list == null) return null;
        final byte[] objectArray = new byte[list.size()];
        final Iterator<Byte> iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            objectArray[i] = iterator.next();
            i++;
        }
        return objectArray;
    }

    @ExMethod
    public static String[] toStringArray(Collection<String> list) {
        if (list == null) return null;
        return list.toArray(new String[list.size()]);
    }

    @ExMethod
    public static boolean[] toBooleanArray(Collection<Boolean> list) {
        if (list == null) return null;
        final boolean[] objectArray = new boolean[list.size()];
        final Iterator<Boolean> iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            objectArray[i] = iterator.next();
            i++;
        }
        return objectArray;
    }

    @ExMethod
    public static double[] toDoubleArray(Collection<Double> list) {
        if (list == null) return null;
        final double[] objectArray = new double[list.size()];
        final Iterator<Double> iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            objectArray[i] = iterator.next();
            i++;
        }
        return objectArray;
    }

    @ExMethod
    public static float[] toFloatArray(Collection<Float> list) {
        if (list == null) return null;
        final float[] objectArray = new float[list.size()];
        final Iterator<Float> iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            objectArray[i] = iterator.next();
            i++;
        }
        return objectArray;
    }

    @ExMethod
    public static char[] toCharArray(Collection<Character> list) {
        if (list == null) return null;
        final char[] objectArray = new char[list.size()];
        final Iterator<Character> iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            objectArray[i] = iterator.next();
            i++;
        }
        return objectArray;
    }


    @ExMethod
    public static <T> List<T> filterNoNull(List<T> list) {
        if (list == null) return null;
        return ExCollection.filter(list, Objects::nonNull);
    }

    @ExMethod
    public static <E> List<E> filter(List<E> collection, Predicate<E> predicate) {
        if (collection == null) return null;
        return ExCollection.findAll(collection, predicate);
    }


    @ExMethod
    public static <E> List<E> filterNot(List<E> collection, Predicate<E> predicate) {
        if (collection == null) return null;
        return ExCollection.findAll(collection, predicate.negate());
    }

    @ExMethod
    public static <E, R> List<? extends R> filter(List<E> collection, Class<? super R> clazz) {
        if (collection == null) return null;
        return ExCollection.findAll(collection, a -> a.isInstanceOf(clazz)).map(a -> (R) a);
    }

    @ExMethod
    public static <E, R> Set<? extends R> filter(Set<E> collection, Class<? super R> clazz) {
        if (collection == null) return null;
        return ExCollection.findAll(collection, a -> a.isInstanceOf(clazz)).map(a -> (R) a);
    }

    @ExMethod
    public static <E> Set<E> filter(Set<E> collection, Predicate<E> predicate) {
        if (collection == null) return null;
        return ExCollection.findAll(collection, predicate);
    }

    @ExMethod
    public static <E> Set<E> filterNot(Set<E> collection, Predicate<E> predicate) {
        if (collection == null) return null;
        return ExCollection.findAll(collection, predicate.negate());
    }

    @ExMethod
    public static <E> boolean anyMatch(Collection<E> collection, Predicate<E> predicate) {
        for (E e : collection) {
            if (predicate.test(e)) {
                return true;
            }
        }
        return false;
    }

    @ExMethod
    public static <E, T> T reduce(Collection<E> collection, T identity, BiFunction<T, E, T> accumulator) {
        if (collection.size() == 0) return identity;
        T data = identity;
        for (E e : collection) {
            if (e == null) continue;
            else data = accumulator.apply(data, e);
        }
        return data;
    }

    @ExMethod
    public static <E, T> T reduce(E[] collection, T identity, BiFunction<T, E, T> accumulator) {
        if (collection.size() == 0) return identity;
        T data = identity;
        for (E e : collection) {
            if (e == null) continue;
            else data = accumulator.apply(data, e);
        }
        return data;
    }

    public static class SumBigDecimal {
        @ExMethod
        public static BigDecimal sum(Collection<BigDecimal> collection) {
            if (collection.size() == 0) return BigDecimal.ZERO;
            BigDecimal number = null;
            for (BigDecimal e : collection) {
                if (number == null) number = e;
                else number = number.add(e);
            }
            return number;
        }

        @ExMethod
        public static BigDecimal sum(BigDecimal[] collection) {
            if (collection.size() == 0) return BigDecimal.ZERO;
            BigDecimal number = null;
            for (BigDecimal e : collection) {
                if (number == null) number = e;
                else number = number.add(e);
            }
            return number;
        }

    }

    public static class SumInteger {
        @ExMethod
        public static int sum(Collection<Integer> collection) {
            if (collection.size() == 0) return 0;
            Integer number = null;
            for (Integer e : collection) {
                if (number == null) number = e;
                else number = number + e;
            }
            return number;
        }

        @ExMethod
        public static int sum(Integer[] collection) {
            if (collection.size() == 0) return 0;
            Integer number = null;
            for (Integer e : collection) {
                if (number == null) number = e;
                else number = number + e;
            }
            return number;
        }

        @ExMethod
        public static int sum(int[] collection) {
            if (collection.size() == 0) return 0;
            Integer number = null;
            for (Integer e : collection) {
                if (number == null) number = e;
                else number = number + e;
            }
            return number;
        }
    }

    public static class SumDouble {
        @ExMethod
        public static double sum(Collection<Double> collection) {
            if (collection.size() == 0) return 0;
            Double number = null;
            for (Double e : collection) {
                if (number == null) number = e;
                else number = number + e;
            }
            return number;
        }

        @ExMethod
        public static double sum(Double[] collection) {
            if (collection.size() == 0) return 0;
            Double number = null;
            for (Double e : collection) {
                if (number == null) number = e;
                else number = number + e;
            }
            return number;
        }

        @ExMethod
        public static double sum(double[] collection) {
            if (collection.size() == 0) return 0;
            Double number = null;
            for (Double e : collection) {
                if (number == null) number = e;
                else number = number + e;
            }
            return number;
        }
    }

    public static class SumLong {
        @ExMethod
        public static long sum(Collection<Long> collection) {
            if (collection.size() == 0) return 0;
            Long number = null;
            for (Long e : collection) {
                if (number == null) number = e;
                else number = number + e;
            }
            return number;
        }

        @ExMethod
        public static long sum(Long[] collection) {
            if (collection.size() == 0) return 0;
            Long number = null;
            for (Long e : collection) {
                if (number == null) number = e;
                else number = number + e;
            }
            return number;
        }

        @ExMethod
        public static long sum(long[] collection) {
            if (collection.size() == 0) return 0;
            Long number = null;
            for (Long e : collection) {
                if (number == null) number = e;
                else number = number + e;
            }
            return number;
        }
    }

    @ExMethod
    public static <E> boolean noneMatch(Collection<E> collection, Predicate<E> predicate) {
        for (E e : collection) {
            if (predicate.test(e)) {
                return false;
            }
        }
        return true;
    }

    @ExMethod
    public static <K, M> List<M> map(List<K> collection, ThrowFunction<K, ? extends M> function) {
        if (collection == null) return null;
        List<M> newCollection = new ArrayList<>();
        for (K k : collection) {
            try {
                newCollection.add(function.apply(k));
            } catch (Exception e) {
                throw (RuntimeException) e;
            }
        }
        return newCollection;
    }

    @ExMethod
    public static <K, M> Set<M> map(Set<K> collection, ThrowFunction<K, ? extends M> function) {
        if (collection == null) return null;
        Set<M> newCollection = new HashSet<>();
        for (K k : collection) {
            try {
                newCollection.add(function.apply(k));
            } catch (Exception e) {
                throw (RuntimeException) e;
            }
        }
        return newCollection;
    }

    @ExMethod
    public static <E> boolean nullOrEmpty(Collection<E> collection) {
        return collection == null || collection.isEmpty();
    }

    @ExMethod
    public static <E> boolean noEmpty(Collection<E> collection) {
        return collection != null && !collection.isEmpty();
    }

//    @ExMethod
//    public static <E, M> Set<M> map(Set<E> collection, Function<E, M> function) {
//        return collection.stream().map(e -> {
//            try {
//                return function.apply(e);
//            } catch (Exception ex) {
//                throw (RuntimeException) ex;
//            }
//        }).collect(Collectors.toSet());
//    }

    @ExMethod
    public static <E> List<E> sort(Collection<E> collection) {
        if (collection == null) return null;
        List<E> list = new ArrayList<>(collection);
        list.sort(null);
        return list;
    }

    @ExMethod
    public static <E> List<E> sortBy(Collection<E> collection, Function<E, ? extends Comparable> function) {
        if (collection == null) return null;
        return (List<E>) collection.stream().sorted(Comparator.comparing(function)).collect(Collectors.toList());
    }

    @ExMethod
    public static <E> E head(List<E> list) {
        if (list == null) return null;
        return list.isEmpty() ? null : list.get(0);
    }

    @ExMethod
    public static <E> Optional<E> first(List<E> list) {
        if (list == null) return Optional.empty();
        return list.isEmpty() ? Optional.empty() : Optional.ofNullable(list.get(0));
    }


    @ExMethod
    public static <E> Optional<E> last(List<E> list) {
        if (list == null) return Optional.empty();
        return list.isEmpty() ? Optional.empty() : Optional.ofNullable(list.get(list.size() - 1));
    }

    @ExMethod
    public static <E> List<E> copy2List(Collection<E> collection) {
        if (collection == null) return new ArrayList<>();
        return new ArrayList<>(collection);
    }

    @ExMethod
    public static <E> Set<E> copy2Set(Collection<E> collection) {
        if (collection == null) return new HashSet<>();
        return new HashSet<>(collection);
    }


    @ExMethod
    public static <E> boolean allMatch(Collection<E> collection, Predicate<E> predicate) {
        for (E e : collection) {
            if (!predicate.test(e)) {
                return false;
            }
        }
        return true;
    }

    @ExMethod
    public static <E, M> Map<M, List<E>> groupBy(Collection<E> collection, Function<E, M> function) {
        if (collection == null) return null;
        Map<M, List<E>> map = new HashMap<>();
        for (E e : collection) {
            map.computeIfAbsent(function.apply(e), k -> new ArrayList<>()).add(e);
        }
        return map;
    }

    @ExMethod
    public static <E, M, V> Map<M, V> groupBy(Collection<E> collection, Function<E, M> function, Function<List<E>, V> valueMap) {
        if (collection == null) return null;
        Map<M, List<E>> map = new HashMap<>();
        for (E e : collection) {
            map.computeIfAbsent(function.apply(e), k -> new ArrayList<>()).add(e);
        }
        return ExObject.with(new HashMap<M, V>(), a -> {
            map.forEach((key, value) -> a.put(key, valueMap.apply(value)));
        });
    }

    @ExMethod
    public static <E> List<E> limit(List<E> collection, int count) {
        if (collection == null) return null;
        if (count >= collection.size()) {
            return collection;
        }
        return new ArrayList<>(collection.subList(0, count));
    }

    @ExMethod
    public static <E> List<E> skip(List<E> collection, int count) {
        if (collection == null) return null;
        final ArrayList<E> objects = new ArrayList<>();
        for (int i = count; i < collection.size(); i++) {
            objects.add(collection.get(i));
        }
        return objects;
    }

    @ExMethod
    public static <E> String join(Collection<E> collection, String str) {
        if (collection == null) return null;
        StringBuilder string = new StringBuilder();
        final Iterator<E> iterator = collection.iterator();
        boolean b = iterator.hasNext();
        while (b) {
            final E next = iterator.next();
            string.append(next);
            b = iterator.hasNext();
            if (b) string.append(str);
        }
        return string.toString();
    }

    @ExMethod
    public static <E> List<E> distinct(List<E> collection) {
        if (collection == null) return null;
        List<E> list = new ArrayList<>();
        Set<E> uniqueValues = new HashSet<>();
        for (E e : collection) {
            if (uniqueValues.add(e)) {
                list.add(e);
            }
        }
        return list;
    }

    @ExMethod
    public static <E> List<E> distinct(List<E> collection, Function<? super E, ?> keyExtractor) {
        if (collection == null) return null;
        return ExStream.distinctByKey(collection.stream(), keyExtractor).collect(Collectors.toList());
    }

    @ExMethod
    public static <E> void forEachIndex(Collection<E> collection, BiConsumer<? super E, Integer> function) {
        if (collection == null) return;
        final Iterator<E> iterator = collection.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            function.accept(iterator.next(), i);
            i++;
        }
    }

    @SafeVarargs
    @ExMethod
    public static <E> boolean oneOf(E obj, E... objs) {
        for (E e : objs) {
            if (Objects.equals(obj, e)) return true;
        }
        return false;
    }

    @ExMethod
    public static <E, R> List<R> mapIndex(List<E> collection, BiFunction<? super E, Integer, ? extends R> function) {
        if (collection == null) return null;
        List<R> re = new ArrayList<>();
        for (int i = 0; i < collection.size(); i++) {
            final R apply = function.apply(collection.get(i), i);
            re.add(apply);
        }
        return re;
    }

}
