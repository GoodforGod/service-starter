package io.starter.service.service.web.error;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 16.02.2018
 */
public class WebExecutorException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Unknown web executor exception exception.";

    WebExecutorException() {
        super(DEFAULT_MESSAGE);
    }

    WebExecutorException(final String message) {
        super(message != null ? message : DEFAULT_MESSAGE);
    }

    WebExecutorException(final Throwable throwable) {
        super(throwable != null ? throwable.getMessage() : DEFAULT_MESSAGE);
    }
}
