package com.spring.ai.demo.springaidemo.service.impl;

import com.spring.ai.demo.springaidemo.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private ChatClient openAIChatClient;
    private ChatClient ollamaChatClient;

    public ChatServiceImpl(OpenAiChatModel openAiChatModel, OllamaChatModel ollamaChatModel) {
        this.openAIChatClient = ChatClient.builder(openAiChatModel).build();
        this.ollamaChatClient = ChatClient.builder(ollamaChatModel).build();
    }

    @Override
    public ResponseEntity<String> chat(String q) {


        var response = ollamaChatClient.prompt(q).call().content();

        return ResponseEntity.ok(response);
    }
}
