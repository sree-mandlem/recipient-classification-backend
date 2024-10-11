package com.hackathon.recipientclassification.categorizator;

import org.springframework.stereotype.Component;

@Component
public class ChatGptAdapter implements IChatGptAdapter {

    @Override
    public String sendRequest(String requestString) {
        return ChatGPTAPIHandler.chatGPT(requestString);
    }
}
