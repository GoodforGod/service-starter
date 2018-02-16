package io.starter.service.service.web.error;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 16.02.2018
 */
public class UnknownException extends WebExecutorException {

    public UnknownException() {
        super();
    }

    public UnknownException(String message) {
        super(message);
    }

    public UnknownException(Throwable throwable) {
        super(throwable);
    }
}
