import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HTML4DRUGANDDROP {
    static WebDriver driver;
@BeforeClass
        public void setUpt()  {
    System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("http://magenicautomation.azurewebsites.net/Automation");
    driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

}





@Test
        public void dragAndDropTest() {
    WebElement sourceelement = driver.findElement(By.xpath("//*[@id='draggable']/p"));
    WebElement targetelement1 = driver.findElement(By.xpath("//*[@id='droppable']"));
    WebElement targetelement2 = driver.findElement(By.xpath("//*[@id='droppable2']"));
    Actions action = new Actions(driver);
    action.clickAndHold(sourceelement).moveToElement(targetelement1).release().build().perform();

    action.clickAndHold(targetelement1).moveToElement(targetelement2).release().build().perform();
}

       // @AfterClass
                public void tearDown(){
            driver.close();
        }


}
