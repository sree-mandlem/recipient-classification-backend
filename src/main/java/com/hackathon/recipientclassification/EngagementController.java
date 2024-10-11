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
        return engagementService.getEngagementForUsers(request);
    }

    @PostMapping("/{mailingGroupId}/engagement/{email}")
    public UserEngagementResponse getEngagementData(
            @PathVariable String mailingGroupId,
            @PathVariable String email
    ) {
        return engagementService.getEngagementData(mailingGroupId, email);
    }
}

