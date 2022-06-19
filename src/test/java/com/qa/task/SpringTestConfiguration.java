package com.qa.task;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


import static com.codeborne.selenide.Configuration.*;

@Configuration
public class SpringTestConfiguration {

    public SpringTestConfiguration(Environment environment) {
        browser = environment.getProperty("core.browser.type");
        browserSize = environment.getProperty("core.browser.size");
        headless = Boolean.parseBoolean(environment.getProperty("core.browser.headless"));
        timeout = Integer.parseInt(environment.getProperty("core.browser.timeout", "5000"));
        reopenBrowserOnFail = true;
        savePageSource = false;
        screenshots = false;
        webdriverLogsEnabled = false;
        holdBrowserOpen = true;
        browserCapabilities = getDesiredCaps(browser);
    }

    private DesiredCapabilities getDesiredCaps(String browser) {
        MutableCapabilities opts;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        switch (browser) {
            case "chrome" -> {
                opts = new ChromeOptions();
                ((ChromeOptions) opts).addArguments(
                        "--disable-gpu",
                        "--no-sandbox",
                        "--whitelisted-ips",
                        "--disable-dev-shm-usage",
                        "--allow-insecure-localhost",
                        "--disable-web-security",
                        "--ignore-certificate-errors",
                        "--allow-running-insecure-content",
                        "--start-maximized"
                );
                capabilities.setCapability(ChromeOptions.CAPABILITY, opts);
            }
            case "firefox" -> {
                opts = new FirefoxOptions();
                ((FirefoxOptions) opts).addArguments("");
                capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, opts);
            }
            case "opera" -> {
                opts = new OperaOptions();
                ((OperaOptions) opts).addArguments("");
                capabilities.setCapability(OperaOptions.CAPABILITY, opts);
            }
            case "edge" -> {
                opts = new EdgeOptions();
                capabilities.setCapability(OperaOptions.CAPABILITY, opts);
            }
        }
        return capabilities;
    }

}
