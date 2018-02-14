package io.starter.service.model;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 10.02.2018
 */
public class ReachResponse<T> extends Response<T> {

    private final boolean isReached;


    private ReachResponse(boolean isSuccessful, boolean isReached, T data) {
        super(isSuccessful, data);
        this.isReached = isReached;
    }



    public static <T> ReachResponse<T> successful() {
        return successful(null);
    }

    public static <T> ReachResponse<T> successful(T data) {
        return new ReachResponse<>(true, true, data);
    }

    public static <T> ReachResponse<T> successfulNotNull(T data) {
        return (data == null)
                ? failed()
                : successful(data);
    }

    public static <T> ReachResponse<T> failedReached() {
        return new ReachResponse<>(false, true, null);
    }

    public static <T> ReachResponse<T> failed() {
        return new ReachResponse<>(false, false, null);
    }



    public static <T> ReachResponse<T> of(T data) {
        return (data != null)
                ? successful(data)
                : failed();
    }

    public static <T> ReachResponse<T> ofReach(T data) {
        return (data != null)
                ? successful(data)
                : failedReached();
    }

    public boolean isReached() {
        return isReached;
    }

    public static <T> boolean isReached(ReachResponse<T> response) {
        return (response != null && response.isReached);
    }

    public static <T> boolean isFailedReached(ReachResponse<T> response) {
        return (response != null && response.isReached() && response.isFailed());
    }
}
