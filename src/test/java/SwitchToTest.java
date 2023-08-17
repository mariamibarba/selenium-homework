import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class SwitchToTest {
    public static WebDriver driver;
    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void switchToTest()  {
        String url = "http://the-internet.herokuapp.com/iframe";
        driver.get(url);

        WebElement iframe = driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame(iframe);

        WebElement textField = driver.findElement(By.id("tinymce"));
        textField.clear();
        textField.sendKeys("Here Goes");


        driver.switchTo().defaultContent();

        WebElement alignCenterIcon = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[1]/div[1]/div[2]/div/div[4]/button[2]/span"));
        alignCenterIcon.click();

    }
    @Test
    public void alertTest() {
        String url = "https://demoqa.com/alerts";
        driver.get(url);
        WebElement clickMeButton = driver.findElement(By.id("alertButton"));
        clickMeButton.click();

        Alert alert = driver.switchTo().alert();
        alert.accept();


    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
