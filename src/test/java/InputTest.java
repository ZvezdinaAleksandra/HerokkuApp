import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class InputTest {
    @Test
    public void input() {
        //задаем опции для нашего драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notfications");

        //определяем браузер с которым хотим работать
        WebDriver driver = new ChromeDriver(options);
        SoftAssert softAssert = new SoftAssert();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://the-internet.herokuapp.com/inputs");


    //  вводим текст
        driver.findElement(By.tagName("input")).sendKeys("Hi,bro!");

    // проверяем что поле не приняло текст
        String value = driver.findElement(By.tagName("input")).getAttribute("value");
        softAssert.assertTrue(value.isEmpty());
    // очищаем значение в инпуте
        driver.findElement(By.tagName("input")).clear();
    // вводим число
        driver.findElement(By.tagName("input")).sendKeys("10");

    // проверяем значение
        String value0 = driver.findElement(By.tagName("input")).getAttribute("value");
        softAssert.assertEquals(value0, "10");


    // UP
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_UP);
        String value1 = driver.findElement(By.tagName("input")).getAttribute("value");
        softAssert.assertEquals(value1, "11");


    // DOWN
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_DOWN);
        String value3 = driver.findElement(By.tagName("input")).getAttribute("value");
        softAssert.assertEquals(value3, "10");


        softAssert.assertAll();
        driver.quit();
    }
}