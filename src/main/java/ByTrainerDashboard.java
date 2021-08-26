
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

public class ByTrainerDashboard {
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

		Actions act = new Actions(driver);  	//Move to Left mouse over bar
		WebElement navigate =driver.findElement(By.xpath("//li[@class='menuparent menu-item menu-item--expanded'][1]"));
		act.moveToElement(navigate).build().perform();
		Thread.sleep(10000);


		driver.findElement(By.linkText("My Dashboard")).click();
		Thread.sleep(5000);

		driver.findElement(By.xpath("//*[contains(text(),'Training Performance - by Trainer')]")).click(); //LMS Active Users
		Thread.sleep(14000);
		
		driver.findElement(By.id("render_MainfilterFromDateSelector")).click();
		Thread.sleep(3000);
		
		//From Date  
		WebElement element= driver.findElement(By.id("render_MainfilterFromDateSelector"));
		element.click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("10")).click();

		//To Date
		WebElement element1= driver.findElement(By.id("render_MainfilterToDateSelector"));
		element1.click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("27")).click();
		Thread.sleep(6000);

		driver.switchTo().parentFrame();
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