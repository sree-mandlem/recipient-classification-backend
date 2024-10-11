package com.hackathon.recipientclassification;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IParser {

    //create a string which will be sent to chat gpt as propmpt
    String createChatGptRequestForCategorization(List<String> mailingThemes, List<String> oldCategories);

    //parse chatGpt answer and get map like {"category":["mailing theme 1", "mailing theme 2"]}

    Map<String, Set<String>> parseChatGptResponse(String chatGptResponse);

    String createChatGptRequestForCategory(String mailTheme, List<String> oldCategories);
}

