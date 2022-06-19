package com.qa.task.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        features = "classpath:features",
        plugin = {
                "summary",
                "pretty",
                "json:target/cucumber-report.json",
                "html:target/cucumber-report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        })
public class CucumberTest extends AbstractTestNGCucumberTests {
}
