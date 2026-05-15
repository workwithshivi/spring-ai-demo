package com.spring.ai.demo.springaidemo.config;

import com.spring.ai.demo.springaidemo.advisors.TokenPrintAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AiModelConfig {

    @Bean(name = "openAiChatClient")
    public ChatClient openAiChatModel(OpenAiChatModel chatModel) {
        return ChatClient.builder(chatModel).build();
    }

    @Bean(name = "ollamaChatClient")
    public ChatClient ollamaChatModel(OllamaChatModel chatModel) {
        return ChatClient.builder(chatModel)
                .defaultAdvisors(new TokenPrintAdvisor(),new SimpleLoggerAdvisor(),new SafeGuardAdvisor(List.of("drugs","booster"))) // setting advisors
                .defaultOptions(OllamaChatOptions.builder() // we can set default properties here as well instead of setting in .properties file
                        .maxTokens(100)
                        .build())
                .build();
    }
}
