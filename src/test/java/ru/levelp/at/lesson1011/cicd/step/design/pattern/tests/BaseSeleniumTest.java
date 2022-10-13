package ru.levelp.at.lesson1011.cicd.step.design.pattern.tests;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.levelp.at.lesson1011.cicd.EnvironmentResourcesGenerator;
import ru.levelp.at.lesson1011.cicd.extension.FailTestExtension;
import ru.levelp.at.lesson1011.cicd.step.design.pattern.steps.IndexPageSteps;
import ru.levelp.at.lesson1011.cicd.step.design.pattern.steps.LoginRegistrationSteps;

@TestInstance(Lifecycle.PER_CLASS)
// @ExtendWith(FailTestExtension.class)
public abstract class BaseSeleniumTest {

    protected WebDriver driver;

    // но так делать не желательно, плохой подход через статику передавать информацию о driver в TestWatcher
    // protected static WebDriver driver;
    protected WebDriverWait wait;
    protected static final Faker FAKER = new Faker();

    protected LoginRegistrationSteps loginRegistrationSteps;
    protected IndexPageSteps indexPageSteps;

    @BeforeEach
    public void setUp(TestReporter testInfo) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        loginRegistrationSteps = new LoginRegistrationSteps(driver);
        indexPageSteps = new IndexPageSteps(driver);

        // context.getStore(Namespace.GLOBAL).put("web-driver", driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @AfterAll
    void afterAll() {
        new EnvironmentResourcesGenerator(((RemoteWebDriver) driver).getCapabilities()).createProperties();
    }
}
