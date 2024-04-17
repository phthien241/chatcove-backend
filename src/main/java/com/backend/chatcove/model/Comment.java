package com.backend.chatcove.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
public class Comment {
    @Id
    private ObjectId id;

    private String text;

    @DBRef
    private User user;

    @DBRef
    private Thread thread;

    private Date createdAt = new Date();

    public Comment() {
    }

    public Comment(ObjectId id) {
        this.id = id;
    }

    public Comment(String text, User user, Thread thread) {
        this.text = text;
        this.user = user;
        this.thread = thread;
    }

    public String getId() {
        return id.toString();
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // public Thread getThread() {
    // return this.thread;
    // }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id.toHexString() + '\'' + // Changed from id to id.toHexString()
                ", text='" + text + '\'' +
                ", user='" + (user != null ? user.getUsername() : "null") + '\'' + // Example if User has getUsername()
                ", thread='" + (thread != null ? thread.getHeading() : "null") + '\'' + // Example if Thread has
                                                                                        // getHeading()
                ", createdAt=" + createdAt +
                '}';
    }
}
