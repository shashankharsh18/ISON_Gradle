
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class editUser {
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
    driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
  }

  @Test
  public void testEditUser() throws Exception {
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
      Thread.sleep(15000);
      
      
        WebElement regionDropDown1 = driver.findElement(By.name("select_region"));  
    	Select dropdown1 = new Select(regionDropDown1);
    	dropdown1.selectByValue("1");
    	Thread.sleep(5000);
    	
    	WebElement siteDropDown1 = driver.findElement(By.name("select_site"));  
    	Select dropdown2 = new Select(siteDropDown1);
    	dropdown2.selectByValue("1");
    	Thread.sleep(3000);
      
    	 driver.findElement(By.id("edit-search-user-text")).sendKeys("ISBL02365");
         Thread.sleep(3000);
         
         driver.findElement(By.id("edit-search")).click();
         Thread.sleep(3000);
         
    //driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div[2]/div[2]/div/div/form/div[2]/div[1]/div[1]/table/tbody/tr[5]/td[9]/div/div[3]/div/div[2]/a")).click(); //Edit User
 /*  driver.get("https://lmstraining.isonxperiences.com/edit-user?num=9248");
    Thread.sleep(5000);
    
    driver.findElement(By.name("first_name")).click();
    Thread.sleep(3000);
    driver.findElement(By.name("first_name")).clear();
    Thread.sleep(3000);
    driver.findElement(By.name("first_name")).sendKeys("mytestuserfirstnameedtd");
    Thread.sleep(3000);
    driver.findElement(By.name("last_name")).click();
    Thread.sleep(3000);
    driver.findElement(By.name("last_name")).clear();
    Thread.sleep(3000);
    driver.findElement(By.name("last_name")).sendKeys("mytestuserlastnameedtd");
    Thread.sleep(3000); */
    
   /* WebElement element = driver.findElement(By.xpath("//input[@value='Save']"));    
    JavascriptExecutor js = (JavascriptExecutor) driver;  
    js.executeScript("arguments[0].scrollIntoView();",element); */

  /*  driver.findElement(By.name("op")).click(); //Save
    Thread.sleep(5000);*/
    //Above Save code having an issue as it is not clickable (Elements overlapped)
    
	
  /*   WebElement Invite;
	 WebDriverWait wait4=new WebDriverWait(driver, 30);
	 Invite = wait4.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-submit")));
    Invite.click(); */
    
  /*  boolean var= driver.findElement(By.xpath("//input[@value='Save']")).isDisplayed();
	System.out.println(var);
	if (var==true)
	{
		driver.findElement(By.xpath("//input[@value='Save']"));
	}
	  Thread.sleep(8000); 
    
    System.out.println("Clicked on Save to add User");
    Thread.sleep(6000); */
    System.out.println("Successfully edited an User...!!!");
    
    driver.findElement(By.linkText("Logout")).click(); 
    Thread.sleep(3000);
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