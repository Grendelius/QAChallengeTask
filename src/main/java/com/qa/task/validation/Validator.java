package com.qa.task.validation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Validator {

    LOGS("Logs"),
    LINKS("Links");

    private final String title;
}
