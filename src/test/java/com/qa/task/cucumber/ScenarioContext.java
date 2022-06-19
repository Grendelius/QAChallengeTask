package com.qa.task.cucumber;

import com.qa.task.model.User;
import com.qa.task.page.AbstractPage;
import com.qa.task.page.BasePage;
import io.cucumber.spring.ScenarioScope;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Setter
@Component
@ScenarioScope
public class ScenarioContext {

    private BasePage currentPage;
    private AbstractPage currentDomain;
    private User currentUser;

}
