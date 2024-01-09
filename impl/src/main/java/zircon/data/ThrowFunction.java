package zircon.data;


@FunctionalInterface
public interface ThrowFunction<T, R> {
    R apply(T var1) throws Exception;

    default ThrowFunction<T,R> _this(){
        return this;
    }
}
