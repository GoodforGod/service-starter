package io.starter.service.service.web;

import io.starter.service.model.OptionalData;

import java.util.List;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 16.02.2018
 */
public interface IExecutor<T> {

    OptionalData<T> process(Object ... objects);

    OptionalData<List<T>> processAll(Object ... objects);
}
