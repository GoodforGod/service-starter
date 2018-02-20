package io.starter.service.service.web.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.starter.service.model.OptionalData;
import io.starter.service.service.web.IExecutor;
import io.starter.service.service.web.error.BrokenDataException;
import io.starter.service.service.web.error.ConnectionException;
import io.starter.service.service.web.error.WebExecutorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import static io.starter.service.model.OptionalData.empty;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 16.02.2018
 */
abstract class BasicHttpExecutor<T, E> implements IExecutor<T> {

    static final Logger logger = LoggerFactory.getLogger(BasicHttpExecutor.class);

    private final ObjectMapper mapper = new ObjectMapper();

    private final TypeReference<T> dataReference = new TypeReference<T>() { };
    private final TypeReference<List<T>> dataAllReference = new TypeReference<List<T>>() { };
    private final TypeReference<E> errorReference = new TypeReference<E>() { };

    abstract String buildUrl(Object ... objects);
    abstract String buildUrlAll(Object ... objects);

    @Override
    public OptionalData<T> process(Object ... objects) {
        try {
            final String url = buildUrl(objects);
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

    @Override
    public OptionalData<List<T>> processAll(Object... objects) {
        try {
            final String url = buildUrlAll(objects);
            final HttpResponse<String> response = Unirest.get(url).asString();

            if (response == null)
                return empty();

            return (response.getStatus() == 200)
                    ? handleResponseAll(response)
                    : handleError(response);

        } catch (UnirestException e) {
            logger.warn(e.getMessage());
            return OptionalData.ofException(new ConnectionException(e.getMessage()));
        }
    }

    private OptionalData<T> handleResponse(final HttpResponse<String> response) {
        try {
            return OptionalData.ofNullable(mapper.readValue(response.getBody(), dataReference));
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return OptionalData.ofException(new BrokenDataException(e.getMessage()));
        }
    }

    private OptionalData<List<T>> handleResponseAll(final HttpResponse<String> response) {
        try {
            return OptionalData.ofNullable(mapper.readValue(response.getBody(), dataAllReference));
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return OptionalData.ofException(new BrokenDataException(e.getMessage()));
        }
    }

    private OptionalData handleError(final HttpResponse<String> response) {
        try {
            final E error = mapper.readValue(response.getBody(), errorReference);

            return (error == null)
                    ? OptionalData.ofException(new BrokenDataException())
                    : OptionalData.ofException(convertErrorToException(error));

        } catch (IOException e) {
            logger.warn(e.getMessage());
            return OptionalData.ofException(new BrokenDataException(e.getMessage()));
        }
    }

    WebExecutorException convertErrorToException(final E error) {
        return BrokenDataException.of(error);
    }
}
