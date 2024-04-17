package com.backend.chatcove;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.backend.chatcove.repository")
@SpringBootApplication
public class ChatcoveApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatcoveApplication.class, args);
	}

}
