package com.hackathon.recipientclassification;

import java.util.List;

public class UserEngagementResponse {

    private String email;
    private double commonRate;
    private List<CategoryDTO> categories;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getCommonRate() {
        return commonRate;
    }

    public void setCommonRate(double commonRate) {
        this.commonRate = commonRate;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }
}
