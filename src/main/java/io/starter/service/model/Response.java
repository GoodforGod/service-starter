package io.starter.service.model;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 10.02.2018
 */
public class Response<T> {

    private final boolean isSuccessful;
    private final T data;

    public static <T> Response<T> successful() {
        return new Response<>(true, null);
    }

    public static <T> Response<T> successful(T data) {
        return new Response<>(true, data);
    }

    public static <T> Response<T> failed() {
        return new Response<>(false, null);
    }

    Response(boolean isSuccessful, T data) {
        this.isSuccessful = isSuccessful;
        this.data = data;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public boolean isFailed() {
        return !isSuccessful;
    }

    public static <T> boolean isSuccessful(Response<T> response) {
        return (response != null && response.isSuccessful);
    }

    public static <T> boolean isFailed(Response<T> response) {
        return !isSuccessful(response);
    }

    public T getData() {
        return data;
    }
}
