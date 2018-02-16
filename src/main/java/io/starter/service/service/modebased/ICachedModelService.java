package io.starter.service.service.modebased;

import io.starter.service.model.OptionalData;

import java.io.Serializable;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 16.02.2018
 */
public interface ICachedModelService<T, ID extends Serializable> extends IModelService<T, ID> {
    OptionalData<Boolean> exist(T t);

    OptionalData<T> getCached(ID id);

    OptionalData<T> removeCached(ID id);
}
