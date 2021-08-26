
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

public class importBulkUsersSDHead {
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
  public void testImportBulkusers() throws Exception {
	  ConfigReader config= new ConfigReader();
	  driver.get(config.getApplicationUrl());
	  
	  driver.findElement(By.linkText("Login")).click();
	  driver.findElement(By.id("username")).click();
	  driver.findElement(By.id("username")).clear();
	  driver.findElement(By.id("username")).sendKeys(config.getSDHeadUserName());
	  driver.findElement(By.id("password")).clear();
	  driver.findElement(By.id("password")).sendKeys(config.getISONPassword());
	  driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
	  Thread.sleep(40000);
	  
	// Click On Send Anyway button if the prompt is displaying
		/*boolean var=  driver.findElement(By.xpath("//button[@id='proceed-button']")).isDisplayed();
		System.out.println(var);
		if (var==true)
		{
			 driver.findElement(By.xpath("//button[@id='proceed-button']")).click();
		}
		  Thread.sleep(8000);*/


	  WebElement menuBar1 = driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[3]/div/ul/li[3]/a"));
	  Actions act1 = new Actions (driver);
	  act1.moveToElement(menuBar1).perform();
	  driver.findElement(By.linkText("User Management")).click();
	  Thread.sleep(5000);
	  driver.findElement(By.linkText("Import - Bulk Users")).click(); //Import - Bulk Users
      Thread.sleep(5000);
     
      
      driver.findElement(By.id("edit-file-upload")).sendKeys("D:\\SrinivasFiles\\SampleTestData\\testdataSDHead.csv");
      Thread.sleep(5000);
      
    driver.findElement(By.id("edit-submit")).click(); //Save
    Thread.sleep(20000);
    System.out.println("Successfully Imported the Bulk Users...!!!");
    //Thread.sleep(60000);
    
    
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
