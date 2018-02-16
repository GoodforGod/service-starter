package io.starter.service.service.modebased.impl;

import io.starter.service.model.CacheContainer;
import io.starter.service.model.OptionalData;
import io.starter.service.model.dao.BasicMongoModel;
import io.starter.service.service.modebased.ICachedModelService;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 16.02.2018
 */
abstract class BasicMongoCachedService<T extends BasicMongoModel<ID>, ID extends Serializable>
        extends BasicMongoService<T, ID>
        implements ICachedModelService<T, ID> {

    final Map<ID, CacheContainer<T>> cache = new ConcurrentHashMap<>();

    BasicMongoCachedService(MongoRepository<T, ID> repository) {
        super(repository);
    }

    @Override
    public OptionalData<Boolean> exist(ID id) {
        if (isIdNotValid(id))
            return OptionalData.empty();

        return (cache.containsKey(id))
                ? OptionalData.of(true)
                : super.exist(id);
    }

    @Override
    public OptionalData<Boolean> exist(T t) {
        return exist(t.getId());
    }

    @Override
    public OptionalData<T> find(ID id) {
        if (isIdNotValid(id))
            return OptionalData.empty();

        final T t = retrieveFromContainer(cache.get(id));
        return (t != null)
                ? OptionalData.of(t)
                : super.find(id);
    }

    @Override
    public OptionalData<T> save(T t) {
        return super.save(storeInCache(t));
    }

    @Override
    public OptionalData<Boolean> remove(ID id) {
        if (isIdNotValid(id))
            return OptionalData.empty();

        cache.remove(id);
        return super.remove(id);
    }

    T storeInCache(OptionalData<T> data) {
        return storeInCache(data.orElse(null));
    }

    T storeInCache(T data) {
        if (isNotValid(data))
            return null;

        cache.put(data.getId(), new CacheContainer<>(data));
        return data;
    }

    T retrieveFromContainer(CacheContainer<T> container) {
        return (container != null)
                ? container.getData()
                : null;
    }

    @Override
    public OptionalData<T> getCached(ID id) {
        return OptionalData.ofNullable(retrieveFromContainer(cache.get(id)));
    }

    @Override
    public OptionalData<T> removeCached(ID id) {
        return OptionalData.ofNullable(retrieveFromContainer(cache.remove(id)));
    }

    public abstract void cleanup();
}
