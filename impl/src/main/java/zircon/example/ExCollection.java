package zircon.example;

import zircon.ExMethod;

import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExCollection {
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
    public static <E> boolean allMatch(Collection<E> collection, Predicate<E> predicate) {
        return collection.stream().allMatch(predicate);
    }

    @ExMethod
    public static <E> void forEachIndex(List<E> collection, BiConsumer<? super E, Integer> function) {
        for (int i = 0; i < collection.size(); i++) {
            function.accept(collection.get(i), i);
        }
    }
}
