package com.hackathon.recipientclassification;
import com.hackathon.recipientclassification.categorizator.ICategorizator;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EngagementService {

    private final Storage storage;
    private final ICategorizator categorizator;
    private final EngagementTypeCalculator engagementTypeCalculator;

    public EngagementService(Storage storage, ICategorizator categorizator, EngagementTypeCalculator engagementTypeCalculator) {

        this.storage = storage;
        this.categorizator = categorizator;
        this.engagementTypeCalculator = engagementTypeCalculator;
    }

    public UserEngagementResponse getEngagementData(String mailingGroupId, String email) {
        // Here we are returning mock data, but you can replace this with real logic
        var fullRate = storage.getFullRate(email);
        UserEngagementResponse response = new UserEngagementResponse();
        response.setEmail(email);
        response.setCommonRate(fullRate.getFullRate());// Mock common rate

        var categoryDtos = fullRate.getRates().stream().map(c -> {
            var dto = new CategoryDTO();
            dto.setCategoryName(c.getCategory());
            dto.setRate(c.getRate());
            return dto;
        }).collect(Collectors.toList());

        response.setCategories(categoryDtos); // Add mock category

        return response;
    }

    public EngagementResponse getEngagementForUsers(EngagementRequest engagementRequest) {
        var category = categorizator.getCategory(engagementRequest.getMailingName(), storage.getAllCategories());
        EngagementResponse engagementResponse = new EngagementResponse();
        engagementResponse.setMailingCategory(category);
        var results = engagementRequest.getEmails().stream().map(
                email -> {
                    var r = storage.getRate(category, email);
                    if (r == null) {
                        return null;
                    }
                    var rate = new EngagementResponse.EmailCategoryRate();
                    rate.setCategoryRate(r.getCategoryRate());
                    rate.setCommonRate(r.getCommonRate());
                    rate.setEmail(email);
                    rate.setEngagementType(engagementTypeCalculator.calculate(r.getCategoryRate()));
                    return rate;
                }
        ).filter(Objects::nonNull).toList();
        engagementResponse.setEmails(results);
        return engagementResponse;
    }
}

