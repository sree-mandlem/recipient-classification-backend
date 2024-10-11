package com.hackathon.recipientclassification;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class EngagementService {

    public UserEngagementResponse getEngagementData(String mailingGroupId, String email) {
        // Here we are returning mock data, but you can replace this with real logic
        UserEngagementResponse response = new UserEngagementResponse();
        response.setEmail(email);
        response.setCommonRate(0.5); // Mock common rate

        CategoryDTO category = new CategoryDTO();
        category.setCategoryName("Christmas");
        category.setRate(0.3); // Mock rate

        response.setCategories(Arrays.asList(category)); // Add mock category

        return response;
    }
}

