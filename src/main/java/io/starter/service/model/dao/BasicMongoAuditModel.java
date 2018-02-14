package io.starter.service.model.dao;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 14.02.2018
 */
public abstract class BasicMongoAuditModel<ID extends Serializable> extends BasicMongoModel<ID> {

    @CreatedDate
    protected Date created;

    @LastModifiedDate
    protected Date lastModify;

    public BasicMongoAuditModel(ID id) {
        super(id);
    }

    @Override
    public boolean isNew() {
        return created == null;
    }

    public Date getCreated() {
        return created;
    }

    public Date getLastModify() {
        return lastModify;
    }
}
