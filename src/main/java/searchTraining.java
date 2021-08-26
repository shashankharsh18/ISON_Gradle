
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

public class searchTraining {
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
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
	}

	@Test
	public void testSearchTraining() throws Exception {
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


		WebElement menuBar1 = driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[3]/div/ul/li[4]/a"));
		Actions act1 = new Actions (driver);
		act1.moveToElement(menuBar1).perform();
		driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[3]/div/ul/li[4]/a")).click(); //Trainings
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[3]/div/ul/li[4]/ul/li[1]/a")).click(); //Manage Offline & Online Trainings                             
		Thread.sleep(30000);


		driver.findElement(By.name("select_mode")).click();
		new Select(driver.findElement(By.name("select_mode"))).selectByVisibleText("Online Training");
		driver.findElement(By.name("select_mode")).click();
		Thread.sleep(5000);  
		driver.findElement(By.name("select_type")).click();
		new Select(driver.findElement(By.name("select_type"))).selectByVisibleText("Operational");
		driver.findElement(By.name("select_type")).click();
		Thread.sleep(5000);
		driver.findElement(By.name("select_cat")).click();
		new Select(driver.findElement(By.name("select_cat"))).selectByVisibleText("Briefing Report");
		driver.findElement(By.name("select_cat")).click();
		Thread.sleep(5000);
		driver.findElement(By.name("select_region")).click();
		new Select(driver.findElement(By.name("select_region"))).selectByVisibleText("India");
		driver.findElement(By.name("select_region")).click();


		driver.findElement(By.xpath("//input[@class='search-members-button button js-form-submit form-submit form-control']")).click();
		Thread.sleep(3000);
		System.out.println("Searched Training in the search bar");


		System.out.println("Related Trainings Found for the Filters selected..!!!");
		Thread.sleep(8000); 

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
