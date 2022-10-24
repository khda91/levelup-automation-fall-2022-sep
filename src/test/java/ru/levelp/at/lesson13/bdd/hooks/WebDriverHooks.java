package ru.levelp.at.lesson13.bdd.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.levelp.at.lesson13.bdd.context.TestContext;

public class WebDriverHooks {

    private WebDriver driver;

    @BeforeAll
    public static void setUpClass() {
        System.out.println("Before All");
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        TestContext.getInstance().addObject("driver", driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
