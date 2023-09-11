package zircon.example;

import zircon.ExMethod;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
}
