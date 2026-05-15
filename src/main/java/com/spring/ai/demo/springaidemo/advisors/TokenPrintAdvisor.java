package com.spring.ai.demo.springaidemo.advisors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisorChain;
import reactor.core.publisher.Flux;

@Slf4j
public class TokenPrintAdvisor implements CallAdvisor, StreamAdvisor {



    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        log.info("Token Print Advisor called:");
        var chatClientResponse =callAdvisorChain.nextCall(chatClientRequest);
        log.info("Response received from the model: ", chatClientResponse.chatResponse().getResult().getOutput().getText());

        log.info("Token Consumed: {}\nInput Tokens: {}\nOutput Tokens: {}"
                , chatClientResponse.chatResponse().getMetadata().getUsage().getTotalTokens(),
                chatClientResponse.chatResponse().getMetadata().getUsage().getPromptTokens(),
                chatClientResponse.chatResponse().getMetadata().getUsage().getCompletionTokens());
        return chatClientResponse;
    }

    @Override
    public Flux<ChatClientResponse> adviseStream(ChatClientRequest chatClientRequest, StreamAdvisorChain streamAdvisorChain) {
        return null;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
