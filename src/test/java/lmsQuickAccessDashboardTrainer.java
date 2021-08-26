

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utility.ConfigReader;


import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.CapabilityType;
//import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.firefox.FirefoxProfile;


public class lmsQuickAccessDashboardTrainer {
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
  public void testlmsQuickAccessDashboard() throws Exception {
	  ConfigReader config= new ConfigReader();
	  driver.get(config.getApplicationUrl());
	  
	  driver.findElement(By.linkText("Login")).click();
	  driver.findElement(By.id("username")).click();
	  driver.findElement(By.id("username")).clear();
	  driver.findElement(By.id("username")).sendKeys(config.getTrainerUserName());
	  driver.findElement(By.id("password")).clear();
	  driver.findElement(By.id("password")).sendKeys(config.getISONPassword());
	  driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
	  Thread.sleep(20000);
	  	  
	  WebElement menuBar1 = driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[3]/div/ul/li[1]/a"));
	  Actions act1 = new Actions (driver);
	  act1.moveToElement(menuBar1).perform();
	  Thread.sleep(3000);
	  
	  driver.findElement(By.linkText("My Dashboard")).click();
	  Thread.sleep(3000);
	  
      driver.findElement(By.linkText("LMS Quick Access")).click(); //LMS Quick Access
      Thread.sleep(4000);
      
      //Upcoming Trainings Navigation
      driver.findElement(By.id("dncalendar-next-month")).click();
      System.out.println("Clicked calendar Icon for 1st time");
      Thread.sleep(4000);
      
      driver.findElement(By.id("dncalendar-next-month")).click();
      System.out.println("Clicked calendar Icon for 2nd time");
      Thread.sleep(4000);
 
      System.out.println("LMS Quick Access Dashboard navigation was suceessfully  ..!!!");
    
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
