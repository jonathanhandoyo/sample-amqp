package com.jonathan.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class CustomMapUtils {

    @SafeVarargs
    public static <K, V> Map<K, V> map(Pair<K, V>... pairs) {
        Map<K, V> map = new HashMap<>();
        if (pairs != null && pairs.length > 0) {
            for (Pair<K, V> pair: pairs) {
                if (pair.getRight() != null) {
                    map.put(pair.getKey(), pair.getValue());
                }
            }
        }
        return map;
    }
}
