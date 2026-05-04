import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.time.Duration;

public class FileUploadTest {
    @Test
    public void FileUploadMenu() {
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
        driver.get("https://the-internet.herokuapp.com/upload");
        // Путь к файлу на компьютере
        File file = new File("C:\\Users\\Lil Momma\\Desktop\\JAVA\\ДОМАШКА\\3.Java (Методичка).pdf");
        // Input для загрузки файла
        driver.findElement(By.id("file-upload"))
                .sendKeys(file.getAbsolutePath());
        // Нажимаем кнопку Upload
        driver.findElement(By.id("file-submit")).click();
        // Имя файла, которое отображается на странице после загрузки
        String uploadedFile = driver.findElement(By.id("uploaded-files")).getText();
        Assert.assertEquals(uploadedFile, file.getName());
    }
}
