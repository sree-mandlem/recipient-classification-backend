package com.hackathon.recipientclassification;

import com.hackathon.recipientclassification.EngagementResponse.EmailCategoryRate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Allow only your React app
@RequestMapping("/api")
public class EngagementController {

    private final EngagementService engagementService;

    public EngagementController(EngagementService engagementService) {
        this.engagementService = engagementService;
    }

    @PostMapping("/engagement")
    public EngagementResponse getEngagementData(@RequestBody EngagementRequest request) {
        // Dummy logic: Split emails into categories based on profileCategory (if provided)
        // In a real-world scenario, you'd fetch or calculate the rates from a database or service

        ArrayList<EmailCategoryRate>  categorizedEmails = new ArrayList<>();

        List<EmailCategoryRate> patronsList = new ArrayList<>();
        List<EmailCategoryRate> casualsList = new ArrayList<>();

        for (String email : request.getEmails()) {
            EmailCategoryRate emailRate = new EmailCategoryRate();
            emailRate.setEmail(email);
            emailRate.setCategoryRate(0.5); // Dummy rate for illustration
            emailRate.setCommonRate(0.5); // Dummy rate for illustration
            emailRate.setEngagementType(request.getProfileCategory());

            if ("patrons".equalsIgnoreCase(request.getProfileCategory())) {
                patronsList.add(emailRate);
            } else {
                casualsList.add(emailRate);
            }
        }

        if (!patronsList.isEmpty()) {
            categorizedEmails.addAll(patronsList);
        }
        if (!casualsList.isEmpty()) {
            categorizedEmails.addAll(casualsList);
        }

        EngagementResponse response = new EngagementResponse();
        response.setEmails(categorizedEmails);
        return response;
    }

    @PostMapping("/{mailingGroupId}/engagement/{email}")
    public UserEngagementResponse getEngagementData(
            @PathVariable String mailingGroupId,
            @PathVariable String email
    ) {
        return engagementService.getEngagementData(mailingGroupId, email);
    }
}

