package com.qa.task.page;

import com.qa.task.page.header.PageHeader;
import org.openqa.selenium.By;

import java.util.Map;

public class BasePage extends AbstractPage {

    @Override
    protected Map<String, By> controls() {
        return Map.of();
    }

    public PageHeader pageHeader() {
        return new PageHeader();
    }

}
