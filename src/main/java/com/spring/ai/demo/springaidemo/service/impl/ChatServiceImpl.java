package com.spring.ai.demo.springaidemo.service.impl;

import com.spring.ai.demo.springaidemo.entity.User;
import com.spring.ai.demo.springaidemo.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    private ChatClient openAIChatClient;
    private ChatClient ollamaChatClient;

    public ChatServiceImpl(@Qualifier("openAiChatClient") ChatClient openAIChatClient, @Qualifier("ollamaChatClient") ChatClient ollamaChatClient) {
        this.openAIChatClient = openAIChatClient;
        this.ollamaChatClient = ollamaChatClient;
    }

    @Override
    public ResponseEntity<String> chat(String q) {


        var response = ollamaChatClient.prompt(q).call().content();

        return ResponseEntity.ok(response);
    }

    @Override
    public List<User> chatArray(String query) {

        Prompt prompt = new Prompt(query);
        return ollamaChatClient.prompt(prompt)
                .call()
                .entity(new ParameterizedTypeReference<List<User>>() {

                });
    }
}
