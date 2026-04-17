package com.spring.ai.demo.springaidemo.controller;



import org.jspecify.annotations.Nullable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ChatController {


    private ChatClient chatClient;


    /*
    To initialize this chat client, we cannot use the @Autowired annotation directly. We have to use a constructor method,
    because we will receive a ChatClient. Builder object, not a ChatClient. We have to build that builder and initialize
    it to ChatClient.
    */
    public ChatController(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(value = "q", required = true) String q){


        var response = chatClient.prompt(q).call().content();

        return ResponseEntity.ok(response);

    }
}
