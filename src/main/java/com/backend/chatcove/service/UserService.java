package com.backend.chatcove.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.chatcove.model.User;
import com.backend.chatcove.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByUserId(String userId){
        return userRepository.findByUserId(userId);
    }

    public User createUser(String userId, String avatar, Date createdAt){
        if(userRepository.existsByUserId(userId)){
            throw new IllegalArgumentException("User already exists");
        }
        long userCount = userRepository.count();
        User user = new User();
        user.setAvatar(avatar);
        user.setUserId(userId);
        user.setUsername("user"+(userCount+1));
        return userRepository.save(user);
    }
}