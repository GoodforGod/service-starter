package io.starter.service.service.manager.impl;

import io.starter.service.model.CacheContainer;
import io.starter.service.model.OptionalData;
import io.starter.service.service.manager.IModelCacheManager;
import io.starter.service.util.BasicServiceUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static io.starter.service.model.CacheContainer.retrieve;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 16.02.2018
 */
abstract class BasicModelCacheManager<T, ID extends Serializable>
        extends BasicServiceUtils<T, ID>
        implements IModelCacheManager<T, ID> {

    final Map<ID, CacheContainer<T>> cache;

    BasicModelCacheManager() {
        this(false);
    }

    BasicModelCacheManager(boolean isConcurrent) {
        this.cache = (isConcurrent)
                ? new ConcurrentHashMap<>()
                : new HashMap<>();
    }

    @Override
    public OptionalData<Boolean> exist(ID id) {
        if(isIdNotValid(id))
            return OptionalData.empty();

        return (cache.containsKey(id))
                ? OptionalData.of(true)
                : OptionalData.empty();
    }

    @Override
    public OptionalData<Boolean> exist(T t) {
        if(isNotValid(t))
            return OptionalData.empty();

        return OptionalData.ofNullable(
                cache.entrySet().stream()
                        .anyMatch(e -> e.getValue().equals(t))
        );
    }

    @Override
    public OptionalData<T> find(ID id) {
        return (isIdNotValid(id))
            ? OptionalData.empty()
                : OptionalData.ofNullable(retrieve(cache.get(id)));
    }

    @Override
    public OptionalData<List<T>> findAll() {
        return OptionalData.ofNullable(
                cache.entrySet().stream()
                        .map(e -> retrieve(e.getValue()))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public OptionalData<T> save(ID id, T t) {
        if(isNotValid(t) || isIdNotValid(id))
            return OptionalData.empty();

        cache.put(id, CacheContainer.of(t));
        return OptionalData.of(t);
    }

    @Override
    public OptionalData<Boolean> remove(ID id) {
        if(isIdNotValid(id))
            return OptionalData.empty();

        return (cache.remove(id) != null)
                ? OptionalData.of(true)
                : OptionalData.empty();
    }

    @Override
    public OptionalData<Boolean> remove(T t) {
        final ID id = cache.entrySet().stream()
                .filter(e -> e.getValue().equals(t))
                .map(Map.Entry::getKey)
                .findFirst().orElse(null);

        return remove(id);
    }
}
