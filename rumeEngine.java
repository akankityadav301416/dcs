public class RuleEngine {
    private List<Rule> rules;

    public RuleEngine(List<Rule> rules) {
        this.rules = rules;
    }

    public EmailConfig evaluate(Map<String, String> inputs) {
        for (Rule rule : rules) {
            if (rule.getConditionMatcher().matches(inputs)) {
                return rule.getConfig();
            }
        }
        throw new IllegalArgumentException("No matching configuration found for the given parameters.");
    }
}

// Helper classes for rules and configurations
public class Rule {
    private ConditionMatcher conditionMatcher;
    private EmailConfig config;

    public Rule(ConditionMatcher conditionMatcher, EmailConfig config) {
        this.conditionMatcher = conditionMatcher;
        this.config = config;
    }

    public ConditionMatcher getConditionMatcher() {
        return conditionMatcher;
    }

    public EmailConfig getConfig() {
        return config;
    }
}

public interface EmailConfig {
    // Define methods to access configuration properties
    String getType();
    String getValue();
    String getAppPerimeter();
    String getSubPerimeter();
    String getEmail();
}
