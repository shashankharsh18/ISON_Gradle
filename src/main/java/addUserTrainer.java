
import org.testng.annotations.*;

import Utility.ConfigReader;

import static org.testng.Assert.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class addUserTrainer{
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
  public void testAddUser() throws Exception {
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
	  
	// Click On Send Anyway button if the prompt is displaying
			/*boolean var=  driver.findElement(By.xpath("//button[@id='proceed-button']")).isDisplayed();
			System.out.println(var);
			if (var==true)
			{
				 driver.findElement(By.xpath("//button[@id='proceed-button']")).click();
			}
			  Thread.sleep(8000); */

		  
	  
	  WebElement menuBar1 = driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[3]/div/ul/li[8]/a"));
	  Actions act1 = new Actions (driver);
	  act1.moveToElement(menuBar1).perform();
	  System.out.println("MOved to Navigation Menu");
	  Thread.sleep(3000);
	  
	  driver.findElement(By.linkText("User Management")).click();
	  System.out.println("Clicked on User Management");
	  Thread.sleep(4000);
	  
	  driver.findElement(By.linkText("Manage Users")).click(); //Manage Users
	  System.out.println("Clicked On Manage Users");
      Thread.sleep(35000);
      
      
      driver.findElement(By.linkText("Add New User")).click(); //Add New User
      System.out.println("Clicked on ADD User Button");
      Thread.sleep(5000);
    
    
      
    String date = new SimpleDateFormat("yyyMMddHHmmssSS").format(new Date());
    driver.findElement(By.name("employee_id")).sendKeys("Trainer" +date);  //Give New User Name
    System.out.println("Given Employee Id");
    Thread.sleep(4000);
    
    driver.findElement(By.name("user_role")).click();
    new Select(driver.findElement(By.name("user_role"))).selectByVisibleText("Trainee");
    driver.findElement(By.name("user_role")).click();
    System.out.println("Selected Role as Trainer");
    Thread.sleep(4000);
    
    driver.findElement(By.name("employment_type")).click();
    new Select(driver.findElement(By.name("employment_type"))).selectByVisibleText("Full Time");
    driver.findElement(By.name("employment_type")).click();
    System.out.println("Selected employment_type as Full Time");
    Thread.sleep(4000);
    
    driver.findElement(By.name("password")).sendKeys("ison12345");
    System.out.println("Typed the password");
    Thread.sleep(4000);
    
    driver.findElement(By.name("first_name")).sendKeys("AxxonetFirstname");
    System.out.println("Given FirstName");
    Thread.sleep(4000);
    
    driver.findElement(By.name("last_name")).sendKeys("Axxonetlastname");
    Thread.sleep(4000);
    System.out.println("Given Lastname");
    
    driver.findElement(By.name("gender")).click();
    new Select(driver.findElement(By.name("gender"))).selectByVisibleText("Male");
    driver.findElement(By.name("gender")).click();
    System.out.println("Selected Gender");
    Thread.sleep(4000);
    
     WebElement element= driver.findElement(By.id("edit-joining-date"));
	 String dateString= "2021-01-02";
	 datePickerByJs1(driver,dateString,element);
	 Thread.sleep(3000);
    
    Select Region= new Select(driver.findElement(By.name("select_region")));
    Region.selectByVisibleText("India");
    System.out.println("Selected Region as India");
    Thread.sleep(8000);
    
    Select Site = new Select(driver.findElement(By.id("edit-select-site")));
    Site.selectByVisibleText("Bangalore");
    System.out.println("Selected Site as Bangalore");
	Thread.sleep(8000);
	
	Select Client=new Select(driver.findElement(By.id("edit-select-client")));
	Client.selectByVisibleText("VIL");
	System.out.println("Selected Client as VIL");
	Thread.sleep(5000);
	
	
	Select LOB=new Select(driver.findElement(By.xpath("(//select[@id='Array-available'])[1]")));
	LOB.selectByVisibleText("Idea_Prepaid");
	System.out.println("Selected LOB  as Idea_Prepaid");
	Thread.sleep(5000);
	
	driver.findElement(By.xpath("(//li[@class='multiselect-add'])[1]")).click();
	System.out.println("Clicked on LOB  Add Icon");
	Thread.sleep(10000);
	
	
	 WebElement Invite;
	 WebDriverWait wait4=new WebDriverWait(driver, 30);
	 Invite = wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Save']")));
     Invite.click();
     System.out.println("Clicked on Save to add User");
     Thread.sleep(6000);
   
     
     WebElement element3 = driver.findElement(By.xpath("(//a[@title='Logout'])[2]"));

  	JavascriptExecutor executor2 = (JavascriptExecutor)driver;
  	executor2.executeScript("arguments[0].click()", element3); 
    System.out.println("Logged Out!!!!");
    //driver.get("https://bi-3.axxonet.com/isonlms/");
    Thread.sleep(3000);
     
	
   
  } 
  public static void datePickerByJs1(WebDriver driver,String date,WebElement element)
  {
  	JavascriptExecutor js= (JavascriptExecutor) driver;
  	js.executeScript("arguments[0].setAttribute('value','"+date+"')", element);

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
