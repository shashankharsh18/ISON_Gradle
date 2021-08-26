import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import Utility.ConfigReader;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class deactivateActivateUserRAdmin {
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
	public void testDeactivateActivateUser() throws Exception {
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

		// Click On Send Anyway button if the prompt is displaying
		/*boolean var=  driver.findElement(By.xpath("//button[@id='proceed-button']")).isDisplayed();
   		System.out.println(var);
   		if (var==true)
   		{
   			 driver.findElement(By.xpath("//button[@id='proceed-button']")).click();
   		}
   		  Thread.sleep(8000); */


		Actions act = new Actions(driver);  	//Move to Left mouse over bar
		WebElement navigate =driver.findElement(By.xpath("//li[@class='menuparent menu-item menu-item--expanded'][1]"));
		act.moveToElement(navigate).build().perform();
		System.out.println("Moved to Left Navigation MenuBar");
		Thread.sleep(10000);

		driver.findElement(By.xpath("//a[@title='User Management']")).click();
		System.out.println("Clicked On User Management");
		Thread.sleep(5000);

		driver.findElement(By.xpath("//a[@title='Manage Users']")).click();
		System.out.println("Clicked On Manage Users");
		Thread.sleep(30000);


		WebElement regionDropDown1 = driver.findElement(By.name("select_region"));
		Thread.sleep(3000);
		Select dropdown1 = new Select(regionDropDown1);
		Thread.sleep(5000);

		dropdown1.selectByValue("1");
		Thread.sleep(5000);

		WebElement siteDropDown1 = driver.findElement(By.name("select_site"));
		Thread.sleep(5000);
		Select dropdown2 = new Select(siteDropDown1);
		Thread.sleep(5000);
		dropdown2.selectByValue("1");
		Thread.sleep(8000);

		driver.findElement(By.id("edit-search-user-text")).sendKeys("ISBL02231");
		Thread.sleep(2000);
		driver.findElement(By.id("edit-search")).click();
		Thread.sleep(2000);

		//Deactivate code

		WebElement element1=driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")); //Deactivate User
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element1);
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@id='edit-submit']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		//driver.findElement(By.name("op")).click(); //Deactivate Users button
		//Thread.sleep(3000);
		System.out.println("Deactivated an User!!!");	
		Thread.sleep(30000);
		
		WebElement regionDropDown2 = driver.findElement(By.name("select_region"));  
		Thread.sleep(6000);
		Select dropdown3 = new Select(regionDropDown2);
		Thread.sleep(3000);
		dropdown3.selectByValue("1");
		Thread.sleep(5000);

		WebElement siteDropDown2 = driver.findElement(By.name("select_site"));  
		Thread.sleep(3000);
		Select dropdown4 = new Select(siteDropDown2);
		Thread.sleep(3000);
		dropdown4.selectByValue("1");
		Thread.sleep(5000);

		WebElement statusDropDown = driver.findElement(By.name("select_status"));  
		Thread.sleep(3000);
		Select dropdown = new Select(statusDropDown);
		Thread.sleep(3000);
		dropdown.selectByValue("0");
		Thread.sleep(5000);

		driver.findElement(By.id("edit-search-user-text")).sendKeys("ISBL02231");
		Thread.sleep(2000);
		driver.findElement(By.id("edit-search")).click();
		Thread.sleep(2000);

		//Activate code
		WebElement element2=driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")); //Activate User
		JavascriptExecutor executor3 = (JavascriptExecutor)driver;
		executor3.executeScript("arguments[0].click();", element2);

		driver.findElement(By.xpath("//input[@id='edit-submit']")).sendKeys(Keys.ENTER);
		Thread.sleep(10000);

		//driver.findElement(By.name("op")).click(); //Activate Users button
		//Thread.sleep(3000);
		System.out.println("Activated an User!!!");
		Thread.sleep(5000);

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
