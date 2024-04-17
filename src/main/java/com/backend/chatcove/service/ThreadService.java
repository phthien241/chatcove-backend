package com.backend.chatcove.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.chatcove.model.Comment;
import com.backend.chatcove.model.Thread;
import com.backend.chatcove.repository.ThreadRepository;

@Service
public class ThreadService {
    @Autowired
    private ThreadRepository threadRepository;

    public List<Thread> getListThread(String category){
        return threadRepository.findByCategory(category);
    }

    public Optional<Thread> getThreadInfo(ObjectId id){
        return threadRepository.findById(id);
    }

    public Thread postThread(String heading, String category, List<Comment> comments){
        Thread thread = new Thread();
        thread.setHeading(heading);
        thread.setCategory(category);
        thread.setComments(comments);
        return threadRepository.save(thread);
    }
}
