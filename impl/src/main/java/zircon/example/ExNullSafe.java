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
        return runnable.apply(t);
    }

    @ExMethod
    public static <T> boolean testSafe(Predicate<T> runnable, T t) {
        if (runnable == null) {
            return false;
        }
        return runnable.test(t);
    }

    @ExMethod
    public static <T> void acceptSafe(Consumer<T> runnable, T t) {
        if (runnable == null) {
            return;
        }
        runnable.accept(t);
    }

    @ExMethod
    public static <R> R getSafe(Supplier<R> runnable) {
        if (runnable == null) {
            return null;
        }
        return runnable.get();
    }
}
