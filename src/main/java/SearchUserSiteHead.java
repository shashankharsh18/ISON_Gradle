
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import Utility.ConfigReader;


import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class SearchUserSiteHead {
  private WebDriver driver;
  
  ConfigReader config= new ConfigReader(); 
  
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		String driverPath = "D:/Automation/drivers/browsers";
		String env=config.getEnvironmentName();
		System.out.println("environmentis :" +env);
		
		if(env.contains("UAT")){
			
			System.setProperty("webdriver.chrome.driver", driverPath+"/chrome/chromedriver.exe");
		    
		    DesiredCapabilities dc = new DesiredCapabilities();
		    dc.setAcceptInsecureCerts(true);
		    
		    ChromeOptions coptions = new ChromeOptions();
		    coptions.merge(dc);
		     
		    driver = new ChromeDriver(coptions);
		}
		else 
		{
		System.setProperty("webdriver.gecko.driver",driverPath+"/firefox/geckodriver.exe");
		

		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(false);

		driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
	}

  @Test
  public void testManagePassword() throws Exception {
	  ConfigReader config= new ConfigReader();
	  driver.get(config.getApplicationUrl());
	  
	  driver.findElement(By.linkText("Login")).click();
	  driver.findElement(By.id("username")).click();
	  driver.findElement(By.id("username")).clear();
	  driver.findElement(By.id("username")).sendKeys(config.getSiteHeadUserName());
	  driver.findElement(By.id("password")).clear();
	  driver.findElement(By.id("password")).sendKeys(config.getISONPassword());
	  driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
	  Thread.sleep(20000);
	 
	  
	  WebElement menuBar1 = driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[3]/div/ul/li[3]/a"));
	  Actions act1 = new Actions (driver);
	  act1.moveToElement(menuBar1).perform();
	  driver.findElement(By.linkText("User Management")).click();
	  Thread.sleep(5000);
	  
	  driver.findElement(By.linkText("Manage Users")).click(); //Manage Users
	  System.out.println("Clicked On Manage Users");
      Thread.sleep(15000);
      
      Select Region= new Select(driver.findElement(By.name("select_region")));
      Region.selectByVisibleText("India");
      System.out.println("Selected Region as India");
      Thread.sleep(8000);
      
      Select Site = new Select(driver.findElement(By.id("edit-select-site")));
      Site.selectByVisibleText("Bangalore");
      System.out.println("Selected Site as Bangalore");
  	  Thread.sleep(12000);
  	
  	 driver.findElement(By.xpath("//input[@id='edit-search-user-text']")).sendKeys("ISBL01461");
     System.out.println("Searched the user in Saerch Prompt");
     Thread.sleep(5000);

     WebElement element= driver.findElement(By.xpath("//input[@type='submit']"));
     JavascriptExecutor executor = (JavascriptExecutor)driver;
     executor.executeScript("arguments[0].click();", element);
     System.out.println("Clicked on Search Icon to search user");
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