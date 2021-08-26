import java.util.HashMap;
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
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class MYCertificateTrainer {
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
	public void CertificateTwo() throws Exception {
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
		  Thread.sleep(8000);*/

		Actions act = new Actions(driver);  	//Move to Left mouse over bar
		WebElement navigate =driver.findElement(By.xpath("//li[@class='menuparent menu-item menu-item--expanded'][1]"));
		act.moveToElement(navigate).build().perform();
		System.out.println("moved to Left Navigation bar");
		Thread.sleep(5000);

		//click on Certificate
		WebElement CerClick = driver.findElement(By.linkText("Certificate"));
		CerClick.click();
		System.out.println("Clicked on Certificate");
		Thread.sleep(4000);

		
		//Click MyCertificate
		driver.findElement(By.linkText("My Certificate")).click();
		System.out.println("Clicked on MYCertificate");
		Thread.sleep(16000);

		/*Select TrMode= new Select(driver.findElement(By.id("edit-select-mode")));
		  TrMode.selectByVisibleText("Online Training");
		  System.out.println("Select Training Mode");
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
		  Thread.sleep(5000);

		  WebElement FromDate= driver.findElement(By.id("edit-select-from-date"));
		  FromDate.click();
		  Thread.sleep(2000);

		  FromDate.clear();
		  Thread.sleep(2000);

		  WebElement FromDate1= driver.findElement(By.id("edit-select-from-date"));
		  String dateString= "2020-01-01";
		  datePickerByJs1(driver,dateString,FromDate1);
		  System.out.println("Selected Start Date");
		  Thread.sleep(8000);

		  WebElement ToDate= driver.findElement(By.id("edit-select-to-date"));
		  String dateString2= "2020-12-01";
		  datePickerByJs2(driver,dateString2,ToDate);
		  System.out.println("Selected End Date");
		  Thread.sleep(8000);*/

		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("25 Feb Assessment");
		System.out.println("seacrch Training in Search Option");
		Thread.sleep(8000);

		driver.findElement(By.linkText("View")).click();
		System.out.println("Clicked on View Button to view Certificate");
		Thread.sleep(8000);

		driver.findElement(By.xpath("//span[@class='ui-button-icon ui-icon ui-icon-closethick']")).click();
		Thread.sleep(5000);

		driver.findElement(By.linkText("Download")).click();
		Thread.sleep(4000);
		System.out.println("Clicked on Download Button to download Certificate");
		Thread.sleep(8000);


		driver.findElement(By.linkText("Logout")).click();
		System.out.println("Logged Out");
		//driver.get("https://bi-3.axxonet.com/isonlms/"); 
		Thread.sleep(3000);


	}

	public static void datePickerByJs1(WebDriver driver,String date,WebElement FromDate1)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','"+date+"')", FromDate1);

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