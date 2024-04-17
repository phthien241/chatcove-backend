package com.backend.chatcove.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.chatcove.model.Comment;
import com.backend.chatcove.model.Thread;
import com.backend.chatcove.model.User;
import com.backend.chatcove.repository.CommentRepository;
import com.backend.chatcove.repository.ThreadRepository;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ThreadRepository threadRepository;

    public Comment postComment(String text, String userId, String threadId) {
        Comment comment = new Comment(text, new User(new ObjectId(userId)), new Thread(new ObjectId(threadId)));
        commentRepository.save(comment);
        Thread thread = threadRepository.findById(new ObjectId(threadId)).orElseThrow(() -> new RuntimeException("Thread not found"));
        thread.getComments().add(comment);
        threadRepository.save(thread);
        return comment;
        
    }

    public List<Comment> find() {
        return commentRepository.findAll();
    }
}
