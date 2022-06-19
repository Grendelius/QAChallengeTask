package com.qa.task.page.login;

import com.qa.task.page.BasePage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {

    private final By usernameEdit = By.xpath("//div[@id = 'qa-login-email']/input[@name = 'login[username]']");
    private final By passwordEdit = By.xpath("//div[@id = 'qa-login-password']/input[@name = 'login[password]']");
    private final By signInBtn = By.xpath("//button[@title= 'Login']");

    public LoginPage() {
        $(signInBtn).shouldBe(visible);
    }

    public void signIn(String username, String password) {
        fillCredentials(username, password);
        $(signInBtn).click();
    }

    public void fillCredentials(String username, String password) {
        $(usernameEdit).setValue(username);
        $(passwordEdit).setValue(password);
    }

}
