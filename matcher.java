package com.example.model;

import java.util.List;
import java.util.Map;

public class ConditionMatcher {
    private Map<String, Object> conditions;

    public ConditionMatcher(Map<String, Object> conditions) {
        this.conditions = conditions;
    }

    public boolean matches(Map<String, String> inputs) {
        for (Map.Entry<String, Object> condition : conditions.entrySet()) {
            String key = condition.getKey();
            Object expectedValue = condition.getValue();

            if (expectedValue instanceof String) {
                if (!inputs.getOrDefault(key, "").equalsIgnoreCase((String) expectedValue)) {
                    return false;
                }
            } else if (expectedValue instanceof List) {
                @SuppressWarnings("unchecked")
                List<String> expectedValues = (List<String>) expectedValue;
                boolean matchFound = false;
                for (String value : expectedValues) {
                    if (inputs.getOrDefault(key, "").equalsIgnoreCase(value)) {
                        matchFound = true;
                        break;
                    }
                }
                if (!matchFound) {
                    return false;
                }
            }
        }
        return true;
    }
}
