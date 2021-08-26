
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

public class addEditDelRegion {
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
	public void testAddEditDelRegion() throws Exception {
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
		/*	boolean var=  driver.findElement(By.xpath("//button[@id='proceed-button']")).isDisplayed();
		System.out.println(var);
		if (var==true)
		{
			 driver.findElement(By.xpath("//button[@id='proceed-button']")).click();
		}
		  Thread.sleep(8000); */


		WebElement menuBar1 = driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[3]/div/ul/li[3]/a"));
		Actions act1 = new Actions (driver);
		act1.moveToElement(menuBar1).perform();
		driver.findElement(By.linkText("Demography")).click();
		//Thread.sleep(500);
		driver.findElement(By.linkText("Regions")).click();
		//Thread.sleep(500);
		System.out.println("Started to Add Region!!");
		driver.findElement(By.linkText("Add")).click();
		//Thread.sleep(500);
		driver.findElement(By.name("location_name")).click();
		driver.findElement(By.name("location_name")).clear();
		driver.findElement(By.name("location_name")).sendKeys("myRegions");
		//Thread.sleep(200);
		driver.findElement(By.name("location_code")).click();
		driver.findElement(By.name("location_code")).clear();
		driver.findElement(By.name("location_code")).sendKeys("21");
		//Thread.sleep(200);
		driver.findElement(By.name("description")).click();
		driver.findElement(By.name("description")).clear();
		driver.findElement(By.name("description")).sendKeys("myRegions desc");
		//Thread.sleep(200);
		driver.findElement(By.name("op")).click();
		//Thread.sleep(300);
		System.out.println("Region created Successfully !!");
		//Thread.sleep(800);


		//Edit Region
		System.out.println("Started to Edit Region !!");
		driver.findElement(By.xpath("//*[@placeholder='Search Here']")).sendKeys("myRegions");
		driver.findElement(By.xpath("//text()[contains(.,'Search')]/ancestor::label[1]")).click();
		driver.findElement(By.xpath("//text()[contains(.,'Edit')]/ancestor::a[1]")).click();
		//driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div/div[2]/div/div/div/div/div[2]/div/table/tbody/tr[5]/td[5]/a[1]")).click();
		driver.findElement(By.name("description")).click();
		driver.findElement(By.name("description")).clear();
		driver.findElement(By.name("description")).sendKeys("myRegionsdescription");
		//Thread.sleep(500);    
		driver.findElement(By.name("op")).click();
		System.out.println("Edit Region was Successful!!");
		//Thread.sleep(500);


		//Delete Region
		System.out.println("Started Delete Region !!");
		driver.findElement(By.xpath("//*[@placeholder='Search Here']")).sendKeys("myRegions");
		driver.findElement(By.xpath("//text()[contains(.,'Search')]/ancestor::label[1]")).click();
		driver.findElement(By.xpath("//text()[contains(.,'Delete')]/ancestor::a[1]")).click();
		//driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div/div[2]/div/div/div/div/div[2]/div/table/tbody/tr[5]/td[5]/a[2]")).click(); //Delete
		//Thread.sleep(300);
		driver.findElement(By.id("edit-submit")).click(); //Delete it
		System.out.println("Delete Region was Successful!!");
		Thread.sleep(3000);

		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(5000);


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