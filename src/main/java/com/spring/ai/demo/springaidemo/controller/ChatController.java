package com.spring.ai.demo.springaidemo.controller;


import com.spring.ai.demo.springaidemo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ChatController {


    @Autowired
    private ChatService chatService;


    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(value = "q", required = true) String q) {

        return chatService.chat(q);

    }
}
