package zircon.example;

import zircon.ExMethod;
import zircon.data.ThrowConsumer;
import zircon.data.ThrowFunction;
import zircon.data.ThrowSupplier;

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
    public static <T,R> R convert(T obj, ThrowFunction<T, R> supplier) {
        try {
            return supplier.apply(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
