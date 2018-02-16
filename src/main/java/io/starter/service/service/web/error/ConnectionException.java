package io.starter.service.service.web.error;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 16.02.2018
 */
public class ConnectionException extends WebExecutorException {

    public ConnectionException() {
        super();
    }

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(Throwable throwable) {
        super(throwable);
    }
}
