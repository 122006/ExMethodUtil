package zircon.data;

@FunctionalInterface
public interface ThrowSupplier<T> {
    T get() throws Exception;

    default ThrowSupplier<T> _this(){
        return this;
    }
}
