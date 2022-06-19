package com.qa.task.cucumber.hook;

import com.qa.task.cucumber.Hook;
import io.cucumber.java.Scenario;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$$x;

@Slf4j
@RequiredArgsConstructor
@Component
public class PrintPageLinksHook implements Hook {

    private final String type = "print-page-links-number";

    @Override
    public void accept(Scenario scenario, String action) {
        if (type.equalsIgnoreCase(action)) {
            log.info("Hook[{}]", action);
            log.info("Links to verify: {}", $$x("//a").asFixedIterable().stream()
                    .filter(link -> link.attr("href") != null)
                    .filter(link -> StringUtils.startsWith(link.getAttribute("href"), "https://"))
                    .map(link -> link.getAttribute("href"))
                    .toList());

        }
    }
}
