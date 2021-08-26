
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import Utility.ConfigReader;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class listSentNotificationsClient {
	private WebDriver driver;
	ConfigReader config= new ConfigReader();
	
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
	public void ListSentNotifications() throws Exception {
		ConfigReader config= new ConfigReader();
	  driver.get(config.getApplicationUrl());
	  
	  driver.findElement(By.linkText("Login")).click();
	  driver.findElement(By.id("username")).click();
	  driver.findElement(By.id("username")).clear();
	  driver.findElement(By.id("username")).sendKeys(config.getClientUserName());
	  driver.findElement(By.id("password")).clear();
	  driver.findElement(By.id("password")).sendKeys(config.getISONPassword());
	  driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
	  Thread.sleep(60000);

		// Click On Send Anyway button if the prompt is displaying
		/*boolean var=  driver.findElement(By.xpath("//button[@id='proceed-button']")).isDisplayed();
		System.out.println(var);
		if (var==true)
		{
			driver.findElement(By.xpath("//button[@id='proceed-button']")).click();
		}
		Thread.sleep(8000); */

		WebElement menuBar1 =driver.findElement(By.xpath("//li[@class='menuparent menu-item menu-item--expanded'][1]"));
		Actions act1 = new Actions (driver);
		act1.moveToElement(menuBar1).build().perform();
		System.out.println("Moved to Left Navigation Bar");
		Thread.sleep(8000);

		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		WebElement TraineeTab = driver.findElement(By.xpath("//a[@title='Notifications']"));
		js3.executeScript("arguments[0].scrollIntoView();", TraineeTab);
		Thread.sleep(2000);

		WebElement TraineeClick;
		WebDriverWait wait1=new WebDriverWait(driver, 10);
		TraineeClick = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Notifications']")));
		System.out.println("Clicked On Notifications");
		TraineeClick.click();
		
		driver.findElement(By.linkText("View")).click();
		//driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div/table/tbody/tr[1]/td[5]/a")).click();//View button
		System.out.println("Clicked on View Button");
		Thread.sleep(5000);

		driver.findElement(By.xpath("//a[contains(text(),'Cancel')]")).click();//Cancel button
		System.out.println("Clicked on Cancel Button");
		Thread.sleep(5000);

		System.out.println("List of Sent Notifications page navigated Successfully..!!!");
		Thread.sleep(8000);

		driver.findElement(By.linkText("Logout")).click();
		System.out.println("Logged Out");
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
