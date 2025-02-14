package zircon.example;

import zircon.ExMethod;

public class ExComparable {

    @ExMethod
    public static <T extends Comparable<T>> boolean gt(Comparable<T> v1, T v2) {
        return v1.compareTo(v2) > 0;
    }

    @ExMethod
    public static <T extends Comparable<T>> boolean ge(Comparable<T> v1, T v2) {
        return v1.compareTo(v2) >= 0;
    }

    @ExMethod
    public static <T extends Comparable<T>> boolean eq(Comparable<T> v1, T v2) {
        if (v1 == null) {
            return v2 == null;
        }
        return v1.compareTo(v2) == 0;
    }

    @ExMethod
    public static <T extends Comparable<T>> boolean lt(Comparable<T> v1, T v2) {
        return v1.compareTo(v2) < 0;
    }

    @ExMethod
    public static <T extends Comparable<T>> boolean le(Comparable<T> v1, T v2) {
        return v1.compareTo(v2) <= 0;
    }

    @ExMethod
    public static <T extends Comparable<T>> boolean in(Comparable<T> v0, T v1, T v2) {
        return v0.compareTo(v1) >= 0 && v0.compareTo(v2) < 0;
    }
}
