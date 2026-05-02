package com.spring.ai.demo.springaidemo.service;

import com.spring.ai.demo.springaidemo.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ChatService {
     String chat(String q);

    List<User> chatArray(String query);

    String promptTempelateFluentApi(String q);
    String promptTempelate(String q);

    String resourcePrompts(String topic, String subTopic);

    String chatWithAdvisors(String query);
}
