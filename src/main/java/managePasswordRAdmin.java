
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import Utility.ConfigReader;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class managePasswordRAdmin {
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
	public void testManagePassword() throws Exception {
		ConfigReader config= new ConfigReader();
		driver.get(config.getApplicationUrl());

		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(config.getRAdminUserName());
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(config.getISONPassword());
		driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
		Thread.sleep(20000);

		boolean var7=  driver.findElement(By.xpath("//li[@class='menuparent menu-item menu-item--expanded'][1]")).isDisplayed();
		System.out.println(var7);
		if (var7==true)
		{
			Actions act = new Actions(driver);  	//Move to Left mouse over bar
			WebElement navigate =driver.findElement(By.xpath("//li[@class='menuparent menu-item menu-item--expanded'][1]"));
			act.moveToElement(navigate).build().perform();
			System.out.println("moved to Left Navigation Menu");
			Thread.sleep(5000);
		}

		driver.findElement(By.linkText("User Management")).click();
		System.out.println("Clicked on user management");
		Thread.sleep(5000);

		driver.findElement(By.linkText("Manage Passwords")).click(); //Manage Password
		System.out.println("Clicked on manage password");
		Thread.sleep(60000);


		driver.findElement(By.xpath("//*[@placeholder='Search Here']")).sendKeys("ISRO2398");
		Thread.sleep(3000);

		driver.findElement(By.xpath("//text()[contains(.,'Change Password')]/ancestor::a[1]")).click();
		System.out.println("User Selected and clicked on Change the Password Button ...!!!");
		Thread.sleep(5000);


		driver.findElement(By.name("new_password")).sendKeys("ison543");
		System.out.println("Given new password");
		Thread.sleep(5000);


		driver.findElement(By.name("confirm_new_password")).sendKeys("ison543");
		System.out.println("Given Confirmation password");
		Thread.sleep(5000);

		driver.findElement(By.name("op")).click();
		Thread.sleep(15000);

		//driver.findElement(By.cssSelector("span.ui-button-icon.ui-icon.ui-icon-closethick")).click();//Close pop-up window
		//Thread.sleep(3000);

		System.out.println("User Password Changed Successfully..!!!");
		Thread.sleep(2000); 

		driver.findElement(By.linkText("Logout")).click();
		//driver.get("https://bi-3.axxonet.com/isonlms/"); 
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