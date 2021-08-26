
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

public class sendNotifications {
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
		//Dimension d = new Dimension(1382,744); 
		//driver.manage().window().setSize(d);  

		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	}

	@Test
	public void testSendNotifications() throws Exception {
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

		//WebElement menuBar1 = driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[3]/div/ul/li[10]/a"));
		WebElement menuBar1 =driver.findElement(By.xpath("//li[@class='menuparent menu-item menu-item--expanded'][1]"));
		Actions act1 = new Actions (driver);
		act1.moveToElement(menuBar1).build().perform();
		System.out.println("Moved to Left Navigation Bar");
		Thread.sleep(8000);

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
		new Select(driver.findElement(By.name("select_region"))).selectByVisibleText("All");
		driver.findElement(By.name("select_region")).click();
		System.out.println("Slected Region");
		Thread.sleep(5000);

		//Selecting Four Sites
		WebElement mullist1 = driver.findElement(By.id("edit-select-site"));
		Select mulopt = new Select(mullist1);
		mulopt.selectByVisibleText("Bangalore");
		Thread.sleep(4000);

		//Site 2
		Select Sites= new Select(driver.findElement(By.id("edit-select-site")));
		Sites.selectByVisibleText("Botswana");
		System.out.println("Selected Site as Botswana");
		Thread.sleep(4000);

		//Site 3
		Select Sites3= new Select(driver.findElement(By.id("edit-select-site")));
		Sites3.selectByVisibleText("Cairo");
		System.out.println("Selected Site as Cairo");
		Thread.sleep(4000);

		//Site 4
		Select Sites4= new Select(driver.findElement(By.id("edit-select-site")));
		Sites4.selectByVisibleText("Dehradun");
		System.out.println("Selected Site as Cairo");
		Thread.sleep(4000);

		System.out.println("Selected Four Sites");
		Thread.sleep(5000);

		//Selecting Four Clients
		WebElement mullist2 = driver.findElement(By.id("edit-select-client"));
		Select mulopt1 = new Select(mullist2);
		mulopt1.selectByVisibleText("VIL");
		System.out.println("Slected Client as VIL");
		Thread.sleep(5000);

		//Clients 2
		Select Clients= new Select(driver.findElement(By.id("edit-select-client")));
		Clients.selectByVisibleText("SBI");
		System.out.println("Selected Client as SBI");
		Thread.sleep(4000);

		//Clients 3
		Select Clients3= new Select(driver.findElement(By.id("edit-select-client")));
		Clients3.selectByVisibleText("Vodafone");
		System.out.println("Selected Client as Vodafone");
		Thread.sleep(4000);

		//Clients 4
		Select Clients4= new Select(driver.findElement(By.id("edit-select-client")));
		Clients4.selectByVisibleText("Zomato");
		System.out.println("Selected Client as Zomato");
		Thread.sleep(4000);

		driver.findElement(By.name("select_role")).click();
		new Select(driver.findElement(By.name("select_role"))).selectByVisibleText("Trainer");
		driver.findElement(By.name("select_role")).click();
		System.out.println("Selected Role");
		Thread.sleep(10000);

		driver.findElement(By.id("edit-submit")).click(); //Get Users
		System.out.println("Clicked on Get User Button");
		Thread.sleep(15000);

		Select Users = new Select(driver.findElement(By.xpath("(//select[@id='Array-available'])[3]")));
		Users.selectByVisibleText("Abhinav Singh (ISDU09894)");
		System.out.println("Selected Trainer AS Abhinav");
		Thread.sleep(6000);
		
		/*Select Users2 = new Select(driver.findElement(By.xpath("(//select[@id='Array-available'])[3]")));
		Users2.selectByVisibleText("Abhishek Chand (ISDU20111))");
		System.out.println("Selected Trainee AS Abhishek");
		Thread.sleep(3000);
		
		Select Users3 = new Select(driver.findElement(By.xpath("(//select[@id='Array-available'])[3]")));
		Users3.selectByVisibleText("Anita Kumari (ISDU15767))");
		System.out.println("Selected Trainee AS Akhilesh");
		Thread.sleep(3000);  */
		
		

		WebElement element = driver.findElement(By.xpath("(//li[@class='multiselect-add'])[3]"));

		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", element);

		Thread.sleep(5000);

		WebElement Invite;
		WebDriverWait wait4=new WebDriverWait(driver, 10);
		Invite = wait4.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-select-subject")));
		System.out.println("Ist Invite Message");
		Invite.sendKeys("New Training Invite for All Regions !!");

		//driver.findElement(By.id("edit-select-subject")).sendKeys("New Training Invite");
		//Thread.sleep(5000);

		//CKE EDITOR CODING


		WebElement iframe3 = driver.findElement(By.xpath("//div[@id='cke_1_contents']/iframe"));
		driver.switchTo().frame(iframe3);
		WebElement web3=driver.findElement(By.xpath("//html[@dir='ltr']"));
		web3.click();
		Thread.sleep(2000);
		Actions act2=new Actions(driver);
		act2.moveToElement(web3).sendKeys("Training Invite from Multipe Regions").build().perform();
		Thread.sleep(2000);


		driver.switchTo().defaultContent();
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
