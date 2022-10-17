package ru.levelp.at.lesson1011.cicd.extension;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.levelp.at.lesson1011.cicd.step.design.pattern.context.TestContext;

public class FailTestExtension implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("========== Fail");
        // BaseSeleniumTest.driver это если переменная driver public static
        // но так делать не желательно, плохой подход
        // ---
        // правильный подход
        var driver = (WebDriver) TestContext.getInstance().getObject("driver");
        attachScreenshot(driver);
        attachPageSource(driver);
    }

    @Attachment(value = "screenshot", type = "image/png", fileExtension = "png")
    private byte[] attachScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    private void attachPageSource(WebDriver driver) {
        Allure.addAttachment("page source", "text/html", driver.getPageSource(), "html");
    }
}
