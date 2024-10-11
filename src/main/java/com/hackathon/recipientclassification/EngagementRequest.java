package com.hackathon.recipientclassification;

import java.util.List;

public class EngagementRequest {
    private List<String> emails;
    private String mailName;
    private String profileCategory; // This is nullable
    private Long mailingGroupId;

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public String getMailName() {
        return mailName;
    }

    public void setMailName(String mailName) {
        this.mailName = mailName;
    }

    public String getProfileCategory() {
        return profileCategory;
    }

    public void setProfileCategory(String profileCategory) {
        this.profileCategory = profileCategory;
    }

    public Long getMailingGroupId() {
        return mailingGroupId;
    }

    public void setMailingGroupId(Long mailingGroupId) {
        this.mailingGroupId = mailingGroupId;
    }
}
