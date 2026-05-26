package com.spring.ai.demo.springaidemo.controller;


import com.spring.ai.demo.springaidemo.entity.User;
import com.spring.ai.demo.springaidemo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping
public class ChatController {


    @Autowired
    private ChatService chatService;


    @GetMapping("/chat")
    public String chat(@RequestParam(value = "q", required = true) String q) {

//        return chatService.chat(q);
            return chatService.chatWithAdvisors(q);
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
    @GetMapping("/stream-chat")
    public ResponseEntity<Flux<String>> streamChat(@RequestParam(value = "q", required = true) String q) {
        return ResponseEntity.ok(chatService.streamChat(q));
    }

    /*
    * you have to pass userId in header to chreate new session for new user.
    * */
    @GetMapping("/users-chat")
    public ResponseEntity<Flux<String>> streamChat(@RequestParam(value = "query", required = true) String query
            ,@RequestHeader(value = "userId") String userId) {
        return ResponseEntity.ok(chatService.usersChat(query,userId));
    }

}
