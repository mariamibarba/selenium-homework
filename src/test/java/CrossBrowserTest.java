import com.gargoylesoftware.htmlunit.BrowserVersion;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class CrossBrowserTest {


    WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) throws Exception{

        //Check if parameter passed as 'chrome'
        if(browser.equalsIgnoreCase("chrome")){
            //set path to chromedriver_old.exe
            //  System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_old.exe");
             WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
//            create chrome instance
//            driver = new HtmlUnitDriver(BrowserVersion.CHROME);
        }
        //Check if parameter passed as 'Edge'
        else if(browser.equalsIgnoreCase("Edge")){
            //set path to Edge.exe
            // System.setProperty("webdriver.edge.driver", ".\\src\\main\\resources\\MicrosoftWebDriver.exe");
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            //create Edge instance
//            driver = new HtmlUnitDriver(BrowserVersion.FIREFOX);
        }
        else{
            //If no browser passed throw exception
            throw new Exception("Browser is not correct");
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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