
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmailNotification {
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
	public void testEditUser() throws Exception {
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
		
		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		WebElement TraineeTab = driver.findElement(By.xpath("//a[@title='Notifications']"));
		js3.executeScript("arguments[0].scrollIntoView();", TraineeTab);
		Thread.sleep(2000);

		WebElement TraineeClick;
		WebDriverWait wait1=new WebDriverWait(driver, 10);
		TraineeClick = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Notifications']")));
		System.out.println("Clicked On Notifications");
		TraineeClick.click();

		//driver.findElement(By.linkText("Send Notifications")).click(); //Send Notifications
		driver.findElement(By.xpath("//a[@title='Send Notifications']")).click();
		System.out.println("Clicked On Send Notifications");
		Thread.sleep(18000);
		
		driver.findElement(By.name("select_region")).click();
		new Select(driver.findElement(By.name("select_region"))).selectByVisibleText("India");
		driver.findElement(By.name("select_region")).click();
		System.out.println("Slected Region");
		Thread.sleep(5000);

		WebElement mullist1 = driver.findElement(By.id("edit-select-site"));
		Select mulopt = new Select(mullist1);
		mulopt.selectByVisibleText("Bangalore");
		System.out.println("Slected Site");
		Thread.sleep(5000);

		WebElement mullist2 = driver.findElement(By.id("edit-select-client"));
		Select mulopt1 = new Select(mullist2);
		mulopt1.selectByVisibleText("VIL");
		System.out.println("Slected Client");
		Thread.sleep(5000);
		
		driver.findElement(By.name("select_role")).click();
		new Select(driver.findElement(By.name("select_role"))).selectByVisibleText("Trainee");
		driver.findElement(By.name("select_role")).click();
		System.out.println("Selected Role");
		Thread.sleep(10000);
		
		boolean var8=  driver.findElement(By.id("edit-submit")).isDisplayed();
		System.out.println(var8);
		if (var8==true)
		{
			driver.findElement(By.id("edit-submit")).click(); //Get Users
			System.out.println("Clicked on Get User Button");
			Thread.sleep(15000);
		}

		

		Select Users = new Select(driver.findElement(By.xpath("(//select[@id='Array-available'])[3]")));
		Users.selectByVisibleText("Shrishail Ellure (ISCH00684)");
		System.out.println("Selected Trainer AS Shrishail");
		Thread.sleep(6000);
		
		WebElement element = driver.findElement(By.xpath("(//li[@class='multiselect-add'])[3]"));
		Thread.sleep(3000);


		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", element);

		Thread.sleep(5000);

		WebElement Invite;
		WebDriverWait wait4=new WebDriverWait(driver, 10);
		Invite = wait4.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-select-subject")));
		System.out.println("Ist Invite Message");
		Invite.sendKeys("Email from ISON LMS Automation");

		//driver.findElement(By.id("edit-select-subject")).sendKeys("New Training Invite");
		//Thread.sleep(5000);

		//CKE EDITOR CODING


		WebElement iframe3 = driver.findElement(By.xpath("//div[@id='cke_1_contents']/iframe"));
		driver.switchTo().frame(iframe3);
		WebElement web3=driver.findElement(By.xpath("//html[@dir='ltr']"));
		web3.click();
		Thread.sleep(2000);
		Actions act2=new Actions(driver);
		act2.moveToElement(web3).sendKeys("Automation Email").build().perform();
		Thread.sleep(2000);


		driver.switchTo().defaultContent();
		Thread.sleep(3000);
		
		driver.findElement(By.id("edit-copynotifymail")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@id='edit-submit--2']")).click(); //Send
		Thread.sleep(15000);
		System.out.println("Clicked On Save Button and sent Notification");
		Thread.sleep(3000);

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