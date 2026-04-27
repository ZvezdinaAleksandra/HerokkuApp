import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

    public class ContextMenuTest {

        @Test
        public void chekContextMenu() {
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
            driver.get("https://the-internet.herokuapp.com/context_menu");
            // Сделать клик ПКМ
            WebElement box = driver.findElement(By.id("hot-spot"));
            new Actions(driver).contextClick(box).perform();
            // Переключится на алерт
            Alert alert = driver.switchTo().alert();
            Assert.assertEquals(alert.getText(),
                    "You selected a context menu");
            alert.accept();
            driver.quit();
        }
    }
