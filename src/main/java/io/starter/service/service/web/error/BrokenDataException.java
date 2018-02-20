package io.starter.service.service.web.error;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 16.02.2018
 */
public class BrokenDataException extends WebExecutorException {

    public BrokenDataException() {
        super("JSON model can not be parsed.");
    }

    public BrokenDataException(String message) {
        super(message);
    }

    public BrokenDataException(Throwable throwable) {
        super(throwable);
    }

    public <E> BrokenDataException(E e) {
        this();
    }

    public static  <E> BrokenDataException of(E e) {
        return (e == null)
                ? new BrokenDataException()
                : new BrokenDataException();
    }
}
