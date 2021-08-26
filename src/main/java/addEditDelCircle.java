import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import Utility.ConfigReader;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class addEditDelCircle {
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
	public void testAddEditDelCircle() throws Exception {
		ConfigReader config= new ConfigReader();
		driver.get(config.getApplicationUrl());

		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(config.getSGAUserName());
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(config.getSGAUserPassword());
		driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
		Thread.sleep(60000);

		/*
		boolean var7=  driver.findElement(By.xpath("//nav[@class='pcoded-navbar menu-light navbar-collapsed']")).isDisplayed();
		System.out.println(var7);
		if (var7==true)
		{
			Actions act = new Actions(driver);  	//Move to Left mouse over bar
			WebElement navigate =driver.findElement(By.xpath("//nav[@class='pcoded-navbar menu-light navbar-collapsed']"));
			act.moveToElement(navigate).build().perform();
			System.out.println("moved to Left Navigation Menu");
			Thread.sleep(5000);
		}

		driver.findElement(By.linkText("Demography")).click();
		Thread.sleep(5000);

		driver.findElement(By.linkText("Circles")).click();
		Thread.sleep(20000);

		//Add Circle
		System.out.println("Started to Add Circle !!");
		driver.findElement(By.linkText("Add")).click();
		Thread.sleep(5000);

		driver.findElement(By.name("circle_name")).sendKeys("Karunadu");
		Thread.sleep(5000);

		//Selected Client
		Select Client = new Select(driver.findElement(By.xpath("//select[@data-drupal-selector='edit-client-name']")));
		Client.selectByValue("7");
		Thread.sleep(3000);

		//Selected Site
		Select Site = new Select(driver.findElement(By.xpath("//select[@class='multiselect-available form-multiselect form-control']")));
		Site.selectByValue("1");
		Thread.sleep(3000);

		driver.findElement(By.xpath("//li[@class='multiselect-add']")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//textarea[@data-drupal-selector='edit-description']")).sendKeys("CicleOne");
		Thread.sleep(4000);

		driver.findElement(By.xpath("//input[@type='submit']")).click(); //Save
		Thread.sleep(2000);
		System.out.println("Circle added Successfully !!");
		Thread.sleep(25000); 

		//Edit Circle
		System.out.println("Started Editing the Circle !!");
		Thread.sleep(4000);
		
		boolean var1= driver.findElement(By.xpath("//input[@type='search']")).isDisplayed();
		System.out.println(var1);
		if (var1==true)
		{
			driver.findElement(By.xpath("//input[@type='search']")).sendKeys("Karunadu");
		}
		Thread.sleep(8000);

		//driver.findElement(By.xpath("//input[@type='search']")).sendKeys("Karunadu");
		//Thread.sleep(2000);

		driver.findElement(By.linkText("Edit")).click();
		Thread.sleep(4000);

		driver.findElement(By.name("description")).click();
		Thread.sleep(2000);

		driver.findElement(By.name("description")).clear();
		Thread.sleep(2000);

		driver.findElement(By.name("description")).sendKeys("CircleTwo");
		System.out.println("Edited the Circle description field ");
		Thread.sleep(5000);

		driver.findElement(By.xpath("//input[@type='submit']")).click(); //Save
		Thread.sleep(20000);
		
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("Karunadu");
		Thread.sleep(25000);

		//Delete Circle

		boolean var3= driver.findElement(By.xpath("//text()[contains(.,'Search')]/ancestor::label[1]")).isDisplayed();
		System.out.println(var3);
		if (var3==true)
		{
			driver.findElement(By.xpath("//text()[contains(.,'Search')]/ancestor::label[1]")).click();
		}
		Thread.sleep(8000);

		boolean var4= driver.findElement(By.linkText("Delete")).isDisplayed();
		System.out.println(var4);
		if (var4==true)
		{
			driver.findElement(By.linkText("Delete")).sendKeys(Keys.ENTER);
			Thread.sleep(3000);
		}
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(3000);
		
		*/
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
