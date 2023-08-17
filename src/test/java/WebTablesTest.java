import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class WebTablesTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void webTablesTest() {
        String url = "http://techcanvass.com/Examples/webtable.html";
        driver.get(url);

        WebElement table = driver.findElement(By.xpath("//table[@id='t01']"));

        WebElement hondaRow = table.findElement(By.xpath("//td[contains(text(),'Honda')]/following-sibling::td[2]"));
        String hondaPrice = hondaRow.getText();

        System.out.println("Honda's price: " + hondaPrice);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
