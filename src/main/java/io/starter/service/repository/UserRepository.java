package io.starter.service.repository;

import io.starter.service.model.dao.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 16.02.2018
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
