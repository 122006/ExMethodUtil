package zircon.data;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface ThrowRunnable {
    void run() throws Exception;

    default ThrowRunnable _this(){
        return this;
    }
}
