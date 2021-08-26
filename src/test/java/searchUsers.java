
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

public class searchUsers {
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
		//coptions.addArguments("--headless");

		driver = new ChromeDriver(coptions);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
	}

	@Test
	public void testSearchUsers() throws Exception {

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



		WebElement menuBar1 = driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[3]/div/ul/li[3]/a"));
		Actions act1 = new Actions (driver);
		act1.moveToElement(menuBar1).perform();
		driver.findElement(By.linkText("User Management")).click();
		System.out.println("Clicked On UserManagement");
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[3]/div/ul/li[3]/ul/li[1]/a")).click(); //Manage Users
		Thread.sleep(5000);

		WebElement regionDropDown1 = driver.findElement(By.name("select_region"));  
		Select dropdown1 = new Select(regionDropDown1);
		dropdown1.selectByValue("1");
		System.out.println("Selected Region As India");
		Thread.sleep(5000);

		WebElement siteDropDown1 = driver.findElement(By.name("select_site"));  
		Select dropdown2 = new Select(siteDropDown1);
		dropdown2.selectByValue("1");
		System.out.println("Selected Site As Bangalore");
		Thread.sleep(5000);

		//ADD SEARCH CODE
		driver.findElement(By.id("edit-search-user-text")).sendKeys("Ajay");
		driver.findElement(By.id("edit-search")).click();
		System.out.println("Search text as Ajay..!!!");
		String text = driver.findElement(By.id("edit-search-user-text")).getAttribute("value");
		System.out.println("Entered text is: " + text);
		Thread.sleep(5000);

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
