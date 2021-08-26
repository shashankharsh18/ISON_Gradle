import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.firefox.FirefoxProfile;

import Utility.ConfigReader;



public class myCalendarDashboard {
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
	public void testmyCalendarDashboard() throws Exception {
		ConfigReader config= new ConfigReader(); 


		driver.get(config.getApplicationUrl());

		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(config.getSGAUserName());
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

		driver.findElement(By.linkText("My Dashboard")).click();
		Thread.sleep(8000);

		driver.findElement(By.linkText("My Calendar")).click(); //My Calendar
		Thread.sleep(8000);

		driver.switchTo().frame("iFrameResizer0");
		Thread.sleep(5000);

		//Calendar Reset Filters Navigation

		WebElement element = driver.findElement(By.xpath("//span[@class='lever']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", element);

		System.out.println("Clicked from plan");
		Thread.sleep(10000);

		WebElement element2 = driver.findElement(By.xpath("//span[@class='lever']"));
		JavascriptExecutor executor2 = (JavascriptExecutor)driver;
		executor2.executeScript("arguments[0].click()", element2);


		System.out.println("Clicked from actual");
		Thread.sleep(10000);

		driver.switchTo().defaultContent();
		Thread.sleep(3000);

		System.out.println("My Calendar dashboard details displayed successfully  ..!!!");

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
