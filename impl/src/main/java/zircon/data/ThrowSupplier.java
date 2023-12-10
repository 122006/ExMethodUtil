package zircon.data;

@FunctionalInterface
public interface ThrowSupplier<T> {
    T get() throws Exception;

}
