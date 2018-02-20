package io.starter.service.service.database.impl;

import io.starter.service.model.CacheContainer;
import io.starter.service.model.OptionalData;
import io.starter.service.model.dao.BasicMongoModel;
import io.starter.service.service.database.IModelCacheService;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static io.starter.service.model.CacheContainer.retrieve;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 16.02.2018
 */
abstract class BasicMongoCacheService<T extends BasicMongoModel<ID>, ID extends Serializable>
        extends BasicMongoService<T, ID>
        implements IModelCacheService<T, ID> {

    final Map<ID, CacheContainer<T>> cache;

    BasicMongoCacheService(MongoRepository<T, ID> repository) {
        this(repository, false);
    }

    BasicMongoCacheService(MongoRepository<T, ID> repository, boolean isConcurrent) {
        super(repository);
        this.cache = (isConcurrent)
                ? new ConcurrentHashMap<>()
                : new HashMap<>();
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

        final T t = retrieve(cache.get(id));
        return (t != null)
                ? OptionalData.of(t)
                : super.find(id);
    }

    @Override
    public OptionalData<T> save(T t) {
        final OptionalData<T> superData = super.save(t);

        return (superData.isPresent())
                ? OptionalData.of(cache(t))
                : superData;
    }

    @Override
    public OptionalData<List<T>> save(Iterable<T> t) {
        final OptionalData<List<T>> superData = super.save(t);

        if (superData.isPresent())
            t.forEach(this::cache);

        return superData;
    }

    @Override
    public OptionalData<Boolean> remove(ID id) {
        if (isIdNotValid(id))
            return OptionalData.empty();

        removeCached(id);
        return super.remove(id);
    }

    @Override
    public OptionalData<Boolean> remove(T t) {
        return remove(t.getId());
    }


    private T cache(T data) {
        if (isNotValid(data))
            return null;

        cache.put(data.getId(), CacheContainer.of(data));
        return data;
    }

    @Override
    public OptionalData<T> getCached(ID id) {
        return OptionalData.ofNullable(retrieve(cache.get(id)));
    }

    @Override
    public OptionalData<T> removeCached(ID id) {
        return OptionalData.ofNullable(retrieve(cache.remove(id)));
    }

    public abstract void cleanup();
}
