import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;

import Utility.ConfigReader;

import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class Certificate {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		String driverPath = "D:/Automation/drivers/browsers";
		System.setProperty("webdriver.chrome.driver", driverPath+"/chrome/chromedriver.exe");

		String downloadFilepath = "D:\\SrinivasFiles\\ScriptsDownloads\\"; //download path

		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--test-type");
		options.addArguments("--disable-extensions");
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);


		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setAcceptInsecureCerts(true);
		options.merge(dc);
		driver = new ChromeDriver(options);  

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	}

	@Test
	public void GetCertificate() throws Exception {
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


		/*Select TrMode= new Select(driver.findElement(By.id("edit-select-mode")));
		TrMode.selectByVisibleText("Online Training");
		System.out.println("Select Training Mode");
		Thread.sleep(5000);

		Select TrType= new Select(driver.findElement(By.id("edit-select-type")));
		TrType.selectByVisibleText("Operational");
		System.out.println("Select Training Type");
		Thread.sleep(5000);

		Select Region= new Select(driver.findElement(By.id("edit-select-region")));
		Region.selectByVisibleText("India");
		System.out.println("Selected Region AS India");
		Thread.sleep(5000);

		Select Site= new Select(driver.findElement(By.id("edit-select-site")));
		Site.selectByVisibleText("Mohali");
		System.out.println("Selected Site as Mohali");
		Thread.sleep(5000);

		Select Client= new Select(driver.findElement(By.id("edit-select-client")));
		Client.selectByVisibleText("VIL");
		System.out.println("Selected Client as VIL");
		Thread.sleep(3000);

		WebElement FromDate= driver.findElement(By.id("edit-select-from-date"));
		String dateString= "2020-01-01";
		datePickerByJs1(driver,dateString,FromDate);
		System.out.println("Selected Start Date");
		Thread.sleep(3000);

		WebElement ToDate= driver.findElement(By.id("edit-select-to-date"));
		String dateString2= "2021-12-01";
		datePickerByJs2(driver,dateString2,ToDate);
		System.out.println("Selected End Date");
		Thread.sleep(8000); */
		  
		  
		  
		  
		  
		  
		  
		  

	/*	driver.navigate().to("https://lmstraining.isonxperiences.com/certificates-details");
		Thread.sleep(8000);

		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("Mock Testing Airtel May 2021");
		Thread.sleep(4000);

		driver.findElement(By.linkText("Proceed")).click();
		System.out.println("Clicked On Proceed Button");
		Thread.sleep(3000);

		driver.findElement(By.linkText("View")).click();
		System.out.println("Clicked on View Button to view Certificate");
		Thread.sleep(8000);

		driver.findElement(By.xpath("//span[@class='ui-button-icon ui-icon ui-icon-closethick']")).click();
		Thread.sleep(5000);

		driver.findElement(By.linkText("Download")).click();
		Thread.sleep(4000);
		System.out.println("Clicked on Download Button to download Certificate");
		Thread.sleep(12000);   */

				
		driver.findElement(By.linkText("Logout")).click();
		System.out.println("Logged Out");
		Thread.sleep(3000); 

	}

	public static void datePickerByJs1(WebDriver driver,String date,WebElement FromDate)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','"+date+"')", FromDate);

	}

	public static void datePickerByJs2(WebDriver driver,String date,WebElement ToDate)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','"+date+"')", ToDate);

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