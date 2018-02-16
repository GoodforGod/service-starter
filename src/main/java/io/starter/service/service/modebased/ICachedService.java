package io.starter.service.service.modebased;

import java.io.Serializable;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 16.02.2018
 */
public interface ICachedService<T, ID extends Serializable> extends IModelService<T, ID> {
    T getFromCache(ID id);

    T removeFromCache(ID id);
}
