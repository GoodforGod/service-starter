package io.starter.service.model;

import java.time.temporal.ChronoUnit;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 16.02.2018
 */
public class CacheTime {

    private final long units;
    private final ChronoUnit type;

    private CacheTime(long units, ChronoUnit type) {
        this.type = type;
        this.units = units;
    }

    public static CacheTime ofMinutes(long minutes) {
        return new CacheTime(minutes, ChronoUnit.MINUTES);
    }

    public static CacheTime ofHours(long hours) {
        return new CacheTime(hours, ChronoUnit.HOURS);
    }

    public static CacheTime ofDays(long days) {
        return new CacheTime(days, ChronoUnit.DAYS);
    }

    public long getUnits() {
        return units;
    }

    public ChronoUnit getType() {
        return type;
    }
}
