package org.ebook.kin.data.repository;

import org.ebook.kin.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User>findByUsername(String username);
}
