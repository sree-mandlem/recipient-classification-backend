package com.hackathon.recipientclassification;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EngagementType {
    PATRON,
    CASUAL,
    DISENGAGED,
    NON_APPLICABLE;

    @JsonValue
    public String toLowerCase() {
        // Convert the enum value to lowercase with underscores replaced by hyphens (optional)
        return this.name().toLowerCase().replace('_', '-');
    }
}
