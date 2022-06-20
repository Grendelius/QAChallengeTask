package com.qa.task.page.navigator;

import com.qa.task.page.AbstractPage;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class NavigatorPanel {

    private static final By newArrivals = By.xpath("//a[@href = 'New Arrivals']");
    private static final By designers = By.xpath("//a[@href = 'Designers']");

    private final List<Selector<?>> selectors;

    public NavigatorPanel() {
        selectors = List.of(
                new Selector<>("New Arrivals", newArrivals, new NewArrivalsPanel()),
                new Selector<>("Designers", designers, new DesignersPanel())
        );
    }

    @SuppressWarnings("unchecked")
    public <T extends AbstractPage> T navigateTo(String selectorName) {
        var slct = selectors.stream()
                .filter(selector1 -> selector1.name.equalsIgnoreCase(selectorName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown selector: " + selectorName));

        $(slct.locator).click();

        return (T) slct.selectorObj;
    }


    @RequiredArgsConstructor
    private static class Selector<T extends AbstractPage> {

        private final String name;
        private final By locator;
        private final T selectorObj;

    }

}
