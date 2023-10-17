package zircon.example;

import zircon.ExMethod;

public class ExComparable {

    @ExMethod
    public static <T extends Comparable<T>> boolean gt(T v1, T v2) {
        return v1.compareTo(v2) > 0;
    }

    @ExMethod
    public static <T extends Comparable<T>> boolean ge(T v1, T v2) {
        return v1.compareTo(v2) >= 0;
    }

    @ExMethod
    public static <T extends Comparable<T>> boolean eq(T v1, T v2) {
        return v1.compareTo(v2) == 0;
    }

    @ExMethod
    public static <T extends Comparable<T>> boolean lt(T v1, T v2) {
        return v1.compareTo(v2) < 0;
    }

    @ExMethod
    public static <T extends Comparable<T>> boolean le(T v1, T v2) {
        return v1.compareTo(v2) <= 0;
    }

    @ExMethod
    public static <T extends Comparable<T>> boolean in(T v0, T v1, T v2) {
        return v0.compareTo(v1) >= 0 && v0.compareTo(v2) < 0;
    }
}
