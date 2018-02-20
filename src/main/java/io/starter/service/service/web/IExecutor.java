package io.starter.service.service.web;

import io.starter.service.model.OptionalData;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 16.02.2018
 */
public interface IExecutor<T> {

    OptionalData<T> execute(String url, String data);
}
