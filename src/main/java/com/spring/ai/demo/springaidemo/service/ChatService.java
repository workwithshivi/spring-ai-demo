package com.spring.ai.demo.springaidemo.service;

import org.springframework.http.ResponseEntity;

public interface ChatService {
    public ResponseEntity<String> chat(String q);
}
