package io.starter.service.service.manager.impl;

import io.starter.service.model.CacheContainer;
import io.starter.service.model.OptionalData;
import io.starter.service.service.manager.ICacheAggregator;
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
abstract class BasicCacheAggregator<T, ID extends Serializable>
        extends BasicServiceUtils<T, ID>
        implements ICacheAggregator<T, ID> {

    final Map<ID, CacheContainer<T>> cache;

    BasicCacheAggregator() {
        this(false);
    }

    BasicCacheAggregator(boolean isConcurrent) {
        this.cache = (isConcurrent)
                ? new ConcurrentHashMap<>()
                : new HashMap<>();
    }

    @Override
    public OptionalData<Boolean> contains(ID id) {
        if (isIdNotValid(id))
            return OptionalData.empty();

        return (cache.containsKey(id))
                ? OptionalData.of(true)
                : OptionalData.empty();
    }

    @Override
    public OptionalData<Boolean> containsCached(ID id) {
        return contains(id);
    }

    @Override
    public OptionalData<Boolean> contains(T t) {
        if (isNotValid(t))
            return OptionalData.empty();

        return OptionalData.ofNullable(
                cache.entrySet().stream()
                        .anyMatch(e -> e.getValue().equals(t))
        );
    }

    @Override
    public boolean isCacheEmpty() {
        return cache.isEmpty();
    }

    @Override
    public OptionalData<T> get(ID id) {
        return (isIdNotValid(id))
                ? OptionalData.empty()
                : OptionalData.ofNullable(retrieve(cache.get(id)));
    }

    @Override
    public OptionalData<T> getCached(ID id) {
        return get(id);
    }

    @Override
    public OptionalData<List<T>> getAll() {
        return OptionalData.ofNullable(
                cache.entrySet().stream()
                        .map(e -> retrieve(e.getValue()))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public OptionalData<List<T>> getAllCached() {
        return getAll();
    }

    @Override
    public OptionalData<T> store(ID id, T t) {
        if (isNotValid(t) || isIdNotValid(id))
            return OptionalData.empty();

        cache.put(id, CacheContainer.of(t));
        return OptionalData.of(t);
    }

    @Override
    public OptionalData<Boolean> removeCached(ID id) {
        if (isIdNotValid(id))
            return OptionalData.empty();

        return (cache.remove(id) != null)
                ? OptionalData.of(true)
                : OptionalData.empty();
    }

    @Override
    public OptionalData<Boolean> removeCached(T t) {
        final ID id = cache.entrySet().stream()
                .filter(e -> e.getValue().equals(t))
                .map(Map.Entry::getKey)
                .findFirst().orElse(null);

        return removeCached(id);
    }

    public abstract void cleanup();
}
