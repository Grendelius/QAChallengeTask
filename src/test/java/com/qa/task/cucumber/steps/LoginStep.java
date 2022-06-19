package com.qa.task.cucumber.steps;

import com.qa.task.model.User;
import com.qa.task.page.login.LoginPage;
import io.cucumber.java.en.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static com.qa.task.page.Page.*;

@Slf4j
public class LoginStep extends BaseStep {

    @Autowired
    protected MainStep mainStep;

    @Step("user submit current user credentials")
    public void submitCredentials() {
        submitCredentials(scenarioContext.getCurrentUser());
    }

    @Step("user submit credentials")
    public void submitCredentials(User user) {
        LoginPage page = currentPage();
        page.signIn(user.getUsername(), user.getPassword());
    }

    @Step("user logged in successfully")
    public void loggedIn() {
        mainStep.navigateToPage(LOGIN.getTitle());
        mainStep.isOnPage(LOGIN.getTitle());
        submitCredentials();
        mainStep.isOnPage(MY_ACCOUNT.getTitle());
    }

}
