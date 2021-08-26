

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class EditPoll {
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
	public void testEditPoll() throws Exception {
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

		WebElement menuBar1 = driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[3]/div/ul/li[8]/a"));
		Actions act1 = new Actions (driver);
		act1.moveToElement(menuBar1).perform();
		System.out.println("MOved to Navigation Menu");
		Thread.sleep(6000);

		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		WebElement TraineeTab = driver.findElement(By.xpath("//a[@title='Poll / Survey']"));
		js3.executeScript("arguments[0].scrollIntoView();", TraineeTab);
		Thread.sleep(2000);

		WebElement TraineeClick;
		WebDriverWait wait1=new WebDriverWait(driver, 10);
		TraineeClick = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Poll / Survey']")));
		System.out.println(" Clicked Poll / Survey");
		TraineeClick.click();

		driver.findElement(By.xpath("//span[contains(text(),'Manage Poll/Survey')]")).click(); //Manage Users
		System.out.println("Clicked On Manage Users");
		Thread.sleep(15000);


		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("poll20");
		Thread.sleep(5000);

		driver.findElement(By.linkText("Edit")).click();
		Thread.sleep(6000);

		Select duration = new Select(driver.findElement(By.id("edit-duration")));
		duration.selectByValue("345600");
		Thread.sleep(3000);

		driver.findElement(By.cssSelector("#edit-submit")).click();
		Thread.sleep(5000);

		//4th Question in Poll/Survey.

		driver.findElement(By.linkText("Add New Question")).click();  //Add New Button
		Thread.sleep(5000);

		
		String date = new SimpleDateFormat("yyyMMddHHmmssSS").format(new Date());
		
		driver.findElement(By.xpath("//input[@id='edit-question-0-value']")).sendKeys("Curret Captain of Indian Cricket Team is" +date);  	  
		System.out.println("User Created a Poll Question...");
		Thread.sleep(5000);

		//Entering the Choices
		driver.findElement(By.xpath("//input[@name='choice[0][choice]']")).sendKeys("Rohit Sharma");
		System.out.println("User entered choice as YES");
		Thread.sleep(4000);

		driver.findElement(By.xpath("//input[@id='edit-choice-add-more']")).sendKeys(Keys.ENTER);//Add another item button
		Thread.sleep(4000);

		driver.findElement(By.xpath("//input[@name='choice[1][choice]']")).sendKeys("Virat Kholi");
		System.out.println("User entered choice as NO");
		Thread.sleep(4000);

		driver.findElement(By.xpath("//input[@id='edit-submit']")).sendKeys(Keys.ENTER);//Save button
		Thread.sleep(20000);
		
		System.out.println("Poll/Survey Question created Successfully..!!!");
		Thread.sleep(8000); 

		WebElement element22 = driver.findElement(By.linkText("Go to main content"));
		JavascriptExecutor executor22 = (JavascriptExecutor)driver;
		executor22.executeScript("arguments[0].click();", element22);
		Thread.sleep(8000); 
		
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("poll20");
		Thread.sleep(5000);
		
		driver.findElement(By.linkText("Overview")).click();  //Click on Overview Button
		Thread.sleep(4000);
		
		driver.findElement(By.linkText("Back")).click(); 
		Thread.sleep(8000);
		
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("poll20");
		Thread.sleep(5000);
		
		driver.findElement(By.linkText("Edit")).click();  //edit for 2nd time
		Thread.sleep(6000);
		
		driver.findElement(By.linkText("Skip Continue")).click();  //Skip Continue
		Thread.sleep(6000);
		
		WebElement element2 = driver.findElement(By.linkText("Go to main content"));
		JavascriptExecutor executor2 = (JavascriptExecutor)driver;
		executor2.executeScript("arguments[0].click();", element2);
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