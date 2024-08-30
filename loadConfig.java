public static List<Rule> loadRulesFromJson(String fileName) {
    List<Rule> rules = new ArrayList<>();

    // Parse JSON and create Rule objects
    // Example:
    Map<String, Object> jsonMap = parseJsonFile(fileName);
    List<Map<String, Object>> jsonRules = (List<Map<String, Object>>) jsonMap.get("rules");

    for (Map<String, Object> jsonRule : jsonRules) {
        Map<String, String> conditions = (Map<String, String>) jsonRule.get("conditions");
        Map<String, String> configMap = (Map<String, String>) jsonRule.get("config");

        ConditionMatcher matcher = new ConditionMatcher(conditions);
        EmailConfig config = new SimpleEmailConfig(configMap); // SimpleEmailConfig is an implementation of EmailConfig

        rules.add(new Rule(matcher, config));
    }

    return rules;
}

// Helper method to parse JSON
private static Map<String, Object> parseJsonFile(String fileName) {
    // Implement JSON parsing logic here
    return new HashMap<>();
}
