package com.backend.chatcove.controller;

import org.springframework.web.bind.annotation.RestController;

import com.backend.chatcove.model.User;
import com.backend.chatcove.service.UserService;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get-user/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        return userService.findByUserId(userId).map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        try {
            User user = userService.createUser(userDto.getUserId(), userDto.getAvatar(), userDto.getCreatedAt());
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public static class UserDto {
        private String userId;
        private String avatar;
        private Date createdAt;

        public String getUserId() {
            return this.userId;
        }

        public String getAvatar() {
            return this.avatar;
        }

        public Date getCreatedAt() {
            return this.createdAt;
        }
    }
}
