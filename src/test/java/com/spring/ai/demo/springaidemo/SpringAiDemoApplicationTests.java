package com.spring.ai.demo.springaidemo;

import com.spring.ai.demo.springaidemo.service.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringAiDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private ChatService chatService;

    @Test
    void testChatService(){
        System.out.println("********Testing*****");

        var output = chatService.resourcePrompts("push-pull-leg", "bro-split");
        System.out.println(output);
    }
}
