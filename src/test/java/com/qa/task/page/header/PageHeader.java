package com.qa.task.page.header;

import com.qa.task.page.AbstractPage;
import com.qa.task.page.gender.MenPage;
import com.qa.task.page.gender.WomenPage;
import com.qa.task.page.login.LoginPage;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class PageHeader {

    private static final By womenSelector = By.xpath("//li[@data-id='21143']");
    private static final By menSelector = By.xpath("//li[@data-id='20795']");

    private static final By MyAccountBtn = By.id("myaccount");

    private final List<Selector<?>> selectors;

    public PageHeader() {
        selectors = List.of(
                new Selector<>("Men", menSelector, new MenPage()),
                new Selector<>("Women", womenSelector, new WomenPage()),
                new Selector<>("My Account", MyAccountBtn, new LoginPage())
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
