package io.starter.service.model;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 10.02.2018
 */
public class DataResponse<T> extends Response<T> {

    private final boolean isFound;

    public static <T> DataResponse<T> successful() {
        return new DataResponse<>(true, true, null);
    }

    public static <T> DataResponse<T> successful(T data) {
        return new DataResponse<>(true, true, data);
    }

    public static <T> DataResponse<T> failedFound() {
        return new DataResponse<>(false, true, null);
    }

    public static <T> DataResponse<T> failed() {
        return new DataResponse<>(false, false, null);
    }

    public static <T> DataResponse<T> of(T data) {
        return (data != null)
                ? successful(data)
                : failed();
    }

    public static <T> DataResponse<T> ofFound(T data) {
        return (data != null)
                ? successful(data)
                : failedFound();
    }

    private DataResponse(boolean isSuccessful, boolean isFound, T data) {
        super(isSuccessful, data);
        this.isFound = isFound;
    }

    public boolean isFound() {
        return isFound;
    }

    public static <T> boolean isFound(DataResponse<T> response) {
        return (response != null && response.isFound);
    }

    public static <T> boolean isFailedFound(DataResponse<T> response) {
        return (response != null && response.isFound() && response.isFailed());
    }
}
