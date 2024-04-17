package com.backend.chatcove.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.chatcove.model.Comment;
import com.backend.chatcove.service.CommentService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PutMapping("/post-comment")
    public ResponseEntity<?> postComment(@RequestBody CommentDto commentDto){
        try{
            Comment comment = commentService.postComment(commentDto.getText(), commentDto.getUserId(), commentDto.getThreadId());
            return ResponseEntity.ok(comment);
        }catch(Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}

class CommentDto {
    private String text;
    private String userId;
    private String threadId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }
}