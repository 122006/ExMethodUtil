package zircon.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

import zircon.ExMethod;

public class ExMap {
    @ExMethod
    public static <K, V> HashMap<K, V> filter(Map<K, V> map, BiPredicate<K, V> predicate) {
        HashMap<K, V> hashMap = new HashMap<K, V>();
        for (Map.Entry<K, V> kvEntry : map.entrySet()) {
            if (predicate.test(kvEntry.getKey(), kvEntry.getValue()))
                hashMap.put(kvEntry.getKey(), kvEntry.getValue());
        }
        return hashMap;
    }

    @ExMethod
    public static <K, V> Map<K, V> removeIf(Map<K, V> map, BiPredicate<K, V> predicate) {
        List<Map.Entry<K, V>> removeList = new ArrayList<>();
        for (Map.Entry<K, V> kvEntry : map.entrySet()) {
            if (predicate.test(kvEntry.getKey(), kvEntry.getValue()))
                removeList.add(kvEntry);
        }
        for (Map.Entry<K, V> kvEntry : removeList) {
            map.remove(kvEntry.getKey(), kvEntry.getValue());
        }
        return map;
    }

    @ExMethod
    public static <K, V> Map.Entry<K, V> find(Map<K, V> map, BiPredicate<K, V> predicate) {
        for (Map.Entry<K, V> kvEntry : map.entrySet()) {
            if (predicate.test(kvEntry.getKey(), kvEntry.getValue()))
                return kvEntry;
        }
        return null;
    }

}
