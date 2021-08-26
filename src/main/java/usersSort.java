
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import Utility.ConfigReader;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class usersSort {
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
	String driverPath = "D:/Automation/drivers/browsers";
	System.setProperty("webdriver.chrome.driver", driverPath+"/chrome/chromedriver.exe");
	
	DesiredCapabilities dc = new DesiredCapabilities();
    dc.setAcceptInsecureCerts(true);
    
    ChromeOptions coptions = new ChromeOptions();
    coptions.merge(dc);
     
    driver = new ChromeDriver(coptions);
    
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
  }

  @Test
  public void testusersSort() throws Exception {
	  ConfigReader config= new ConfigReader();
	  driver.get(config.getApplicationUrl());
	  
	  driver.findElement(By.linkText("Login")).click();
	  driver.findElement(By.id("username")).click();
	  driver.findElement(By.id("username")).clear();
	  driver.findElement(By.id("username")).sendKeys(config.getSGAUserName());
	  driver.findElement(By.id("password")).clear();
	  driver.findElement(By.id("password")).sendKeys(config.getSGAUserPassword());
	  driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
	  Thread.sleep(20000);
	  
	  
	  WebElement menuBar1 = driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[3]/div/ul/li[3]/a"));
	  Actions act1 = new Actions (driver);
	  act1.moveToElement(menuBar1).perform();
	  driver.findElement(By.linkText("User Management")).click();
	  Thread.sleep(5000);
      driver.findElement(By.linkText("Manage Users")).click(); //Manage Users
      Thread.sleep(15000);
	  
    driver.findElement(By.id("edit-select-region")).click();
    new Select(driver.findElement(By.id("edit-select-region"))).selectByVisibleText("Africa");
    Thread.sleep(3000);
    driver.findElement(By.id("edit-select-site")).click();
    new Select(driver.findElement(By.id("edit-select-site"))).selectByVisibleText("Zambia");
    Thread.sleep(3000);
    driver.findElement(By.id("edit-select-sort-by-value")).click();
    new Select(driver.findElement(By.id("edit-select-sort-by-value"))).selectByVisibleText("Last Name");
    Thread.sleep(3000);
    driver.findElement(By.id("edit-reset")).click();
    Thread.sleep(3000);
    
    System.out.println("User sorting applied for the listed Users...!!!");
    Thread.sleep(5000);
        
    driver.findElement(By.linkText("Logout")).click();
    
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
