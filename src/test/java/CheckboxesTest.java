import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CheckboxesTest {
    @Test
    public void checkboxes() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");

        WebDriver driver = new ChromeDriver(options);
        SoftAssert softAssert = new SoftAssert();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://the-internet.herokuapp.com/checkboxes");

        // первый чекбокс
        WebElement firstCheckbox =
                driver.findElement(By.cssSelector("input[type='checkbox']:nth-of-type(1)"));

        softAssert.assertFalse(firstCheckbox.isSelected());
        firstCheckbox.click();
        softAssert.assertTrue(firstCheckbox.isSelected());

        // второй чекбокс
        WebElement secondCheckbox =
                driver.findElement(By.cssSelector("input[type='checkbox']:nth-of-type(2)"));

        softAssert.assertTrue(secondCheckbox.isSelected());
        secondCheckbox.click();
        softAssert.assertFalse(secondCheckbox.isSelected());

        softAssert.assertAll();
        driver.quit();
    }
}