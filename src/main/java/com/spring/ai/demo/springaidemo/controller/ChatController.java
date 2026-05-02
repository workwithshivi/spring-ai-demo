package com.spring.ai.demo.springaidemo.controller;


import com.spring.ai.demo.springaidemo.entity.User;
import com.spring.ai.demo.springaidemo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class ChatController {


    @Autowired
    private ChatService chatService;


    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(value = "q", required = true) String q) {

        return chatService.chat(q);

    }

    @GetMapping("/chatArray")
    public ResponseEntity<List<User>> chatArray(@RequestParam(value = "q", required = true) String q) {

        return ResponseEntity.ok(chatService.chatArray(q));

    }

    @GetMapping("/promptTempelate")
    public ResponseEntity<String> promptTempelate(@RequestParam(value = "q", required = true) String q) {

        return ResponseEntity.ok(chatService.promptTempelate(q));

    }

    @GetMapping("/resource-prompt")
    public ResponseEntity<String> resourcePrompts(@RequestParam(value = "topic", required = true) String topic,
                                                  @RequestParam(value = "subTopic", required = true) String subTopic) {

        return ResponseEntity.ok(chatService.resourcePrompts(topic,subTopic));

    }


}
