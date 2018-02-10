package io.starter.service.util;

import com.sun.istack.internal.Nullable;

import java.util.UUID;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 10.02.2018
 */
public abstract class BasicStringUtils {

    public static boolean isEmpty(String value) {
        return (value == null || value.isEmpty());
    }

    public static boolean isNonEmpty(String value) {
        return !isEmpty(value);
    }

    public static boolean isBlank(String value) {
        return (isEmpty(value) || value.trim().isEmpty());
    }

    public static boolean isNotBlank(String value) {
        return !isBlank(value);
    }

    public static String emptyOrValue(String value) {
        return (value == null) ? "" : value;
    }

    public static String nullOrValue(String value) {
        return (isBlank(value)) ? null : value;
    }

    public static String genUniqueId() {
        return UUID.randomUUID().toString();
    }

    public static String genUniqueIdNoDash() {
        return genUniqueId().replace("-", "");
    }

    @Nullable
    public static String genUniqueIdNoDash(int length) {
        if(length < 1)
            return null;

        final String id = genUniqueIdNoDash();

        return (length >= id.length())
                ? id.substring(0, length)
                : null;
    }
}
