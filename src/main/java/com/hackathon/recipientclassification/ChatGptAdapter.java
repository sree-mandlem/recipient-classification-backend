package com.hackathon.recipientclassification;

public class ChatGptAdapter implements IChatGptAdapter {

    @Override
    public String sendRequest(String requestString) {
        return ChatGPTAPIHandler.chatGPT(requestString);
    }
}
