package io.starter.service.service.modebased;

import io.starter.service.model.OptionalData;

import java.io.Serializable;
import java.util.List;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 16.02.2018
 */
public interface IModelService<T, ID extends Serializable> {
    OptionalData<Boolean> exist(ID id);

    OptionalData<T> find(ID id);

    OptionalData<List<T>> findAll();

    OptionalData<T> save(T t);
    OptionalData<List<T>> save(List<T> t);

    OptionalData<Boolean> delete(T t);
    OptionalData<Boolean> deleteById(ID id);
}
