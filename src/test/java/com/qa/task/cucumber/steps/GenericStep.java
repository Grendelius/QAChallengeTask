package com.qa.task.cucumber.steps;

import com.qa.task.validation.Validator;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Step;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenericStep extends BaseStep {

    @ParameterType(".*")
    public Validator validator(String validatorName) {
        return Validator.valueOf(validatorName.toUpperCase());
    }

    @Step("user verifies {validator}")
    public void verify(Validator validator) {
        validators.getValidator(validator).validate();
    }

}
