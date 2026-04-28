package com.spring.ai.demo.springaidemo.service;

import com.spring.ai.demo.springaidemo.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ChatService {
    public ResponseEntity<String> chat(String q);

    List<User> chatArray(String query);
}
