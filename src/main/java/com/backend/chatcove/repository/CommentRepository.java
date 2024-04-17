package com.backend.chatcove.repository;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.chatcove.model.Comment;

public interface CommentRepository extends MongoRepository<Comment,ObjectId> {

}
