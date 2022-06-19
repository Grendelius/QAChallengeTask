package com.qa.task.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GlobalValidators {

    @SuppressWarnings("rawtypes")
    private final List<ContentDataValidator> validators;

    public ContentDataValidator getValidator(Validator validator) {
        switch (validator) {
            case LOGS -> {
                return validators.stream()
                        .filter(v -> v instanceof WebDriverLogsValidator)
                        .findFirst().get();
            }
            case LINKS -> {
                return validators.stream()
                        .filter(v -> v instanceof HttpLinkValidator)
                        .findFirst().get();
            }
            default -> throw new IllegalArgumentException("Unknown validator: " + validator);
        }
    }

}
