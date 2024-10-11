package com.hackathon.recipientclassification;

import java.util.Map;
import java.util.List;

public class EngagementResponse {
    private String mailingCategory;
    private List<EmailCategoryRate> emails;

    public List<EmailCategoryRate> getEmails() {
        return emails;
    }

    public void setEmails(List<EmailCategoryRate> emails) {
        this.emails = emails;
    }

    public String getMailingCategory() {
        return mailingCategory;
    }

    public void setMailingCategory(String mailingCategory) {
        this.mailingCategory = mailingCategory;
    }

    public static class EmailCategoryRate {
        private String email;
        private double categoryRate;
        private double commonRate;
        private String engagementType; //NULLABLE

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

        public String getEngagementType() {
            return engagementType;
        }

        public void setEngagementType(String engagementType) {
            this.engagementType = engagementType;
        }
    }
}
