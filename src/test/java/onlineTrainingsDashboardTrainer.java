
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import Utility.ConfigReader;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class onlineTrainingsDashboardTrainer {
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
	public void testonlineTrainingsDashboard() throws Exception {
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

		driver.findElement(By.linkText("My Dashboard")).click();
		Thread.sleep(5000);

		driver.findElement(By.linkText("Online Trainings")).click(); //Online Trainings

		Thread.sleep(8000);


		driver.switchTo().frame("iFrameResizer0");
		Thread.sleep(6000);

		//driver.findElement(By.xpath("//input[@type='search']")).sendKeys("Duplicate of Trainer Testing");
		//Thread.sleep(7000);

		//From Date  
		WebElement element= driver.findElement(By.id("render_MainfilterFromDateSelector"));
		element.click();
		Thread.sleep(5000);
		driver.findElement(By.linkText("7")).click();
		Thread.sleep(5000);

		//To Date
		WebElement element1= driver.findElement(By.id("render_MainfilterToDateSelector"));
		element1.click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("27")).click();
		Thread.sleep(6000);






		/*
      //Click on 1st Row of table grid in Portlet 1
      driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/section/div[1]/div/div/div[2]/div/div[1]/div/div[2]/div[2]/div/table/tbody/tr[1]/td[1]")).click();
      Thread.sleep(3000);
      //Click on 1st Row of table grid in Portlet 2
      driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/section/div[1]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/table/tbody/tr[1]/td[1]")).click();
      Thread.sleep(3000);
      //Click on Slide button
      driver.findElement(By.cssSelector("span.lever")).click();
      Thread.sleep(3000);
      driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/section/div[2]/div/div/div[1]/div/span[2]/label/span[2]")).click();
		 */

		System.out.println("Online Training Dashboard loaded Successfully... !!!");

		driver.switchTo().defaultContent();
		Thread.sleep(7000);

		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(2000);

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
