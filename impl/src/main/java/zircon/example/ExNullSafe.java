package zircon.example;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import zircon.ExMethod;

public class ExNullSafe {
    @ExMethod
    public static void runSafe(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        runnable.run();
    }

    @ExMethod
    public static <T, R> R applySafe(Function<T, R> runnable, T t) {
        if (runnable == null) {
            return null;
        }
        try {
            return runnable.apply(t);
        } catch (Exception e) {
            return null;
        }
    }

    @ExMethod
    public static <T> boolean testSafe(Predicate<T> runnable, T t) {
        if (runnable == null) {
            return false;
        }
        try {
            return runnable.test(t);
        } catch (Exception e) {
            return false;
        }
    }

    @ExMethod
    public static <T> void acceptSafe(Consumer<T> runnable, T t) {
        if (runnable == null) {
            return;
        }
        try {
            runnable.accept(t);
        } catch (Exception e) {
        }
    }

    @ExMethod
    public static <R> R getSafe(Supplier<R> runnable) {
        if (runnable == null) {
            return null;
        }
        try {
            return runnable.get();
        } catch (Exception e) {
            return null;
        }
    }
}
