package com.qa.task.cucumber;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GlobalContext {

    private Map<String, Object> storage = new ConcurrentHashMap<>();

    public void put(String key, Object value) {
        storage.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) storage.get(key);
    }

    public boolean contains(String key) {
        return storage.containsKey(key);
    }

}
