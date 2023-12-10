package zircon.data;

@FunctionalInterface
public interface ThrowConsumer<T> {
    void accept(T t) throws Exception;

}
