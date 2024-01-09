package zircon.data;

@FunctionalInterface
public interface ThrowConsumer<T> {
    void accept(T t) throws Exception;
    default ThrowConsumer<T> _this(){
        return this;
    }
}
