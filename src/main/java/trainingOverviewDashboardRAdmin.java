import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import static org.testng.Assert.*;


import Utility.ConfigReader;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class trainingOverviewDashboardRAdmin {
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
	public void testtrainingOverviewDashboard() throws Exception {

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


		WebElement menuBar1 = driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[3]/div/ul/li[1]/a"));
		Actions act1 = new Actions (driver);
		act1.moveToElement(menuBar1).perform();

		driver.findElement(By.linkText("My Dashboard")).click();
		Thread.sleep(5000);

		driver.findElement(By.linkText("Training Overview")).click();
		Thread.sleep(10000);

		//Calendar Reset Filters Navigation

		/* WebElement element = driver.findElement(By.xpath("//span[@class='lever']"));
	  JavascriptExecutor executor = (JavascriptExecutor)driver;
	  executor.executeScript("arguments[0].click()", element);

	  System.out.println("Clicked from plan");
      Thread.sleep(10000);

      WebElement element2 = driver.findElement(By.xpath("//span[@class='lever']"));
	  JavascriptExecutor executor2 = (JavascriptExecutor)driver;
	  executor2.executeScript("arguments[0].click()", element2);


      System.out.println("Clicked from actual");
      Thread.sleep(10000);

      driver.switchTo().defaultContent();
      Thread.sleep(3000);	  
	  System.out.println("Training Overview Dashboard loaded Successfully.. !!");	*/ 


		//driver.switchTo().frame("iFrameResizer0");
		//Thread.sleep(5000);

		/*
	  driver.findElement(By.xpath("//input[@class='search-text']")).sendKeys("Divya H");
	  Thread.sleep(5000);

	  driver.switchTo().defaultContent();
      Thread.sleep(3000);


	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  js.executeScript("window.scrollBy(0,-250)", "");
	  Thread.sleep(3000);   */


		driver.findElement(By.linkText("Logout")).click(); 
		Thread.sleep(4000);
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
