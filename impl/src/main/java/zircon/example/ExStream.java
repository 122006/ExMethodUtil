package zircon.example;

import zircon.ExMethod;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExStream {
    @ExMethod
    public static <T> List<T> list(Stream<T> stream) {
        return stream.collect(Collectors.toList());
    }

    @ExMethod
    public static <T> Set<T> set(Stream<T> stream) {
        return stream.collect(Collectors.toSet());
    }

    @ExMethod
    public static <T> Stream<T> distinctByKey(Stream<T> stream, Function<? super T, ?> keyExtractor) {
        return stream.filter(new Predicate<T>() {
            Map<Object, Boolean> seen = new ConcurrentHashMap<>();

            @Override
            public boolean test(T t) {
                return seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
            }
        });
    }

    @ExMethod
    public static <T> void forEachIndex(Stream<T> stream, BiConsumer<? super T, Integer> function) {
        int[] i = {0};
        stream.forEach(a -> function.accept(a, i[0]++));
    }
    @ExMethod
    public static <T> Stream<T> filterNoNull(Stream<T> stream) {
        return stream.filter(Objects::nonNull);
    }

}
