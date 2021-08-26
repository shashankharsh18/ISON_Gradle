
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import Utility.ConfigReader;




public class globalInsightsDashboard {
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
  public void testglobalInsightsDashboard() throws Exception {
	  	 ConfigReader config= new ConfigReader(); 

	  
	 driver.get(config.getApplicationUrl());
		  
		  driver.findElement(By.linkText("Login")).click();
		  driver.findElement(By.id("username")).click();
		  driver.findElement(By.id("username")).clear();
		  driver.findElement(By.id("username")).sendKeys(config.getSGAUserName());
		  driver.findElement(By.id("password")).clear();
		  driver.findElement(By.id("password")).sendKeys(config.getISONPassword());
		  driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
		  Thread.sleep(20000);
	    
	  System.out.println("Logged into Brajesh Account"); 
	  Thread.sleep(10000);
	  
	  System.out.println(driver.findElements(By.tagName("iframe")).size());
	  Thread.sleep(5000);
	  
	  driver.switchTo().frame("iFrameResizer0");
	  Thread.sleep(6000);
		
	  
	  driver.findElement(By.xpath("//div[@title='Headcount']")).click();
	  System.out.println("Clicked on HeadCount To view Chart");
	  Thread.sleep(6000);
	  
	  WebElement CloseIcon = driver.findElement(By.xpath("//i[@class='fa fa-times-circle pull-right']"));
	  JavascriptExecutor executor = (JavascriptExecutor)driver;
	  executor.executeScript("arguments[0].click();", CloseIcon);
	  System.out.println("Clicked on CloseIcon");
	  
	  driver.switchTo().parentFrame();
	  Thread.sleep(6000);
	  
	  //Code to click on Infobar 
	  //driver.findElement(By.xpath("(//span[@class='card-value-cp'])[1]")).click();
	  //Thread.sleep(3000);
	  
	  //System.out.println("Global Insights Dashboard Loaded Successfully... !!");
	  
	  driver.findElement(By.linkText("Logout")).click();
	  Thread.sleep(2000);  
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
