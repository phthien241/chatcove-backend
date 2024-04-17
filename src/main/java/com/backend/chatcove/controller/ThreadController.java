package com.backend.chatcove.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.chatcove.model.Comment;
import com.backend.chatcove.model.Thread;
import com.backend.chatcove.service.ThreadService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/thread")
public class ThreadController {
    @Autowired
    private ThreadService threadService;

    @GetMapping("/get-list-thread/{category}")
    public ResponseEntity<List<ThreadDto>> getListThread(@PathVariable String category) {
        List<Thread> threads = threadService.getListThread(category);
        List<ThreadDto> dtos = threads.stream().map(thread -> {
            ThreadDto dto = new ThreadDto(thread.getId(),thread.getHeading(),thread.getComments(),thread.getCategory());
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }


    @GetMapping("/get-thread-info/{id}")
    public ResponseEntity<Thread> getThreadInfo(@PathVariable String id){
        return threadService.getThreadInfo(new ObjectId(id)).map(thread->ResponseEntity.ok(thread))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/post-thread")
    public ResponseEntity<?> postThread(@RequestBody ThreadDto threadDto){
        try{
            Thread thread = threadService.postThread(threadDto.getHeading(), threadDto.getCategory(), threadDto.getComments());
            return ResponseEntity.ok(thread);
        }catch(Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}

class ThreadDto{
    private String id;
    private String heading;
    private List<Comment> comments;
    private String category;

    public ThreadDto(String id, String heading, List<Comment> comments, String category) {
        this.id = id;
        this.heading = heading;
        this.comments = comments;
        this.category = category;
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setComment(List<Comment> comments) {
        this.comments = comments;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
