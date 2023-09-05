package zircon.example;

import zircon.ExMethod;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ExCollection {
    @ExMethod
    public static <T> T find(Collection<T> collection, Predicate<T> predicate) {
        return collection.stream().filter(predicate).findFirst().orElse(null);
    }

    @ExMethod
    public static <T> List<T> findAll(Collection<T> collection, Predicate<T> predicate) {
        return collection.stream().filter(predicate).collect(Collectors.toList());
    }
}
