package io.starter.service.model;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 16.02.2018
 */
public class OptionalData<D> {

    private static final OptionalData<?> EMPTY = new OptionalData<>();

    private final D value;
    private final Exception exception;

    //<editor-fold desc="Constructs">

    private OptionalData() {
        this((D) null);
    }

    private OptionalData(final D t) {
        this.value = t;
        this.exception = null;
    }

    private <E extends Exception> OptionalData(final E exception) {
        this.value = null;
        this.exception = exception;
    }

    //</editor-fold>

    /**
     * Check if value is presented
     * @return is value is present
     */
    public boolean isPresent() {
        return value != null;
    }

    @SuppressWarnings(value = "unchecked")
    public static <D> OptionalData<D> empty() {
        return (OptionalData<D>) EMPTY;
    }

    public D get() {
        if (!isPresent())
            throw new NoSuchElementException("No value is present.");

        return value;
    }

    public D orElse(final D otherValue) {
        return (isPresent())
                ? this.value
                : otherValue;
    }

    public static <D> OptionalData<D> of(final D t) {
        return new OptionalData<>(Objects.requireNonNull(t));
    }

    public static <D> OptionalData<D> ofNullable(final D value) {
        return (value == null)
                ? empty()
                : of(value);
    }

    /**
     * Check if exception is presented
     * @return was exception occurred
     */
    public boolean isException() {
        return exception != null;
    }

    public Exception exception() {
        if (!isException())
            throw new NoSuchElementException("No exception is present.");

        return exception;
    }

    public static <D> OptionalData<D> ofException(final Exception exception) {
        return new OptionalData<>(exception);
    }

    public D orThrow() throws Exception {
        if (!isPresent())
            throw (isException())
                    ? exception
                    : new NoSuchElementException("No exception is present.");

        return value;
    }
}
