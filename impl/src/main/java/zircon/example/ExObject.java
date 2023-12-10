package zircon.example;

import zircon.ExMethod;
import zircon.data.*;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ExObject {
    @ExMethod
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    @ExMethod
    public static <T> T nullOr(T obj, T or) {
        return obj == null ? or : obj;
    }

    @ExMethod
    public static <T> T nullOr(T obj, ThrowSupplier<T> or) {
        if (obj == null) {
            try {
                return or.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return obj;
    }


    @ExMethod
    public static <T> T let(T obj, ThrowConsumer<T> supplier) {
        try {
            supplier.accept(obj);
            return obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ExMethod
    public static <T, R> R convert(T obj, ThrowFunction<T, R> supplier) {
        try {
            return supplier.apply(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ExMethod(ex = {Object.class})
    public static <T, R> Function<T, R> $throw(ThrowFunction<T, R> action) {
        return t -> {
            try {
                return action.apply(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @ExMethod(ex = {Object.class})
    public static <T> Supplier<T> $throw(ThrowSupplier<T> action) {
        return () -> {
            try {
                return action.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @ExMethod(ex = {Object.class})
    public static Runnable $throw(ThrowRunnable action) {
        return () -> {
            try {
                action.run();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @ExMethod(ex = {Object.class})
    public static <T> Consumer<T> $throw(ThrowConsumer<T> action) {
        return (t) -> {
            try {
                action.accept(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @ExMethod(ex = {Object.class})
    public static <T> Predicate<T> $throw(ThrowPredicate<T> action) {
        return (t) -> {
            try {
                return action.test(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

}
