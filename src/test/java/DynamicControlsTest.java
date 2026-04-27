import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class DynamicControlsTest {

    @Test
    public void chekDynamicControls() {
        //задаем опции для нашего драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        //определяем браузер с которым хотим работать
        WebDriver driver = new ChromeDriver(options);
        SoftAssert softAssert = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        // Нажать Remove
        driver.findElement(By.xpath("//button[text()='Remove']")).click();
        // Дождаться текста “It’s gone!”
        wait.until(ExpectedConditions.textToBe(By.id("message"), "It's gone!"));
        // Проверить, что чекбокса нет
        Assert.assertTrue(driver.findElements(By.id("checkbox")).isEmpty(),
                "Checkbox отображается");
        // Найти input
        WebElement input = driver.findElement(By.xpath("//input[@type='text']"));
        // Проверить, что он disabled
        Assert.assertFalse(input.isEnabled(), "Input disabled");
        // Нажать Enable
        driver.findElement(By.xpath("//button[text()='Enable']")).click();
        // Дождаться текста “It's enabled!”
        wait.until(ExpectedConditions.textToBe(By.id("message"), "It's enabled!"));
        // Проверить, что input стал enabled
        wait.until(ExpectedConditions.elementToBeClickable(input));
        Assert.assertTrue(input.isEnabled(), "Input enabled");

        driver.quit();
    }
}