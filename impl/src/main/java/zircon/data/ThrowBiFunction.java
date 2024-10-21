package zircon.data;


@FunctionalInterface
public interface ThrowBiFunction<T, T2, R> {
    R apply(T var1,T2 var2) throws Exception;

}
