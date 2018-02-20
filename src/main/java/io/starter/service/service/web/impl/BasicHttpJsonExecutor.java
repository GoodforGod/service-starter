package io.starter.service.service.web.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.starter.service.model.OptionalData;
import io.starter.service.service.web.IExecutor;
import io.starter.service.service.web.error.BrokenDataException;
import io.starter.service.service.web.error.ConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static io.starter.service.model.OptionalData.empty;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 16.02.2018
 */
abstract class BasicHttpJsonExecutor<T, E> implements IExecutor<T> {

    static final Logger logger = LoggerFactory.getLogger(BasicHttpJsonExecutor.class);

    private final ObjectMapper mapper = new ObjectMapper();

    private final Class<T> mappedClass;
    private final Class<E> errorClass;

    BasicHttpJsonExecutor(Class<T> valueClass, Class<E> errorClass) {
        this.mappedClass = valueClass;
        this.errorClass = errorClass;
    }

    @Override
    public OptionalData<T> execute(final String url, final String data) {
        try {
            final HttpResponse<String> response = Unirest.get(url).asString();

            if (response == null)
                return empty();

            return (response.getStatus() == 200)
                    ? handleResponse(response)
                    : handleError(response);

        } catch (UnirestException e) {
            logger.warn(e.getMessage());
            return OptionalData.ofException(new ConnectionException(e.getMessage()));
        }
    }

    private OptionalData<T> handleResponse(final HttpResponse<String> response) {
        try {
            return OptionalData.ofNullable(mapper.readValue(response.getBody(), mappedClass));
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return OptionalData.ofException(new BrokenDataException(e.getMessage()));
        }
    }

    private OptionalData<T> handleError(final HttpResponse<String> response) {
        try {
            final E error = mapper.readValue(response.getBody(), errorClass);

            return (error == null)
                    ? OptionalData.ofException(new BrokenDataException())
                    : OptionalData.ofException(BrokenDataException.of(error));

        } catch (IOException e) {
            logger.warn(e.getMessage());
            return OptionalData.ofException(new BrokenDataException(e.getMessage()));
        }
    }
}
