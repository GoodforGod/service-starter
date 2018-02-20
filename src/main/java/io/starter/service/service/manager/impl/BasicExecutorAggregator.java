package io.starter.service.service.manager.impl;

import io.starter.service.model.OptionalData;
import io.starter.service.service.manager.ICacheAggregator;
import io.starter.service.service.web.IExecutor;

import java.io.Serializable;
import java.util.List;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 16.02.2018
 */
abstract class BasicExecutorAggregator<T, ID extends Serializable>
        extends BasicCacheAggregator<T, ID>
        implements ICacheAggregator<T, ID> {

    final IExecutor<T> executor;

    BasicExecutorAggregator(IExecutor<T> executor) {
        super();
        this.executor = executor;
    }

    BasicExecutorAggregator(IExecutor<T> executor, boolean isConcurrent) {
        super(isConcurrent);
        this.executor = executor;
    }

    @Override
    public OptionalData<Boolean> contains(ID id) {
        final OptionalData<T> s = super.get(id);
        if (s.isException())
            return OptionalData.ofException(s.exception());

        return (s.isPresent())
                ? OptionalData.of(true)
                : OptionalData.empty();
    }

    @Override
    public OptionalData<T> get(ID id) {
        final OptionalData<T> s = super.get(id);
        if (s.isPresent())
            return s;

        final OptionalData<T> execData = executor.process(id);
        if (execData.isException())
            return OptionalData.ofException(execData.exception());

        if (execData.isPresent()) {
            return (store(id, execData.get()).isPresent())
                    ? OptionalData.of(execData.get())
                    : OptionalData.empty();
        }

        return OptionalData.empty();
    }

    @Override
    public OptionalData<List<T>> getAll() {
        return executor.processAll();
    }
}
