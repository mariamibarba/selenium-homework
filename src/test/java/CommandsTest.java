import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CommandsTest {

    @Test
    public void tetssss() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        String url = "http://the-internet.herokuapp.com/dynamic_controls";
        driver.get(url);
        driver.manage().window().maximize();

        WebElement enableButton = driver.findElement(By.xpath("//button[contains(text(),'Enable')]"));

        enableButton.click();


        WebElement inputField = driver.findElement(By.xpath("//input[@type='text']"));
        inputField.isEnabled();

        driver.findElement(By.id("message")).isDisplayed();

        driver.findElement(By.xpath("//button[contains(text(),'Disable')]")).isDisplayed();

        inputField.sendKeys("Bootcamp");

        inputField.clear();

        driver.quit();



        WebDriverManager.chromedriver().setup();
        WebDriver newDriver = new ChromeDriver();

        newDriver.navigate().to("http://the-internet.herokuapp.com/drag_and_drop");
        newDriver.manage().window().maximize();


        // Get the WebElements representing columns A and B
        WebElement columnA = newDriver.findElement(By.id("column-a"));
        WebElement columnB = newDriver.findElement(By.id("column-b"));


        int columnAY = columnA.getLocation().getY();
        int columnBY = columnB.getLocation().getY();



        System.out.println(columnBY);
        System.out.println(columnAY);

        if (columnAY==columnBY){
            System.out.println("they have same y coordinates");

        }
        newDriver.quit();



    }



}
