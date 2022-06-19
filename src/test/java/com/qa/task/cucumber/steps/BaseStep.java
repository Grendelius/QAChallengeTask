package com.qa.task.cucumber.steps;

import com.qa.task.core.UserData;
import com.qa.task.cucumber.GlobalContext;
import com.qa.task.cucumber.ScenarioContext;
import com.qa.task.page.AbstractPage;
import com.qa.task.page.BasePage;
import com.qa.task.page.PageFactory;
import com.qa.task.validation.GlobalValidators;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class BaseStep {

    @Autowired
    protected GlobalContext globalContext;

    @Autowired
    protected ScenarioContext scenarioContext;

    @Autowired
    protected PageFactory pageFactory;

    @Autowired
    protected UserData userData;

    @Autowired
    protected GlobalValidators validators;

    protected void openPage(String name) {
        PageFactory.PageMeta page = pageFactory.get(name);
        open(page.getUrl());
    }

    @SuppressWarnings("unchecked")
    protected <T extends BasePage> T getPage(String name) {
        return (T) pageFactory.get(name).getConstructorSupplier().get();
    }

    @SuppressWarnings("unchecked")
    protected <T extends BasePage> T currentPage() {
        return (T) scenarioContext.getCurrentPage();
    }

    protected <T extends BasePage> void currentPage(T page) {
        scenarioContext.setCurrentPage(page);
    }

    @SuppressWarnings("unchecked")
    protected <T extends AbstractPage> T currentDomain() {
        return (T) scenarioContext.getCurrentDomain();
    }

    protected <T extends AbstractPage> void currentDomain(T panel) {
        scenarioContext.setCurrentDomain(panel);
    }

    protected <T extends AbstractPage> T currentActive() {
        return currentDomain() != null ? currentDomain() : (T) currentPage();
    }

    protected <T> void save(String dataKey, T value) {
        globalContext.put(dataKey, value);
    }

    protected <T> T load(String dataKey) {
        if (globalContext.contains(dataKey)) {
            return (T) globalContext.get(dataKey);
        } else {
            throw new NullPointerException("Test data entry '%s' is not found in the global context storage".formatted(dataKey));
        }
    }

}
