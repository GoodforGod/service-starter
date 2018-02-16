package io.starter.service.service.modebased.impl;

import io.starter.service.model.OptionalData;
import io.starter.service.service.modebased.IModelService;
import io.starter.service.util.BasicServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;
import java.util.List;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 16.02.2018
 */
abstract class BasicMongoService<T, ID extends Serializable>
        extends BasicServiceUtils<T, ID>
        implements IModelService<T, ID> {

    final Logger logger = LoggerFactory.getLogger(BasicMongoService.class);

    final MongoRepository<T, ID> repository;

    BasicMongoService(MongoRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public OptionalData<Boolean> exist(ID id) {
        try {
            return (isIdNotValid(id))
                    ? OptionalData.of(false)
                    : OptionalData.ofNullable(repository.exists(id));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return OptionalData.ofException(e);
        }
    }

    @Override
    public OptionalData<T> find(ID id) {
        try {
            return (isIdNotValid(id))
                    ? OptionalData.empty()
                    : OptionalData.ofNullable(repository.findOne(id));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return OptionalData.ofException(e);
        }
    }

    @Override
    public OptionalData<List<T>> findAll() {
        try {
            return OptionalData.ofNullable(repository.findAll());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return OptionalData.ofException(e);
        }
    }

    @Override
    public OptionalData<T> save(T t) {
        try {
            return (isNotValid(t))
                    ? OptionalData.empty()
                    : OptionalData.ofNullable(repository.save(t));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return OptionalData.ofException(e);
        }
    }

    @Override
    public OptionalData<List<T>> save(Iterable<T> t) {
        try {
            return (isNotValid(t))
                    ? OptionalData.empty()
                    : OptionalData.ofNullable(repository.save(t));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return OptionalData.ofException(e);
        }
    }

    @Override
    public OptionalData<Boolean> remove(T t) {
        if (isNotValid(t))
            return OptionalData.empty();

        try {
            repository.delete(t);
            return OptionalData.of(true);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return OptionalData.ofException(e);
        }
    }

    @Override
    public OptionalData<Boolean> remove(ID id) {
        if (isIdNotValid(id))
            return OptionalData.empty();

        try {
            repository.delete(id);
            return OptionalData.of(true);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return OptionalData.ofException(e);
        }
    }
}
