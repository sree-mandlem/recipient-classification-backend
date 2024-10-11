package com.hackathon.recipientclassification;

import com.fasterxml.jackson.annotation.JsonFormat;

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
        @JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT, pattern = "0.00")
        private double categoryRate;
        @JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT, pattern = "0.00")
        private double commonRate;
        private EngagementType engagementType; //NULLABLE

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

        public EngagementType getEngagementType() {
            return engagementType;
        }

        public void setEngagementType(EngagementType engagementType) {
            this.engagementType = engagementType;
        }
    }
}
