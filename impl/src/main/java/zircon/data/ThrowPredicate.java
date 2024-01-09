package zircon.data;

public interface ThrowPredicate<T> {
    boolean test(T t) throws Exception;
    default ThrowPredicate<T> _this(){
        return this;
    }
}
