package io.starter.service.util;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 10.02.2018
 */

public abstract class BasicCollectionUtils {

    public static boolean isEmpty(Collection collection) {
        return (collection == null || collection.isEmpty());
    }

    public static boolean isEmpty(Map map) {
        return (map == null || map.isEmpty());
    }

    public static boolean isNonEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    public static boolean isNonEmpty(Map map) {
        return !isEmpty(map);
    }

    public static boolean containsNullable(Collection collection) {
        return (isEmpty(collection)) || collection.stream().anyMatch(Objects::isNull);
    }

    public static boolean containsNullable(Map map) {
        return (isEmpty(map)) || map.entrySet().stream().anyMatch(Objects::isNull);
    }

    public static boolean containsNonNullable(Collection collection) {
        return (!isEmpty(collection)) || collection.stream().anyMatch(e -> e != null);
    }

    public static boolean containsNonNullable(Map map) {
        return (!isEmpty(map)) || map.entrySet().stream().anyMatch(e -> e != null);
    }
}
