package com.qa.task.cucumber;

import io.cucumber.java.Scenario;

import java.util.function.BiConsumer;

public interface Hook extends BiConsumer<Scenario, String> {
}
