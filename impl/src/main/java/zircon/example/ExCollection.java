package zircon.example;

import zircon.ExMethod;

import java.io.Serializable;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Collections.addAll(collection, es);
        return collection;
    }


    @ExMethod
    public static <E> E find(Collection<E> collection, Predicate<E> predicate) {
        return collection.stream().filter(predicate).findFirst().orElse(null);
    }

    @ExMethod
    public static <E> List<E> findAll(Collection<E> collection, Predicate<E> predicate) {
        return collection.stream().filter(predicate).collect(Collectors.toList());
    }

    @ExMethod
    public static <E> boolean anyMatch(Collection<E> collection, Predicate<E> predicate) {
        return collection.stream().anyMatch(predicate);
    }

    @ExMethod
    public static <E> boolean noneMatch(Collection<E> collection, Predicate<E> predicate) {
        return collection.stream().noneMatch(predicate);
    }

    @ExMethod
    public static <E> List<E> sort(Collection<E> collection) {
        return collection.stream().sorted().collect(Collectors.toList());
    }

    @ExMethod
    public static <E, U extends Comparable<? super U>> List<E> sortBy(Collection<E> collection, Function<E, U> function) {
        return collection.stream().sorted(Comparator.comparing(function)).collect(Collectors.toList());
    }

    @ExMethod
    public static <E> E head(List<E> list) {
        return list.get(0);
    }

    @ExMethod
    public static <E> Optional<E> first(List<E> list) {
        return list.isEmpty() ? Optional.empty() : Optional.ofNullable(list.get(0));
    }

    @ExMethod
    public static <E> Optional<E> last(List<E> list) {
        return list.isEmpty() ? Optional.empty() : Optional.ofNullable(list.get(list.size() - 1));
    }

    @ExMethod
    public static <E> boolean allMatch(Collection<E> collection, Predicate<E> predicate) {
        return collection.stream().allMatch(predicate);
    }
    @ExMethod
    public static <E,M> Map<M,List<E>> groupBy(Collection<E> collection, Function<E, M> function) {
        return collection.stream().collect(Collectors.groupingBy(function));
    }
    @ExMethod
    public static <E> List<E> limit(List<E> collection, int count) {
        if (count>=collection.size()){
            return collection;
        }
        return new ArrayList<>(collection.subList(0, count));
    }
    @ExMethod
    public static <E> List<E> skip(List<E> collection, int count) {
        final ArrayList<E> objects = new ArrayList<>();
        for (int i = count; i < collection.size(); i++) {
            objects.add(collection.get(i));
        }
        return objects;
    }


    @ExMethod
    public static <E> void forEachIndex(List<E> collection, BiConsumer<? super E, Integer> function) {
        for (int i = 0; i < collection.size(); i++) {
            function.accept(collection.get(i), i);
        }
    }
}
