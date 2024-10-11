package com.hackathon.recipientclassification.categorizator;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ICategorizator {

    //Should take mailingThemes and oldCategories (if there is no categories and based on chat gpt answer return categories
    Map<String, Set<String>> getCategories(List<String> mailingThemes, List<String> oldCategories);

    //Get mail category for rates checking
    String getCategory(String mailSubject, List<String> oldCategories);
}