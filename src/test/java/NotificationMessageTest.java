import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class NotificationMessageTest {
    @Test
    public void notificationMessage() {
        //задаем опции для нашего драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notfications");

        //определяем браузер с которым хотим работать
        WebDriver driver = new ChromeDriver(options);
        SoftAssert softAssert = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //открывает страницу по указанному url
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");

        //нажимаем на кнопку
        driver.findElement(By.xpath("//a[text()='Click here']")).click();

        //проверяем соответствие текста ожиданиям
        String text = driver.findElement(By.id("flash")).getText();
        Assert.assertTrue(text.contains("Action"));

        driver.quit();
    }
}
