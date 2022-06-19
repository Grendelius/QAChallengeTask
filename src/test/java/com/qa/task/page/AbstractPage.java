package com.qa.task.page;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public abstract class AbstractPage {

    protected abstract Map<String, By> controls();

    public abstract List<String> hrefs();

    public static WebDriver driver() {
        return WebDriverRunner.getWebDriver();
    }

    public By locatorFor(String name) {
        return controls().entrySet().stream()
                .filter(entry -> entry.getKey().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(this.getClass().getSimpleName() + ": unknown control name: " + name)).getValue();
    }

    public void click(String control) {
        $(locatorFor(control)).click();
    }

}
