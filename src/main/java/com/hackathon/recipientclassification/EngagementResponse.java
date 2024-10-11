package com.hackathon.recipientclassification;

import java.util.Map;
import java.util.List;

public class EngagementResponse {
    private Map<String, List<EmailCategoryRate>> emails;

    public Map<String, List<EmailCategoryRate>> getEmails() {
        return emails;
    }

    public void setEmails(Map<String, List<EmailCategoryRate>> emails) {
        this.emails = emails;
    }

    public static class EmailCategoryRate {
        private String email;
        private double categoryRate;
        private double commonRate;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public double getCategoryRate() {
            return categoryRate;
        }

        public void setCategoryRate(double categoryRate) {
            this.categoryRate = categoryRate;
        }

        public double getCommonRate() {
            return commonRate;
        }

        public void setCommonRate(double commonRate) {
            this.commonRate = commonRate;
        }
    }
}
