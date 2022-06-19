package com.qa.task.cucumber.steps;

import com.qa.task.page.header.PageHeader;
import com.qa.task.validation.Validator;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Step;
import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class MainStep extends BaseStep {

    @Step("current user is {string}")
    public void currentUser(String name) {
        log.info("Use '{}' user", name);
        scenarioContext.setCurrentUser(userData.userForName(name));
    }

    @Step("user navigates to {string} page")
    public void navigateToPage(String name) {
        log.info("Navigate to '{}' page", name);
        openPage(name);
    }

    @Step("user see {string} page")
    public void isOnPage(String name) {
        currentPage(getPage(name));
        currentDomain(null);
    }

    @Step("user do not see {string} page")
    public void isNotOnPage(String name) {
        assertThatThrownBy(() -> getPage(name)).isInstanceOf(AssertionError.class);
    }

    @Step("user click on {string} domain")
    public void openDomain(String name) {
        PageHeader menu = currentPage().pageHeader();
        currentDomain(menu.navigateTo(name));
    }

    @Step("^user click \"(.*)\" (?:control|button|link|radio button|select)$")
    public void clickControl(String name) {
        currentActive().click(name);
    }

}
