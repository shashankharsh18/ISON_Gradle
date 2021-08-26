
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import Utility.ConfigReader;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;


public class CopyAssessment {
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
	public void testCopyAssessment() throws Exception {
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


		WebElement menuBar1 =driver.findElement(By.xpath("//li[@class='menuparent menu-item menu-item--expanded'][1]"));
		Actions act1 = new Actions (driver);
		act1.moveToElement(menuBar1).build().perform();
		System.out.println("Moved to Left Navigation Bar");
		Thread.sleep(8000);

		//click on Training n Assessment
		WebElement training = driver.findElement(By.xpath("//a[@title='Trainings']"));
		training.click();
		Thread.sleep(3000);

		driver.findElement(By.linkText("Manage Assessment")).click();
		System.out.println("Clicked on Manage Assessment Button");
		Thread.sleep(6000);

		//Search the Assessment to Edit
		String date = new SimpleDateFormat("yyyMMddHHmmssSS").format(new Date());
		//driver.findElement(By.xpath("//input[@type='search']")).sendKeys("March Assessment" +date);

		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("SGA Assessment");
		Thread.sleep(3000);

		driver.findElement(By.linkText("Copy")).click();
		Thread.sleep(20000);

		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		js3.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(3000);


		Select Certificate= new Select(driver.findElement(By.id("edit-certificate-display")));
		Certificate.selectByValue("1");
		Thread.sleep(5000);


		Select CertificateType= new Select(driver.findElement(By.id("edit-certificate-type")));
		CertificateType.selectByValue("2");
		Thread.sleep(3000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(3000);

		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js2.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(3000);


		driver.findElement(By.id("edit-submit")).click();
		System.out.println("Clicked on Save and Created Assessment");
		Thread.sleep(60000);
		
        /*
		//Search Assessment to add Participant
		//driver.findElement(By.xpath("//input[@type='search']")).sendKeys("SGA Assessment" +date);
		Thread.sleep(3000);

		//driver.findElement(By.linkText("Add Participant")).click();
		WebElement ele2= driver.findElement(By.linkText("Add Participant"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele2);
		Thread.sleep(6000);

		Select Region2 = new Select(driver.findElement(By.id("edit-select-region")));
		Region2.selectByVisibleText("India");
		Thread.sleep(2000);

		Select Site2 = new Select(driver.findElement(By.id("edit-select-site")));
		Site2.selectByVisibleText("Bangalore");
		Thread.sleep(2000);

		Select Client2 = new Select(driver.findElement(By.id("edit-select-client")));
		Client2.selectByVisibleText("VIL");
		Thread.sleep(2000);

		Select User1 = new Select(driver.findElement(By.id("Array-available")));
		User1.selectByVisibleText("Manish Kumar (13AZSB602)");
		Thread.sleep(2000);

		

		driver.findElement(By.xpath("//li[@class='multiselect-add']")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@id='edit-submit']")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		System.out.println("Trainees added to the assessment");  
		*/


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