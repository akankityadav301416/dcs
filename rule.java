package com.example.model;

import java.util.Map;

public class Rule {
    private Map<String, Object> conditions;
    private EmailConfig config;

    // Getters and Setters

    public Map<String, Object> getConditions() {
        return conditions;
    }

    public void setConditions(Map<String, Object> conditions) {
        this.conditions = conditions;
    }

    public EmailConfig getConfig() {
        return config;
    }

    public void setConfig(EmailConfig config) {
        this.config = config;
    }
}
