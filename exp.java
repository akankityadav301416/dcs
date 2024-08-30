public String getB0ExceptionEmails(String gop, String bookingEntity, String source, String bl, String sourceId, String role) {
    Map<String, String> inputs = new HashMap<>();
    inputs.put("gop", gop);
    inputs.put("bookingEntity", bookingEntity);
    inputs.put("source", source);
    inputs.put("sourceId", sourceId);
    inputs.put("bl", bl);

    RuleEngine engine = new RuleEngine(loadRulesFromJson("gbto_config.json"));
    EmailConfig config = engine.evaluate(inputs);

    if ("NON_MARK_ACTORS".equals(config.getType())) {
        return getNonMarkActorsEmails(gop, role, config.getEmail());
    } else if ("BO".equals(config.getType())) {
        return getBOUserEmail(role);
    } else {
        return getUserEmailsFromTypeAndRoleAndPerimeter(
                config.getType(),
                config.getValue(),
                config.getAppPerimeter(),
                config.getSubPerimeter(),
                role
        );
    }
}
