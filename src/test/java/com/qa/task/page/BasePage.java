package com.qa.task.page;

import com.qa.task.page.header.PageHeader;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$$x;

public class BasePage extends AbstractPage {

    @Override
    protected Map<String, By> controls() {
        return Map.of();
    }

    @Override
    public List<String> hrefs() {
        return $$x("//a").asFixedIterable().stream()
                .filter(link -> link.attr("href") != null)
                .filter(link -> StringUtils.startsWith(link.getAttribute("href"), "https://"))
                .map(link -> link.getAttribute("href"))
                .toList();
    }

    public PageHeader pageHeader() {
        return new PageHeader();
    }

}
