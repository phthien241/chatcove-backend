package com.backend.chatcove.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.chatcove.model.Thread;

import java.util.List;
import java.util.Optional;


public interface ThreadRepository extends MongoRepository<Thread,ObjectId> {
    List<Thread> findByCategory(String category);
    Optional<Thread> findThreadById(ObjectId id);
}
