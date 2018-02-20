package io.starter.service.service.manager;

import io.starter.service.model.OptionalData;

import java.io.Serializable;
import java.util.List;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 16.02.2018
 */
public interface ICacheAggregator<T, ID extends Serializable> {
    OptionalData<Boolean> contains(T t);
    OptionalData<Boolean> contains(ID id);
    OptionalData<Boolean> containsCached(ID id);

    boolean isCacheEmpty();

    OptionalData<T> get(ID id);
    OptionalData<T> getCached(ID id);

    OptionalData<List<T>> getAll();
    OptionalData<List<T>> getAllCached();

    OptionalData<T> store(ID id, T t);

    OptionalData<Boolean> removeCached(ID id);
    OptionalData<Boolean> removeCached(T t);
}
