import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;

import Utility.ConfigReader;

import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class usersExportRAdmin {
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
	
   String driverPath = "D:/Automation/drivers/browsers";
   System.setProperty("webdriver.chrome.driver", driverPath+"/chrome/chromedriver.exe");


 
  String downloadFilepath = "D:\\SrinivasFiles\\ScriptsDownloads\\"; //download path

  HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
  chromePrefs.put("profile.default_content_settings.popups", 0);
  chromePrefs.put("download.default_directory", downloadFilepath);
  ChromeOptions options = new ChromeOptions();
  options.setExperimentalOption("prefs", chromePrefs);
  options.addArguments("--test-type");
  options.addArguments("--disable-extensions");
  options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
  
  
  DesiredCapabilities dc = new DesiredCapabilities();
  dc.setAcceptInsecureCerts(true);
  options.merge(dc);
  driver = new ChromeDriver(options);  
   
  driver.manage().window().maximize();
  driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
  }

  @Test
  public void testUsersexport() throws Exception {
	  ConfigReader config= new ConfigReader();
	  driver.get(config.getApplicationUrl());
	  
	  driver.findElement(By.linkText("Login")).click();
	  driver.findElement(By.id("username")).click();
	  driver.findElement(By.id("username")).clear();
	  driver.findElement(By.id("username")).sendKeys(config.getRAdminUserName());
	  driver.findElement(By.id("password")).clear();
	  driver.findElement(By.id("password")).sendKeys(config.getISONPassword());
	  driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
	  Thread.sleep(20000);
	  

	// Click On Send Anyway button if the prompt is displaying
	  
	  Actions act = new Actions(driver);  	//Move to Left mouse over bar
	  WebElement navigate =driver.findElement(By.xpath("//li[@class='menuparent menu-item menu-item--expanded'][1]"));
	  act.moveToElement(navigate).build().perform();
	  System.out.println("Moved to Left Navigation MenuBar");
	  Thread.sleep(10000);
	  
	  driver.findElement(By.xpath("//a[@title='User Management']")).click();
	  System.out.println("Clicked On User Management");
	  Thread.sleep(5000);
	  
	  driver.findElement(By.xpath("//a[@title='Manage Users']")).click();
      System.out.println("Clicked On Manage Users");
      Thread.sleep(40000);
      
      WebElement regionDropDown1 = driver.findElement(By.name("select_region"));  
  	  Select dropdown1 = new Select(regionDropDown1);
  	  dropdown1.selectByValue("1");
      	  
  	  System.out.println("Selected Region");
  	  Thread.sleep(5000);
  	
  	  WebElement siteDropDown1 = driver.findElement(By.name("select_site"));  
  	  Select dropdown2 = new Select(siteDropDown1);
  	  dropdown2.selectByValue("1");
  	
  	  System.out.println("Selected Site");
  	  Thread.sleep(5000);
  	 	   
  	  driver.findElement(By.xpath("//input[@id='edit-export-users-details-button']")).sendKeys(Keys.ENTER);
  	  Thread.sleep(4000);	
	  System.out.println("clicked on ExportUsers Download Button");
	  Thread.sleep(8000);
	  
	  WebElement element3 = driver.findElement(By.xpath("(//a[@title='Logout'])[2]"));

	  JavascriptExecutor executor2 = (JavascriptExecutor)driver;
	  executor2.executeScript("arguments[0].click()", element3); 
	  System.out.println("Logged Out!!!!");
	
     
  }

  @AfterClass(alwaysRun = true)
 
  public void tearDown() throws Exception {
	 driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
      driver.close();
    }
  }
}
