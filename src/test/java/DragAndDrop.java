import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DragAndDrop {

    static WebDriver driver;
    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver",  "driver/chromedriver.exe");
        driver  = new ChromeDriver();
        driver.get("http://magenicautomation.azurewebsites.net/Automation");
        driver.manage().window().maximize();

    }
    @Test
    public void dragAndDropTest(){

    }

}
