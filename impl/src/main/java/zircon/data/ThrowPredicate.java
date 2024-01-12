package zircon.data;

public interface ThrowPredicate<T> {
    boolean test(T t) throws Exception;
}
