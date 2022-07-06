package com.qa.task.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpHead;
import org.assertj.core.api.Condition;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$$x;
import static org.apache.hc.core5.http.HttpStatus.SC_PERMANENT_REDIRECT;
import static org.apache.hc.core5.http.HttpStatus.SC_SUCCESS;

@Slf4j
@Component
@RequiredArgsConstructor
class HttpLinkValidator implements ContentDataValidator {

    @Value("${core.validator.all-links-to-check}")
    private boolean allLinksToCheck;

    private final HttpClient httpClient;

    public void validate() {
        log.info("Validator[{}] has been executing", this.getClass().getSimpleName());
        var pageLinks = $$x("//a").asFixedIterable().stream().parallel()
                .filter(link -> link.attr("href") != null)
                .filter(link -> StringUtils.startsWith(link.getAttribute("href"), "https://"))
                .map(link -> link.getAttribute("href"))
                .toList();


        log.info("Found <a href=””/> links: '{}'", pageLinks.size());
        SoftAssertions softAssert = new SoftAssertions();

        pageLinks.subList(0, allLinksToCheck ? pageLinks.size() - 1 : 20).parallelStream().forEach(link -> {
            HttpHead head = new HttpHead(link);
            log.info("Executing HEAD request: {}", head.getRequestUri());
            try {
                httpClient.execute(head, response -> {
                    int status = response.getCode();
                    log.info("Got status: {}", status);
                    softAssert
                            .assertThat(status)
                            .as("Request HEAD %s got a positive status", head.getRequestUri())
                            .satisfies(new Condition<>(rc -> rc >= SC_SUCCESS && rc <= SC_PERMANENT_REDIRECT, "status between 200 and 308"));
                    return "";
                });
            } catch (Exception e) {
                log.error("Error HEAD by {}", link, e);
            }
        });
        softAssert.assertAll();
    }


}
