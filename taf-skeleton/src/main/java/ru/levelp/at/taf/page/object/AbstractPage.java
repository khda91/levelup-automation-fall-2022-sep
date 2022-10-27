package ru.levelp.at.taf.page.object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.levelp.at.taf.configuration.WebSiteConfiguration;

import java.time.Duration;

public abstract class AbstractPage {

    protected static final WebSiteConfiguration WEB_SITE_CONFIGURATION;
    private static final String WEB_SITE_URL;

    static {
        WEB_SITE_CONFIGURATION = WebSiteConfiguration.getInstance();
        WEB_SITE_URL = WEB_SITE_CONFIGURATION.url();
    }

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofMillis(WEB_SITE_CONFIGURATION.waitTimeout()));
        PageFactory.initElements(driver, this);
    }

    protected void open(String relativeUrl) {
        driver.navigate().to(WEB_SITE_URL + relativeUrl);
    }
}
