package com.hackathon.recipientclassification;

import java.util.*;

public class ChatGptAnswerHandler implements IParser {

    @Override
    public String createChatGptRequestForCategorization(List<String> mailingThemes, List<String> oldCategories) {
        if (oldCategories.isEmpty()) {
            String propmtForFirstRequest = "I have a letter names: %s ." +
                                           "Allocate categories and split list to categories. Answer should have a format:" +
                                           "1. Category name: Letter name 1;;;Letter name 2;;;Letter name 3;;;;;;" +
                                           "2. Category name: Letter name 4;::Letter name 5;;;Letter name 6;;;;;;" +
                                           " Don't add to answer any additional information or words. Don't use new line symbol.";
            String mailingThemesStr = String.join("; ", mailingThemes);
            return String.format(propmtForFirstRequest, mailingThemesStr);
        }
        String propmtForFirstRequest = "I have a letter names: %s ." +
                                       "%sv" +
                                       " I have categories: %s . " +
                                       "Split list of letters in one of these categories and add new categories if needed. Answer should have a format: " +
                                       "1. Category name: Letter name 1;;;Letter name 2;;;Letter name 3;;;;;;" +
                                       "2. Category name: Letter name 4;::Letter name 5;;;Letter name 6;;;;;;" +
                                       " Don't add to answer any additional information or words. Don't use new line symbol.";
        String mailingThemesStr = String.join("; ", mailingThemes);
        String oldCategoriesStr = String.join("; ", oldCategories);
        return String.format(propmtForFirstRequest, mailingThemesStr, oldCategoriesStr);
    }

    private static String extractName(String input) {
        // Use regex to match the number, dot, and spaces, and return the rest of the string
        return input.replaceAll("^\\d+\\.\\s+", "");
    }

    @Override
    public Map<String, Set<String>> parseChatGptResponse(String chatGptResponse) {
        Map<String, Set<String>> categoryMap = new HashMap<>();
        String[] categoryEntries = chatGptResponse.replace("\\n", "").split(";;;;;;");

        for (String entry : categoryEntries) {
            String[] parts = entry.split(":");
            if (parts.length == 2) {
                String category = extractName(parts[0].trim());
                String[] letterNames = parts[1].split(";;;");

                Set<String> lettersSet = new HashSet<>();
                for (String letter : letterNames) {
                    lettersSet.add(letter.trim());
                }
                categoryMap.put(category.toLowerCase(), lettersSet);
            }
        }

        return categoryMap;
    }

    @Override
    public String createChatGptRequestForCategory(String mailTheme, List<String> oldCategories) {
        String prompt = "I have categories: %s. " +
                        "I received a new mail with subject line: %s " +
                        "Define which of existing categories this male belong. If needed you can create new category. " +
                        "Answer ONLY with category name.";
        String oldCategoriesStr = String.join("; ", oldCategories);
        String result = String.format(prompt, oldCategoriesStr, mailTheme);
        return result;
    }
}
