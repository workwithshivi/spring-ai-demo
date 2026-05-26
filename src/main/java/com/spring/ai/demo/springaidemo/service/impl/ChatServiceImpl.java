package com.spring.ai.demo.springaidemo.service.impl;

import com.spring.ai.demo.springaidemo.entity.User;
import com.spring.ai.demo.springaidemo.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatClient openAIChatClient;
    private final ChatClient ollamaChatClient;

    @Value("classpath:/prompts/user-message.st")
    private Resource userResource;

    @Value("classpath:/prompts/system-message.st")
    private Resource systemResource;

    public ChatServiceImpl(@Qualifier("openAiChatClient") ChatClient openAIChatClient, @Qualifier("ollamaChatClient") ChatClient ollamaChatClient) {
        this.openAIChatClient = openAIChatClient;
        this.ollamaChatClient = ollamaChatClient;
    }

    @Override
    public String chat(String q) {
        var response = ollamaChatClient.prompt(q).call().content();
        return response;
    }

    @Override
    public List<User> chatArray(String query) {

        Prompt prompt = new Prompt(query);
        return ollamaChatClient.prompt(prompt).call().entity(new ParameterizedTypeReference<List<User>>() {

        });
    }

    @Override
    public String promptTempelateFluentApi(String q) {

        Prompt prompt = new Prompt(q);

        // passing dynamic value in prompt  using fluent api
        String systemString = "you are an ai expert. give to the point ans concise answer with a perfect example so that user will understand the ai concepts. " + "now answer this question: {query}";
        var response = ollamaChatClient.prompt().user(u -> u.text(systemString).param("query", q)).call().content();
        return response;
    }

    @Override
    public String promptTempelate(String q) {


        /*
         * To use a prompt template to generate dynamic prompts,
         * 1. need to create a prompt template and pass a system query
         * 2. Render the prompt template.
         *
         */
        String systemString = "What is the {techName}? Tell me an example of {exampleName}.";

        PromptTemplate template = PromptTemplate.builder().template(systemString).build();
        String renderedMsg = template.render(Map.of("techName", "Spring Ai", "exampleName", "Spring Ai"));

        Prompt prompt = new Prompt(renderedMsg);

        var response = ollamaChatClient.prompt(prompt).call().content();


        // You can define system prompts as well.

        var systemPromptTemplate = SystemPromptTemplate.builder().template("You are an expert Spring AI assistant.").build();
        var systemMessage = systemPromptTemplate.createMessage();
        var userMessage = template.createMessage(Map.of("techName", "Spring Ai", "exampleName", "Spring Ai"));

        Prompt prompt1 = new Prompt(systemMessage, userMessage);

        var response2 = ollamaChatClient.prompt(prompt1).call().content();
        return response2;
    }

    @Override
    public String resourcePrompts(String topic, String subTopic) {

        var response = ollamaChatClient.prompt().system(system -> system.text(systemResource)).user(user -> user.text(userResource).param("topic", topic).param("subTopic", subTopic)).call().content();
        return response;
    }

    // advisors are something that we run/execute before or after any specific tasks
    @Override
    public String chatWithAdvisors(String query) {
        var response = ollamaChatClient.prompt()
                //.advisors(new SimpleLoggerAdvisor()) // advisors
                .system(system -> system.text(systemResource)).user(query).call().content();
        return response;
    }

    @Override
    public Flux<String> streamChat(String q) {

        return ollamaChatClient.prompt().system(system -> system.text(systemResource))
                .user(user-> user.text(userResource).param("topic",q))
                .stream().content();
    }
}
