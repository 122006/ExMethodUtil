package zircon.data;

@FunctionalInterface
public interface ThrowBiConsumer<T, T2> {
    void accept(T t, T2 t2) throws Exception;
}
