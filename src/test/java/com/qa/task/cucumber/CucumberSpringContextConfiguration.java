package com.qa.task.cucumber;

import com.qa.task.SpringTestApplication;
import com.qa.task.SpringTestConfiguration;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = SpringTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ContextConfiguration(classes = SpringTestConfiguration.class)
public class CucumberSpringContextConfiguration {
}
