import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DropdownTest {

    @Test
    public void dropdown() {
        //задаем опции для нашего драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notfications");
        //определяем браузер с которым хотим работать
        WebDriver driver = new ChromeDriver(options);
        SoftAssert softAssert = new SoftAssert();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select select = new Select(dropdown);
        //проверяем наличие всех элементов
        List<WebElement> dropdownOptions = select.getOptions();

        softAssert.assertEquals(dropdownOptions.get(0).getText(), "Please select an option");
        softAssert.assertEquals(dropdownOptions.get(1).getText(), "Option 1");
        softAssert.assertEquals(dropdownOptions.get(2).getText(), "Option 2");
        // выбраем первый, проверяем, что он выбран
        select.selectByVisibleText("Option 1");

        softAssert.assertEquals(
                select.getFirstSelectedOption().getText(),
                "Option 1",
                "Первый option не выбран"
        );
        // выбраем второй, проверяем, что он выбран
        select.selectByVisibleText("Option 2");

        softAssert.assertEquals(
                select.getFirstSelectedOption().getText(),
                "Option 2",
                "Второй option не выбран"
        );

        softAssert.assertAll();
        driver.quit();
    }
}