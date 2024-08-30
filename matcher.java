public class ConditionMatcher {
    private Map<String, String> conditions;

    public ConditionMatcher(Map<String, String> conditions) {
        this.conditions = conditions;
    }

    public boolean matches(Map<String, String> inputs) {
        for (Map.Entry<String, String> condition : conditions.entrySet()) {
            if (!inputs.getOrDefault(condition.getKey(), "").equalsIgnoreCase(condition.getValue())) {
                return false;
            }
        }
        return true;
    }
}
