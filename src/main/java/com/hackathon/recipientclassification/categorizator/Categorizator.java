package com.hackathon.recipientclassification.categorizator;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class Categorizator implements ICategorizator {

    private final IChatGptAdapter _chatGptAdapter;
    private final IParser _parser;

    public Categorizator(IChatGptAdapter chatGptAdapter, IParser parser) {
        _chatGptAdapter = chatGptAdapter;
        _parser = parser;
    }

    @Override
    public Map<String, Set<String>> getCategories(List<String> mailingThemes, List<String> oldCategories) {
        String propmpt = _parser.createChatGptRequestForCategorization(mailingThemes, oldCategories);
        String answer = _chatGptAdapter.sendRequest(propmpt);
        Map<String, Set<String>> result = _parser.parseChatGptResponse(answer);
        return result;
    }

    @Override
    public String getCategory(String mailSubject, List<String> oldCategories) {
        String propmpt = _parser.createChatGptRequestForCategory(mailSubject, oldCategories);
        String answer = _chatGptAdapter.sendRequest(propmpt);
        return answer.trim().toLowerCase();
    }
}
