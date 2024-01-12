package zircon.example;

import zircon.ExMethod;
import zircon.data.ThrowFunction;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class ExCollection {
    @ExMethod(ex = {List.class})
    public static <E> List<E> create(E... data) {
        final ArrayList<E> es = new ArrayList<>();
        Collections.addAll(es, data);
        return es;
    }


    public static class ExSet {
        @ExMethod(ex = {Set.class})
        public static <E> Set<E> create(E... data) {
            final HashSet<E> es = new HashSet<>();
            Collections.addAll(es, data);
            return es;
        }
    }

    @ExMethod
    public static <E> Collection<E> addVarargs(Collection<E> collection, E... es) {
        if (collection==null) return null;
        Collections.addAll(collection, es);
        return collection;
    }


    @ExMethod
    public static <E> E find(Collection<E> collection, Predicate<E> predicate) {
        if (collection==null) return null;
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
        if (collection==null) return null;
        Set<E> set = new HashSet<>();
        for (E e : collection) {
            if (predicate.test(e)) {
                set.add(e);
            }
        }
        return set;
    }

    @ExMethod
    public static <E> List<E> filter(List<E> collection, Predicate<E> predicate) {
        if (collection==null) return null;
        return findAll(collection, predicate);
    }

    @ExMethod
    public static <E> Set<E> filter(Set<E> collection, Predicate<E> predicate) {
        if (collection==null) return null;
        return findAll(collection, predicate);
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
    public static <E> boolean noneMatch(Collection<E> collection, Predicate<E> predicate) {
        for (E e : collection) {
            if (predicate.test(e)) {
                return false;
            }
        }
        return true;
    }

    @ExMethod
    public static <K, M> List<M> map(List<K> collection, ThrowFunction<K, M> function) {
        if (collection==null) return null;
        return collection.stream().map(e -> {
            try {
                return function.apply((K) e);
            } catch (Exception ex) {
                throw (RuntimeException) ex;
            }
        }).collect(Collectors.toList());
    }

    @ExMethod
    public static <E> boolean nullOrEmpty(Collection<E> collection) {
        return collection == null || collection.isEmpty();
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
        if (collection==null) return null;
        List<E> list = new ArrayList<>(collection);
        list.sort(null);
        return list;
    }

    @ExMethod
    public static <E, U extends Comparable> List<E> sortBy(Collection<E> collection, Function<E, U> function) {
        if (collection==null) return null;
        return (List<E>) collection.stream().sorted(Comparator.comparing(function)).collect(Collectors.toList());
    }

    @ExMethod
    public static <E> E head(List<E> list) {
        if (list==null) return null;
        return list.isEmpty() ? null : list.get(0);
    }

    @ExMethod
    public static <E> Optional<E> first(List<E> list) {
        if (list==null) return Optional.empty();
        return list.isEmpty() ? Optional.empty() : Optional.ofNullable(list.get(0));
    }


    @ExMethod
    public static <E> Optional<E> last(List<E> list) {
        if (list==null) return Optional.empty();
        return list.isEmpty() ? Optional.empty() : Optional.ofNullable(list.get(list.size() - 1));
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
        if (collection==null) return null;
        Map<M, List<E>> map = new HashMap<>();
        for (E e : collection) {
            map.computeIfAbsent(function.apply(e), k -> new ArrayList<>()).add(e);
        }
        return map;
    }

    @ExMethod
    public static <E> List<E> limit(List<E> collection, int count) {
        if (collection==null) return null;
        if (count >= collection.size()) {
            return collection;
        }
        return new ArrayList<>(collection.subList(0, count));
    }

    @ExMethod
    public static <E> List<E> skip(List<E> collection, int count) {
        if (collection==null) return null;
        final ArrayList<E> objects = new ArrayList<>();
        for (int i = count; i < collection.size(); i++) {
            objects.add(collection.get(i));
        }
        return objects;
    }

    @ExMethod
    public static <E> String join(List<E> collection, String str) {
        if (collection==null) return null;
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < collection.size(); i++) {
            string.append(collection.get(i));
            if (i != collection.size() - 1)
                string.append(str);
        }
        return string.toString();
    }

    @ExMethod
    public static <E> List<E> distinct(List<E> collection) {
        if (collection==null) return null;
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
        if (collection==null) return null;
        return collection.stream().distinctByKey(keyExtractor).collect(Collectors.toList());
    }

    @ExMethod
    public static <E> void forEachIndex(List<E> collection, BiConsumer<? super E, Integer> function) {
        for (int i = 0; i < collection.size(); i++) {
            function.accept(collection.get(i), i);
        }
    }

    @ExMethod
    public static <E, R> List<R> mapIndex(List<E> collection, BiFunction<? super E, Integer, R> function) {
        if (collection==null) return null;
        List<R> re = new ArrayList<>();
        for (int i = 0; i < collection.size(); i++) {
            final R apply = function.apply(collection.get(i), i);
            re.add(apply);
        }
        return re;
    }

}
