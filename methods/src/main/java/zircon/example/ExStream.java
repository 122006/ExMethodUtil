package zircon.example;

import zircon.ExMethod;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Set;
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

}
