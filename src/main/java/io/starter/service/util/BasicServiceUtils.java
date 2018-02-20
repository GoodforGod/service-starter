package io.starter.service.util;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 10.02.2018
 */
public abstract class BasicServiceUtils<T, ID> {

    protected boolean isIdValid(ID id) {
        return !isIdNotValid(id);
    }

    protected boolean isIdNotValid(ID id) {
        return (id == null);
    }

    protected boolean isValid(T t) {
        return !isNotValid(t);
    }

    protected boolean isNotValid(T t) {
        return (t == null);
    }

    protected boolean isValid(Iterable<T> ts) {
        return !isNotValid(ts);
    }

    protected boolean isNotValid(Iterable<T> ts) {
        return (BasicCollectionUtils.isEmpty(ts));
    }
}
