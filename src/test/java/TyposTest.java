import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class TyposTest {

    @Test
    public void tupos() {
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
        driver.get("https://the-internet.herokuapp.com/typos");
        //создаем цикл
        for (int i = 0; i < 10; i++) {

            driver.navigate().refresh(); //рефреш страницы
            String text = driver.findElement(By.xpath("(//p)[2]")).getText();

            softAssert.assertTrue(
                    text.contains("Sometimes you'll see a typo, other times you won,t."),
                    "Unexpected text on iteration " + i
            );
        }
        softAssert.assertAll();
        driver.quit();
    }
}

