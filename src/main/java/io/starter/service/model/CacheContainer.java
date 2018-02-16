package io.starter.service.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 16.02.2018
 */
public class CacheContainer<T> {

    private final LocalDateTime created;
    private final T data;

    public CacheContainer(T data) {
        this.data = data;
        this.created = LocalDateTime.now();
    }

    public static <T> CacheContainer<T> of(T data) {
        return (data != null)
                ? new CacheContainer<>(data)
                : null;
    }

    public T getData() {
        return data;
    }

    public LocalDateTime getCreated() {
        return created;
    }



    public long aliveWithinDays(LocalDateTime dateTime) {
        return LocalDateTime.from(created).until(dateTime, ChronoUnit.DAYS);
    }

    public long aliveWithinHours(LocalDateTime dateTime) {
        return LocalDateTime.from(created).until(dateTime, ChronoUnit.HOURS);
    }

    public long aliveWithinMinutes(LocalDateTime dateTime) {
        return LocalDateTime.from(created).until(dateTime, ChronoUnit.MINUTES);
    }



    public long getHourFromCreation() {
        return LocalDateTime.from(created).until(LocalDateTime.now(), ChronoUnit.HOURS);
    }

    public boolean isAliveMoreThen(long hours) {
        return (getHourFromCreation() < hours);
    }

    public boolean isAliveMoreThen(int hours) {
        return isAliveMoreThen((long) hours);
    }
}
