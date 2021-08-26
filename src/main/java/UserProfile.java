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


public class UserProfile {
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
	public void Profile() throws Exception {
		 ConfigReader config= new ConfigReader();
		  driver.get(config.getApplicationUrl());
		  
		  driver.findElement(By.linkText("Login")).click();
		  driver.findElement(By.id("username")).click();
		  driver.findElement(By.id("username")).clear();
		  driver.findElement(By.id("username")).sendKeys(config.getRAdminUserName());
		  driver.findElement(By.id("password")).clear();
		  driver.findElement(By.id("password")).sendKeys(config.getSGAUserPassword());
		  driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
		  Thread.sleep(20000);

		// Click On Send Anyway button if the prompt is displaying

	/*	driver.get("https://bi-3.axxonet.com/isonlms/my-profile-details");
		Thread.sleep(30000);

		//SITE
		driver.findElement(By.linkText("View")).click();
		System.out.println("Clicked View Button to view Site Details");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//span[@class='ui-button-icon ui-icon ui-icon-closethick']")).click();
		Thread.sleep(2000);

		//CLIENT
		driver.findElement(By.xpath("//a[@class='use-ajax btn btn-sm btn-info']")).click();
		System.out.println("View CLIENT Details");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//span[@class='ui-button-icon ui-icon ui-icon-closethick']")).click();
		Thread.sleep(2000);

		//LOB
		driver.findElement(By.xpath("(//a[@class='use-ajax btn btn-sm btn-primary'])[2]")).click();
		System.out.println("View LOB Details");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//span[@class='ui-button-icon ui-icon ui-icon-closethick']")).click();
		Thread.sleep(2000);

		//CIRCLE
		driver.findElement(By.xpath("(//a[@class='use-ajax btn btn-sm btn-info'])[2]")).click();
		System.out.println("View CIRCLE Details");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//span[@class='ui-button-icon ui-icon ui-icon-closethick']")).click();
		Thread.sleep(2000);

		driver.findElement(By.linkText("Change Profile Picture")).click();
		Thread.sleep(2000);

		Actions upload =new Actions(driver);
		WebElement chooseFile1 = driver.findElement(By.id("edit-my-file-upload"));
		upload.moveToElement(chooseFile1).perform();

		Thread.sleep(2000);
		chooseFile1.sendKeys("D:\\SrinivasFiles\\Rahul.png");
		System.out.println("Profile Image is Uploaded..!!");
		Thread.sleep(2000);

		driver.findElement(By.id("edit-submit")).click();
		Thread.sleep(2000);

		driver.findElement(By.linkText("Change Password")).click();
		Thread.sleep(2000);

		driver.findElement(By.id("edit-new-password")).sendKeys("ison12345");
		Thread.sleep(2000);

		driver.findElement(By.id("edit-confirm-new-password")).sendKeys("ison12345");
		System.out.println("New Password is Updated!!!");
		Thread.sleep(2000);


		driver.findElement(By.id("edit-submit--2")).click();
		Thread.sleep(17000);

		driver.findElement(By.linkText("Logout")).click();
		System.out.println("Logged Out!!!!");
		Thread.sleep(60000);

		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("username")).sendKeys("13AZSB6097");
		Thread.sleep(4000);

		driver.findElement(By.id("password")).sendKeys("ison12345");
		System.out.println("Logged in To Check with new Password");
		Thread.sleep(5000);

		driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
		Thread.sleep(3000); */

		driver.findElement(By.linkText("Logout")).click();
		System.out.println("Logged Out in Second Login!!!!");

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