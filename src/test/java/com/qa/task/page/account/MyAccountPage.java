package com.qa.task.page.account;

import com.codeborne.selenide.Condition;
import com.qa.task.page.BasePage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MyAccountPage extends BasePage {

    private final By welcomeMsg = By.cssSelector("p.hello.hs1");

    public MyAccountPage() {
        $(welcomeMsg).shouldBe(Condition.visible);
    }

}
