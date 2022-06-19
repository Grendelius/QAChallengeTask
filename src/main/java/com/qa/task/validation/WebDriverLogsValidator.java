package com.qa.task.validation;

import com.codeborne.selenide.WebDriverRunner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.logging.LogType;
import org.springframework.stereotype.Component;

import java.util.logging.Level;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Component
@RequiredArgsConstructor
class WebDriverLogsValidator implements ContentDataValidator {


    public void validate() {
        log.info("Validator[{}]", this.getClass().getSimpleName());
        var severeEntries = WebDriverRunner.getWebDriver().manage().logs().get(LogType.BROWSER).getAll()
                .parallelStream()
                .filter(entry -> entry.getLevel() == Level.SEVERE)
                .toList();
        log.info("Number of SEVERE entries: '{}'", severeEntries.size());
        log.debug("Found SEVERE entries: '{}'", severeEntries);
        assertThat(severeEntries)
                .as("The page doesn't contain any JS errors with SEVERE level. Errors list: [%s]", severeEntries)
                .isEmpty();
    }

}
