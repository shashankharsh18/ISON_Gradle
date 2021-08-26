
import static org.testng.Assert.*;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import Utility.ConfigReader;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class isonLogin {
	  private WebDriver driver;
	  @DataProvider(name = "Authentication")
	  public static Object[][] credentials() {
	  return new Object[][] { { "ISBL0430", "ison12345" }, { "13AZSB201", "ison12345" }, 
			                  { "ISBL02272", "ison12345" },
			                  { "13AZSB602", "ison12345" } };
	  }

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
	String driverPath = "D:/Automation/drivers/browsers";
	//System.setProperty("webdriver.gecko.driver",driverPath+"/geckodriver-v0.12.0/geckodriver.exe");
    System.setProperty("webdriver.chrome.driver", driverPath+"/chrome/chromedriver.exe");  
    
    DesiredCapabilities dc = new DesiredCapabilities();
    dc.setAcceptInsecureCerts(true);
    
    ChromeOptions coptions = new ChromeOptions();
    coptions.merge(dc);
     
    driver = new ChromeDriver(coptions);
    
	//driver = new FirefoxDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
  }

  @Test(dataProvider = "Authentication")
  public void testLoginScript(String sUsername, String sPassword) throws Exception {
	  ConfigReader config= new ConfigReader();
	  driver.get(config.getApplicationUrl());
	  
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys(sUsername);
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys(sPassword);
    Thread.sleep(5000);
    driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
    Thread.sleep(40000);
    

 // Click On Send Anyway button if the prompt is displaying
 	/*boolean var=  driver.findElement(By.xpath("//button[@id='proceed-button']")).isDisplayed();
 	System.out.println(var);
 	if (var==true)
 	{
 		 driver.findElement(By.xpath("//button[@id='proceed-button']")).click();
 	}
 	  Thread.sleep(8000); */
 	  
    System.out.println("User: " + sUsername + " logged in & logged out");
    Thread.sleep(10000);
    driver.findElement(By.linkText("Logout")).click();
    Thread.sleep(3000);
  }

   @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    }
  }
