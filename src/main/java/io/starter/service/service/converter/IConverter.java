package io.starter.service.service.converter;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 16.02.2018
 */
public interface IConverter<F, T> {
    T convert(F f);
}
