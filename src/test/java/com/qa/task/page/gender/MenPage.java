package com.qa.task.page.gender;

import com.codeborne.selenide.Condition;
import com.qa.task.page.BasePage;
import org.openqa.selenium.By;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class MenPage extends BasePage {

    private final By mytheresaImg = By.xpath("//img[@alt='mytheresa.com']");

    public MenPage() {
        $(mytheresaImg).shouldBe(Condition.visible);
    }

    @Override
    protected Map<String, By> controls() {
        return Map.of();
    }

}
