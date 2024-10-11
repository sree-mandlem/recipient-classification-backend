package com.hackathon.recipientclassification;

import org.springframework.stereotype.Component;

@Component
public class EngagementTypeCalculator {
    public EngagementType calculate(double rate) {
        if (rate >= 0.75) {
            return EngagementType.PATRON;
        } else if (rate >= 0.5){
            return EngagementType.CASUAL;
        } else if (rate >= 0.25) {
            return EngagementType.DISENGAGED;
        } else {
            return EngagementType.NON_APPLICABLE;
        }
    }
}
