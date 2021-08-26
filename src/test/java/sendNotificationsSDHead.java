
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import Utility.ConfigReader;


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

public class sendNotificationsSDHead {
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
	public void testSendNotifications() throws Exception {
		ConfigReader config= new ConfigReader();
	  driver.get(config.getApplicationUrl());
	  
	  driver.findElement(By.linkText("Login")).click();
	  driver.findElement(By.id("username")).click();
	  driver.findElement(By.id("username")).clear();
	  driver.findElement(By.id("username")).sendKeys(config.getSDHeadUserName());
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
		Thread.sleep(2000);
		System.out.println("Selected Region");
		Thread.sleep(5000);

		WebElement mullist1 = driver.findElement(By.id("edit-select-site"));
		Select mulopt = new Select(mullist1);
		mulopt.selectByVisibleText("Bangalore");
		Thread.sleep(2000);
		System.out.println("Selected Site");
		Thread.sleep(4000);

		WebElement mullist2 = driver.findElement(By.id("edit-select-client"));
		Select mulopt1 = new Select(mullist2);
		mulopt1.selectByVisibleText("VIL");
		Thread.sleep(2000);
		System.out.println("Selected Client");
		Thread.sleep(4000);

		driver.findElement(By.name("select_role")).click();
		new Select(driver.findElement(By.name("select_role"))).selectByVisibleText("Trainee");
		driver.findElement(By.name("select_role")).click();
		Thread.sleep(2000);
		System.out.println("Selected Role");
		Thread.sleep(10000);

		driver.findElement(By.id("edit-submit")).click(); //Get Users
		System.out.println("Clicked on Get User Button");
		Thread.sleep(12000);

		Select Users = new Select(driver.findElement(By.xpath("(//select[@id='Array-available'])[3]")));
		Users.selectByVisibleText("Shrishail Ellure (ISCH00684)");
		System.out.println("Selected Trainee ASShrishail Ellure");
		Thread.sleep(3000);

		WebElement element = driver.findElement(By.xpath("(//li[@class='multiselect-add'])[3]"));

		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", element);

		Thread.sleep(5000);

		WebElement Invite;
		WebDriverWait wait4=new WebDriverWait(driver, 10);
		Invite = wait4.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-select-subject")));
		System.out.println("Ist Invite Message");
		Invite.sendKeys("New Training Invite by SD Head Role !!");

		//driver.findElement(By.id("edit-select-subject")).sendKeys("New Training Invite");
		//Thread.sleep(5000);

		//CKE EDITOR CODING


		WebElement iframe3 = driver.findElement(By.xpath("//div[@id='cke_1_contents']/iframe"));
		driver.switchTo().frame(iframe3);
		WebElement web3=driver.findElement(By.xpath("//html[@dir='ltr']"));
		web3.click();
		Thread.sleep(2000);
		Actions act2=new Actions(driver);
		act2.moveToElement(web3).sendKeys("New Training Invite").build().perform();
		Thread.sleep(2000);


		driver.switchTo().defaultContent();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@id='edit-submit--2']")).click(); //Send
		Thread.sleep(15000);
		System.out.println("Clicked On Save Button and sent Notification");
		Thread.sleep(3000);

		driver.findElement(By.linkText("Logout")).click();

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
