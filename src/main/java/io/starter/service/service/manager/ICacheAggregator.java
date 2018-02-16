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
    OptionalData<Boolean> contains(ID id);
    OptionalData<Boolean> contains(T t);

    boolean isCacheEmpty();

    OptionalData<T> get(ID id);

    OptionalData<List<T>> getAll();

    OptionalData<T> store(ID id, T t);

    OptionalData<Boolean> remove(ID id);
    OptionalData<Boolean> remove(T t);
}
