import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

public class JSexecutor {
    public static WebDriver driver;
    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver=new ChromeDriver(options);
    }
    @Test
    public void testToDoList() {
        String url = "http://webdriveruniversity.com/To-Do-List/index.html";
        driver.get(url);

        WebElement todoItem = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li[3]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(todoItem).perform();

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].remove()", todoItem);

    }
    @Test
    public void testScrolling() {

        String url = "http://webdriveruniversity.com/Scrolling/index.html";
        driver.get(url);


        WebElement entriesBox = driver.findElement(By.xpath("//*[@id=\"zone2-entries\"]"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", entriesBox);

        // Validate text with JS executor
        String expectedText = "Entries";
        String actualText = (String) jsExecutor.executeScript("return arguments[0].textContent;", entriesBox);
        assert actualText.contains(expectedText) : "Text validation failed";

    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
