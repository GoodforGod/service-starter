package io.starter.service.model.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 14.02.2018
 */
@Document
public abstract class BasicMongoModel<ID extends Serializable> implements Persistable<ID>{

    @Id
    protected final ID id;

    public BasicMongoModel(ID id) {
        this.id = id;
    }

    @Override
    public ID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicMongoModel<?> that = (BasicMongoModel<?>) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
