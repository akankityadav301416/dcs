package com.example.model;

import java.util.List;
import java.util.Map;

public class RuleEngine {
    private List<Rule> rules;

    public RuleEngine(List<Rule> rules) {
        this.rules = rules;
    }

    public EmailConfig evaluate(Map<String, String> inputs) {
        for (Rule rule : rules) {
            ConditionMatcher matcher = new ConditionMatcher(rule.getConditions());
            if (matcher.matches(inputs)) {
                return rule.getConfig();
            }
        }
        throw new IllegalArgumentException("No matching configuration found for the given parameters.");
    }
}
