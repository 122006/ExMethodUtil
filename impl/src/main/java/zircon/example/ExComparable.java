package zircon.example;

import zircon.ExMethod;

public class ExComparable {

    @ExMethod
    public static <T> boolean gt(Comparable<T> v1, Comparable<T> v2) {
        return v1.compareTo((T) v2) > 0;
    }

    @ExMethod
    public static <T> boolean ge(Comparable<T> v1, Comparable<T> v2) {
        return v1.compareTo((T) v2) >= 0;
    }

    @ExMethod
    public static <T> boolean eq(Comparable<T> v1, Comparable<T> v2) {
        return v1.compareTo((T) v2) == 0;
    }

    @ExMethod
    public static <T> boolean lt(Comparable<T> v1, Comparable<T> v2) {
        return v1.compareTo((T) v2) < 0;
    }

    @ExMethod
    public static <T> boolean le(Comparable<T> v1, Comparable<T> v2) {
        return v1.compareTo((T) v2) <= 0;
    }
}
