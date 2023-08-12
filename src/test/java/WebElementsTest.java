import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.List;


public class WebElementsTest {
    public static WebDriver driver;

    @BeforeMethod
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }


    @Test
    public void testWebElementActions() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        String url = "http://the-internet.herokuapp.com/add_remove_elements/";
        driver.get(url);


        for (int i = 0; i < 3; i++) {
            WebElement addElementButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div/button"));
            Thread.sleep(1000);

            addElementButton.click();
        }

        // Print the last 'Delete' button element using findElement()
        WebElement lastDeleteButton = driver.findElement(By.xpath("(//button[text()='Delete'])[last()]"));
        Thread.sleep(1000);
        System.out.println("Last Delete Button (findElement()): " + lastDeleteButton);

        List<WebElement> deleteButton = driver.findElements(By.cssSelector("[class^='added']"));
        WebElement lastDeleteButtonCss = deleteButton.get(deleteButton.size() - 1);
        System.out.println("Last Delete button (findElements() with cssSelector): " + lastDeleteButtonCss);


        Thread.sleep(2000);
        // Print the last 'Delete' button using findElement() and relative XPath
        WebElement lastDeleteButtonXPath = driver.findElement(By.xpath("//button[contains(@class, 'manually') and text()='Delete']"));
        System.out.println("Last 'Delete' button (findElement() - XPath): " + lastDeleteButtonXPath.getText());
        driver.quit();



    }
    @Test
    public void test2() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        String url= "http://the-internet.herokuapp.com/challenging_dom";
        driver.get(url);


        WebElement elementWithApeirian9 = driver.findElement(By.xpath("//td[text()='Apeirian9']/following-sibling::td"));
        System.out.println("Lorem value of element with Ipsum value 'Apeirian9': " + elementWithApeirian9.getText());
        Thread.sleep(1000);


        WebElement nextElement = elementWithApeirian9.findElement(By.xpath("following-sibling::td"));
        System.out.println("Next element of element with Ipsum value 'Apeirian9': " + nextElement.getText());
        Thread.sleep(1000);


        driver.quit();


    }
    @AfterMethod
    public void after(){
        driver.quit();
    }




}
