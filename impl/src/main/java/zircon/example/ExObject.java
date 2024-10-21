package zircon.example;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import zircon.ExMethod;
import zircon.data.*;

public class ExObject {
    @ExMethod
    public static boolean isNull(Object obj) {
        return obj == null;
    }


    @ExMethod
    public static void ifNull(Object obj, Runnable runnable) {
        if (obj == null) runnable.run();
    }

    @ExMethod
    public static boolean notNull(Object obj) {
        return obj != null;
    }

    /**
     * @deprecated Use `orElse` instead
     */
    @Deprecated
    @ExMethod
    public static <T> T nullOr(T obj, T or) {
        return obj == null ? or : obj;
    }

    @ExMethod
    public static <T> T orElse(T obj, T or) {
        if (obj == null) return or;
        if (obj.getClass().isArray()) {
            if (Array.getLength(obj) == 0) return or;
        } else if (obj instanceof String) {
            if (((String) obj).length() == 0) return or;
        } else if (obj instanceof Collection) {
            if (((Collection<?>) obj).size() == 0) return or;
        }
        return obj;
    }

    @ExMethod
    public static <T> T orElse(T obj, ThrowSupplier<T> or) {
        try {
            if (obj == null) return or.get();
            if (obj.getClass().isArray()) {
                if (Array.getLength(obj) == 0) return or.get();
            } else if (obj instanceof String) {
                if (((String) obj).length() == 0) return or.get();
            } else if (obj instanceof Collection) {
                if (((Collection<?>) obj).size() == 0) return or.get();
            }
            return obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ExMethod
    public static <T, E extends Exception> T orElseThrow(T obj, Supplier<E> or) throws E {
        if (obj == null) throw or.get();
        if (obj.getClass().isArray()) {
            if (Array.getLength(obj) == 0) throw or.get();
        } else if (obj instanceof String) {
            if (((String) obj).length() == 0) throw or.get();
        } else if (obj instanceof Collection) {
            if (((Collection<?>) obj).size() == 0) throw or.get();
        }
        return obj;
    }

    /**
     * @deprecated Use `orElse` instead
     */
    @Deprecated
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

    /**
     * @deprecated Use `orElseThrow` instead
     */
    @Deprecated
    @ExMethod
    public static <T> T nullOrThrow(T obj, Supplier<Exception> or) {
        if (obj == null) {
            throw (RuntimeException) or.get();
        }
        return obj;
    }


    @ExMethod
    public static <T, R> R let(T obj, ThrowFunction<T, R> supplier) {
        if (obj == null) return null;
        try {
            return supplier.apply(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ExMethod
    public static <T, T2, R> R let(T obj, T2 t2, ThrowBiFunction<T, T2, R> supplier) {
        if (obj == null) return null;
        try {
            return supplier.apply(obj,t2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ExMethod
    public static <T> T with(T obj, ThrowConsumer<? super T> supplier) {
        if (obj == null) return null;
        try {
            supplier.accept(obj);
            return obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ExMethod
    public static <T, F1> T with(T obj, F1 f1, ThrowBiConsumer<? super T, ? super F1> supplier) {
        if (obj == null) return null;
        try {
            supplier.accept(obj, f1);
            return obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ExMethod
    public static <T> T cast(Object object, Class<T> tClass) {
        if (object == null) return null;
        if (tClass == String.class) {
            return (T) String.valueOf(object);
        }
        return tClass.cast(object);
    }

    @ExMethod
    public static <T> boolean isInstanceOf(Object object, Class<T> tClass) {
        return tClass.isInstance(object);
    }

    @ExMethod
    public static <T> boolean isNoInstanceOf(Object object, Class<T> tClass) {
        return !tClass.isInstance(object);
    }

    @ExMethod
    public static <T, R> R convert(T obj, ThrowFunction<T, R> supplier) {
        if (obj == null) return null;
        try {
            return supplier.apply(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ExMethod(cover = true)
    public static boolean equals(Object obj1, Object obj2) {
        return Objects.equals(obj1, obj2);
    }

    @ExMethod
    public static boolean noEquals(Object obj1, Object obj2) {
        return !Objects.equals(obj1, obj2);
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
