package com.zenith.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, Long> {

    List<User> findAllByStatus(Status status);
}
