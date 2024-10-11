package com.hackathon.recipientclassification;

import java.util.List;

public class EngagementRequest {
    private List<String> emails;
    private String mailingName;
    private Long mailingGroupId;

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public String getMailingName() {
        return mailingName;
    }

    public void setMailingName(String mailingName) {
        this.mailingName = mailingName;
    }

    public Long getMailingGroupId() {
        return mailingGroupId;
    }

    public void setMailingGroupId(Long mailingGroupId) {
        this.mailingGroupId = mailingGroupId;
    }
}
