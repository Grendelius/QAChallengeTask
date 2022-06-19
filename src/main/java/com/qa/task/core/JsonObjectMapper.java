package com.qa.task.core;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = 1L;

    public static ObjectMapper INSTANCE = new ObjectMapper();

    static {
        INSTANCE.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
    }
}
