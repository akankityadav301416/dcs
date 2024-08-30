package com.example.config;

import com.example.model.Rule;
import com.example.model.EmailConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class RuleConfigLoader {

    public List<Rule> loadRules(String configFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(Paths.get(configFilePath).toFile(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Rule.class));
    }
}
