package com.backend.chatcove.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.chatcove.model.User;

import java.util.Optional;


public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByUserId(String userId);
    boolean existsByUserId(String userId);
    long count();
}
