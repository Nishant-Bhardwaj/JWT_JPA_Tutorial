package com.nishant.jpa.complete.tutorial.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapperUtil {

    @Autowired
    ObjectMapper objectMapper;

    public JsonNode convertStringToTree(String source) throws JsonProcessingException {
        return objectMapper.readTree(source);
    }
}
