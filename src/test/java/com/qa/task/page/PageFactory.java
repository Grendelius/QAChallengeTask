package com.qa.task.page;

import com.qa.task.page.account.MyAccountPage;
import com.qa.task.page.gender.MenPage;
import com.qa.task.page.gender.WomenPage;
import com.qa.task.page.login.LoginPage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.qa.task.page.Page.*;

@Component
public class PageFactory {

    @Value("${store.page.login}")
    private String loginUrl;

    @Value("${store.page.account}")
    private String myAccountUrl;

    @Value("${store.page.men}")
    private String menUrl;

    @Value("${store.page.women}")
    private String womenUrl;

    private final List<PageMeta> pages = new ArrayList<>();

    @PostConstruct
    public void init() {
        pages.add(new PageMeta(LOGIN.getTitle(), loginUrl, LoginPage::new));
        pages.add(new PageMeta(MY_ACCOUNT.getTitle(), myAccountUrl, MyAccountPage::new));
        pages.add(new PageMeta(MEN.getTitle(), menUrl, MenPage::new));
        pages.add(new PageMeta(WOMEN.getTitle(), womenUrl, WomenPage::new));
    }

    public PageMeta get(String name) {
        return pages.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown page name: " + name));
    }

    @AllArgsConstructor
    @Getter
    public static class PageMeta {

        private String name;
        private String url;
        private Supplier<? extends AbstractPage> constructorSupplier;

    }

}
