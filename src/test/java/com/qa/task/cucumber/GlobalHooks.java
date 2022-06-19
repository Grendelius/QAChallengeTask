package com.qa.task.cucumber;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.logging.LogType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileInputStream;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
public class GlobalHooks {

    @Autowired
    private List<Hook> hooks;

    @Value("${core.allure.page-source}")
    private boolean pageSourceCapture;

    @Value("${core.allure.browser-logs}")
    private boolean browserLogsCapture;

    @Before
    public void beforeFixture(Scenario scenario) {
        evalFixture(scenario, "@before");
    }

    @After(order = 1)
    public void afterFixture(Scenario scenario) {
        evalFixture(scenario, "@after");
    }

    @After(order = 0)
    public void globalAfter(Scenario scenario) {
        if (WebDriverRunner.hasWebDriverStarted()) {
            if (scenario.isFailed()) {
                try {
                    Allure.addAttachment("Screenshot", "image/png", new FileInputStream(Objects.requireNonNull(Screenshots.takeScreenShotAsFile())), ".png");

                    if (pageSourceCapture) {
                        Allure.addAttachment("Page source", WebDriverRunner.source());
                    }

                    if (browserLogsCapture) {
                        Allure.addAttachment("Browser console logs", getBrowserLogs(LogType.BROWSER));
                        Allure.addAttachment("Browser perf logs", getBrowserLogs(LogType.PERFORMANCE));
                    }
                } catch (Exception e) {
                    log.error("Error take a screenshot", e);
                }
            }

            Selenide.clearBrowserCookies();
            Selenide.clearBrowserLocalStorage();
            Selenide.closeWebDriver();
        }
    }

    public void evalFixture(Scenario scenario, String type) {
        final List<String> actions = scenario.getSourceTagNames().stream()
                .filter(tag -> tag.startsWith(type))
                .flatMap(tag -> Stream.of(tag.split(type + ":")[1].split(";"))).toList();

        if (!actions.isEmpty()) {
            actions.forEach(action -> {
                hooks.forEach(hook -> {
                    hook.accept(scenario, action);
                });
            });
        }
    }

    private String getBrowserLogs(String type) {
        return String.join("\n", Selenide.getWebDriverLogs(type));
    }

}
