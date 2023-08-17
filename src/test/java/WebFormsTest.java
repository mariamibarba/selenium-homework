import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class WebFormsTest {
    public static WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void getOptionFromDropDown() {
        String url = "http://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html";
        driver.get(url);
        driver.manage().window().maximize();
        Select languageDropdown = new Select(driver.findElement(By.id("dropdowm-menu-1")));
        languageDropdown.selectByValue("c#");

        WebElement selectedOption = languageDropdown.getFirstSelectedOption();
        assert selectedOption.getAttribute("value").equals("c#");
    }
    @Test
    public void checkRadio(){

        String url = "http://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html";
        driver.get(url);
        driver.manage().window().maximize();
        driver.findElements(By.cssSelector("input[type='checkbox']:not(:checked)")).forEach(WebElement::click);

    }
    @Test
    public void radioButton()  {

        String url = "http://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html";
        driver.get(url);
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("input[value='yellow']")).click();


    }

    @Test
    public void selectedOrangeOption(){

        String url = "http://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html";
        driver.get(url);
        driver.manage().window().maximize();
        WebElement orangeOption = driver.findElement(By.cssSelector("option[value='orange']"));
        assert !orangeOption.isEnabled();
        System.out.println(orangeOption.isEnabled());
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}
