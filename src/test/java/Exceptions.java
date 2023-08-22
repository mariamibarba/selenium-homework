import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;




public class Exceptions {
    public static WebDriver driver;
    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);

    }
    @Test
    public void testAlertExceptions(){
        String url = "https://demoqa.com/alerts";
        driver.get(url);
        driver.findElement(By.id("timerAlertButton")).click();
        try {
            Thread.sleep(3000);
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            System.out.println("NoAlertPresentException caught");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            driver.findElement(By.id("timerAlertButton")).click();
            driver.navigate().refresh();
        } catch (StaleElementReferenceException e) {
            System.out.println("StaleElementReferenceException caught");
        }

    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
