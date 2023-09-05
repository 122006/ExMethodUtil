package zircon.example;

import zircon.ExMethod;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExObject {
    @ExMethod
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    @ExMethod
    public static <T> T or(T obj, T or) {
        return obj == null ? or : obj;
    }
}
