
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

public class managePasswordSDHead {
  private WebDriver driver;
  ConfigReader config= new ConfigReader();
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		String driverPath = "D:/Automation/drivers/browsers";
		String env=config.getEnvironmentName();
		//System.out.println("environmentis :" +env);
		
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
	  driver.findElement(By.id("username")).sendKeys(config.getSDHeadUserName());
	  driver.findElement(By.id("password")).clear();
	  driver.findElement(By.id("password")).sendKeys(config.getISONPassword());
	  driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
	  Thread.sleep(20000);
      
   // Click On Send Anyway button if the prompt is displaying
  /*	boolean var=  driver.findElement(By.xpath("//button[@id='proceed-button']")).isDisplayed();
  	System.out.println(var);
  	if (var==true)
  	{
  		 driver.findElement(By.xpath("//button[@id='proceed-button']")).click();
  	}
  	  Thread.sleep(8000);*/
	  
	  
      WebElement menuBar1 = driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[3]/div/ul/li[3]/a"));
	  Actions act1 = new Actions (driver);
	  act1.moveToElement(menuBar1).perform();
	  Thread.sleep(5000);
	  
	  driver.findElement(By.linkText("User Management")).click();
	  System.out.println("Clicked on user management");
	  Thread.sleep(5000);
	  
      driver.findElement(By.linkText("Manage Passwords")).click(); //Manage Password
      System.out.println("Clicked on manage password");
      Thread.sleep(60000);
      
      System.out.println("User Started to Change the Password ...!!!");
      driver.findElement(By.xpath("//input[@type='search']")).sendKeys("ISBL02392");  //Failed once here
      Thread.sleep(5000);
      
      driver.findElement(By.xpath("//text()[contains(.,'Change Password')]/ancestor::a[1]")).click();
      System.out.println("User Selected and clicked on Change the Password Button ...!!!");
      Thread.sleep(5000);
      
 
    driver.findElement(By.name("new_password")).sendKeys("ison543");
    System.out.println("Given new password");
    Thread.sleep(5000);
    
  
    driver.findElement(By.name("confirm_new_password")).sendKeys("ison543");
    System.out.println("Given Confirmation password");
    Thread.sleep(5000);
    
    driver.findElement(By.name("op")).click();
    Thread.sleep(15000);
    
    //driver.findElement(By.cssSelector("span.ui-button-icon.ui-icon.ui-icon-closethick")).click();//Close pop-up window
   //Thread.sleep(3000);
    
    System.out.println("User Password Changed Successfully..!!!");
    Thread.sleep(2000); 
    
    driver.findElement(By.linkText("Logout")).click();
    //driver.get("https://bi-3.axxonet.com/isonlms/"); 
    Thread.sleep(5000);

   
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