package com.qa.task.page;

import com.qa.task.page.navigator.NavigatorPanel;
import org.openqa.selenium.By;

import java.util.Map;

public class BasePage extends AbstractPage {

    @Override
    protected Map<String, By> controls() {
        return Map.of();
    }

    private final NavigatorPanel navigatorPanel = new NavigatorPanel();

    public NavigatorPanel pageHeader() {
        return navigatorPanel;
    }

}
