package ru.levelp.at.lesson12.design.patterns.value.object.tests;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.levelp.at.lesson1011.cicd.EnvironmentResourcesGenerator;
import ru.levelp.at.lesson1011.cicd.extension.FailTestExtension;
import ru.levelp.at.lesson12.design.patterns.value.object.context.TestContext;
import ru.levelp.at.lesson12.design.patterns.value.object.steps.IndexPageSteps;
import ru.levelp.at.lesson12.design.patterns.value.object.steps.LoginRegistrationSteps;

import java.time.Duration;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(FailTestExtension.class)
public abstract class BaseSeleniumTest {

    protected WebDriver driver;

    // но так делать не желательно, плохой подход через статику передавать информацию о driver в TestWatcher
    // protected static WebDriver driver;
    protected WebDriverWait wait;
    protected static final Faker FAKER = new Faker();

    protected LoginRegistrationSteps loginRegistrationSteps;
    protected IndexPageSteps indexPageSteps;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        System.out.println("HEADLESS=" + System.getenv("HEADLESS"));
        boolean headless = Boolean.parseBoolean(System.getenv("HEADLESS"));
        if (headless) {
            options.setHeadless(true);
        }
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        loginRegistrationSteps = new LoginRegistrationSteps(driver);
        indexPageSteps = new IndexPageSteps(driver);

        TestContext.getInstance().addObject("driver", driver);
        // context.getStore(Namespace.GLOBAL).put("web-driver", driver);
    }

    @AfterEach
    public void tearDown() {
        System.out.println("======== After");
        driver.quit();
    }

    @AfterAll
    void afterAll() {
        new EnvironmentResourcesGenerator(((RemoteWebDriver) driver).getCapabilities()).createProperties();
    }
}
