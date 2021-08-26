
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LiveMeeting {
	private WebDriver driver;
	//private StringBuffer verificationErrors = new StringBuffer();

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
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}

	@Test
	public void testLIveMeeting() throws Exception {
		 driver.get("https://bi-3.axxonet.com/isonlms/");
		  driver.findElement(By.linkText("Login")).click();
		  driver.findElement(By.id("username")).click();
		  driver.findElement(By.id("username")).clear();
		  driver.findElement(By.id("username")).sendKeys("brajesh");
		  driver.findElement(By.id("password")).clear();
		  driver.findElement(By.id("password")).sendKeys("Axxonet@123");
		  driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
		  //Thread.sleep(20000);
		  //driver.get("http://bi-3.axxonet.com/isonlms/gcc-dashboard");
		  Thread.sleep(8000);
		  
		


		WebElement menuBar1 = driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[3]/div/ul/li[8]/a"));
		Actions act1 = new Actions (driver);
		act1.moveToElement(menuBar1).perform();
		System.out.println("MOved to Navigation Menu");
		Thread.sleep(8000);

		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		WebElement TraineeTab = driver.findElement(By.linkText("Live Meeting"));
		js3.executeScript("arguments[0].scrollIntoView();", TraineeTab);


		//Live Meeting

		WebElement TraineeClick;
		WebDriverWait wait1=new WebDriverWait(driver, 10);
		TraineeClick = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Live Meeting")));
		System.out.println("Clicked on Live Meeting");
		TraineeClick.click();
		Thread.sleep(2000);

		driver.findElement(By.linkText("Manage Zoom Meeting")).click();
		System.out.println("User clicked Manage Zoom Meeting");
		Thread.sleep(8000);

		/*
		//Create New Live Meeting
		driver.findElement(By.linkText("Create Meeting")).click();
		Thread.sleep(000);

		String date = new SimpleDateFormat("yyyMMddHHmmssSS").format(new Date());
		driver.findElement(By.id("edit-meeting-topic")).sendKeys("NewMeeting" + date);
		Thread.sleep(3000);

		driver.findElement(By.id("edit-meeting-descriptions")).sendKeys("IBVIL Bangalore trainers meeting");
		Thread.sleep(3000);

		Select MeetingType= new Select(driver.findElement(By.id("edit-meeting-type")));
		MeetingType.selectByVisibleText("Scheduled Meeting");
		System.out.println("Selected MeetingType");
		Thread.sleep(5000);

		WebElement element= driver.findElement(By.id("edit-meeting-start-date-date"));
		String dateString= "2021-03-12";
		datePickerByJs1(driver,dateString,element);
		Thread.sleep(3000);

		//Start Time
		WebElement starttime= driver.findElement(By.id("edit-meeting-start-date-time"));
		String time1= "11:19:00";
		TimePickerByJs1(driver,time1,starttime);
		Thread.sleep(3000);

		driver.findElement(By.id("edit-submit")).click();
		System.out.println("Clicked On Save and Created Live Meeting");
		Thread.sleep(3000); 

		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("NewMeeting" + date);
		Thread.sleep(3000);

		//Add Participants
		driver.findElement(By.linkText("Add Participants")).click();
		Thread.sleep(8000);

		Select Region2 = new Select(driver.findElement(By.id("edit-select-region")));
		Region2.selectByVisibleText("India");
		Thread.sleep(2000);

		Select Site2 = new Select(driver.findElement(By.id("edit-select-site")));
		Site2.selectByVisibleText("Bangalore");
		Thread.sleep(2000);

		Select Client2 = new Select(driver.findElement(By.id("edit-select-client")));
		Client2.selectByVisibleText("VIL");
		Thread.sleep(2000);

		Select User1 = new Select(driver.findElement(By.id("Array-available")));
		User1.selectByVisibleText("Melwyn Fernades (ISBL02224)");
		Thread.sleep(2000);

		Select User2 = new Select(driver.findElement(By.id("Array-available")));
		User2.selectByVisibleText("Sudesh D (ISBL02272)");
		Thread.sleep(2000);

		Select User3 = new Select(driver.findElement(By.id("Array-available")));
		User3.selectByVisibleText("mytestuserfirstnameedtd mytestuserlastnameedtd (ISBL00351)");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//li[@class='multiselect-add']")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@id='edit-submit']")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		System.out.println("Trainees added to the Live Meeting");  

		//driver.get("http://bi-3.axxonet.com/isonlms/meeting/4/view-meeting-participants-list");
		//Thread.sleep(6000);

		driver.findElement(By.linkText("Back")).click(); //Back Button
		Thread.sleep(8000);

		//Search and View Added participants
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("NewMeeting" + date);
		Thread.sleep(3000);

		driver.findElement(By.xpath("//a[@title='View Participants']")).click();
		System.out.println("Viewed participants successfully");
		Thread.sleep(6000);  

		driver.findElement(By.linkText("Back")).click(); //Back Button
		Thread.sleep(8000);  */
		////*********//////////

		//Search and Edit Live Meeting 
		/*	driver.findElement(By.xpath("//input[@type='search']")).sendKeys("NewMeeting" + date);
		Thread.sleep(3000);

		driver.findElement(By.linkText("Edit")).click();
		Thread.sleep(8000);

		driver.findElement(By.id("edit-meeting-descriptions")).click();
		Thread.sleep(2000);

		driver.findElement(By.id("edit-meeting-descriptions")).clear();
		Thread.sleep(2000);

		driver.findElement(By.id("edit-meeting-descriptions")).sendKeys("Please Available to the meeting OnTime");
		Thread.sleep(3000);

		driver.findElement(By.id("edit-meeting-duration")).click();
		Thread.sleep(2000);

		driver.findElement(By.id("edit-meeting-duration")).clear();
		Thread.sleep(2000);

		driver.findElement(By.id("edit-meeting-duration")).sendKeys("30");
		Thread.sleep(2000);

		driver.findElement(By.id("edit-submit")).click();
		Thread.sleep(3000);   */


		//Search and Start Meeting 
		//driver.findElement(By.xpath("//input[@type='search']")).sendKeys("NewMeeting" + date);
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("NewMeeting20210316110305203");
		Thread.sleep(3000);

		//Clicked on Start Button
		driver.findElement(By.linkText("Start")).click();
		Thread.sleep(20000);

		/*	String driverPath = "D:/Automation/drivers/browsers";
		System.setProperty("webdriver.chrome.driver", driverPath+"/chrome/chromedriver.exe");

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setAcceptInsecureCerts(true);

		ChromeOptions coptions = new ChromeOptions();
		coptions.merge(dc);

		driver = new ChromeDriver(coptions);


		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  */

		//FireFox Driver
		String driverPath = "D:/Automation/drivers/browsers";
		 System.setProperty("webdriver.edge.driver", driverPath+"/edge/msedgedriver.exe");
		 driver = new EdgeDriver();

		

		

		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


		//Pariticipant Joining Meeting
		 driver.get("http://bi-3.axxonet.com/isonlms/");
		  driver.findElement(By.linkText("Login")).click();
		  Thread.sleep(2000);
		  driver.findElement(By.xpath("//button[@id='details-button']")).click();
		  Thread.sleep(2000);
		  driver.findElement(By.id("proceed-link")).click();
		  Thread.sleep(2000);
		  driver.findElement(By.id("username")).click();
		  driver.findElement(By.id("username")).clear();
	      driver.findElement(By.id("username")).sendKeys("ISBL02224"); 
		  driver.findElement(By.id("password")).clear();
		  driver.findElement(By.id("password")).sendKeys("ison12345");
		  driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
		  Thread.sleep(10000);

	

		

		driver.get("http://bi-3.axxonet.com/isonlms/view/my-meeting");
		Thread.sleep(8000); 

		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("NewMeeting20210316110305203");
		Thread.sleep(3000);

		driver.findElement(By.xpath("//a[@title='Join']")).click();
		Thread.sleep(20000);

		driver.findElement(By.xpath("//text()[.='Leave']/ancestor::button[1]")).click();
		Thread.sleep(6000); //END

		driver.findElement(By.xpath("//text()[.='Leave Meeting']/ancestor::button[1]")).click();
		Thread.sleep(6000); // Participant ended the Meeting

		driver.findElement(By.linkText("Logout")).click();
		System.out.println("Logged Out");//melwyn logged out.
		//driver.get("http://bi-3.axxonet.com/isonlms/");  
		Thread.sleep(4000); 
		
		
		driver.close();
		Thread.sleep(10000);	

		driver.findElement(By.xpath("//text()[.='End']/ancestor::button[1]")).click();
		Thread.sleep(6000); //END

		driver.findElement(By.xpath("//text()[.='End Meeting for All']/ancestor::button[1]")).click();
		Thread.sleep(6000); //End Meeting For Host



		driver.findElement(By.linkText("Back to My Meeting")).click(); 
		Thread.sleep(6000);




		/*	driver.findElement(By.linkText("Logout")).click();
		System.out.println("Logged Out");
		driver.get("http://bi-3.axxonet.com/isonlms/"); 
		Thread.sleep(3000);  */



		//driver.findElement(By.xpath("//button[@class='zmu-btn footer__leave-btn ax-outline ellipsis zmu-btn--danger zmu-btn__outline--blue']"));
		/*driver.findElement(By.xpath("//text()[.='End']/ancestor::button[1]")).click();
		Thread.sleep(3000); //END

		//driver.findElement(By.xpath("//button[@class='zmu-btn leave-meeting-options__btn leave-meeting-options__btn--default leave-meeting-options__btn--danger zmu-btn--default zmu-btn__outline--white']"));
		driver.findElement(By.xpath("//text()[.='End Meeting for All']/ancestor::button[1]")).click();
		Thread.sleep(3000); //End Meeting For All
		 */



	}
	public static void datePickerByJs1(WebDriver driver,String date,WebElement element)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','"+date+"')", element);

	}
	public static void TimePickerByJs1(WebDriver driver,String time,WebElement starttime)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','"+time+"')", starttime);

	}


	/*
	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	} */
}
