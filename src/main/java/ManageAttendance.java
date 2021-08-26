import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class ManageAttendance {
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
	  String driverPath = "D:/Automation/drivers/browsers";
	  System.setProperty("webdriver.chrome.driver", driverPath+"/chrome/chromedriver.exe");
	  //System.setProperty("webdriver.chrome.driver", driverPath+"/chrome/chromedriver_win32_latest/chromedriver.exe");
	
	DesiredCapabilities dc = new DesiredCapabilities();
    dc.setAcceptInsecureCerts(true);
    
    ChromeOptions coptions = new ChromeOptions();
    coptions.merge(dc);
     
    driver = new ChromeDriver(coptions);

    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @Test
  public void testEditUser() throws Exception {
	  driver.get("https://bi-3.axxonet.com/isonlms/");
	  Thread.sleep(5000);
	  driver.findElement(By.linkText("Login")).click();
	  Thread.sleep(3000);
	  driver.findElement(By.id("username")).sendKeys("brajesh");
	  Thread.sleep(3000);
	  driver.findElement(By.id("password")).sendKeys("Axxonet@123");
	  driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
	  Thread.sleep(8000);
	  
	// Click On Send Anyway button if the prompt is displaying
		/*boolean var=  driver.findElement(By.xpath("//button[@id='proceed-button']")).isDisplayed();
		System.out.println(var);
		if (var==true)
		{
			 driver.findElement(By.xpath("//button[@id='proceed-button']")).click();
		}
		  Thread.sleep(8000);*/
		
		Actions act = new Actions(driver);  	//Move to Left mouse over bar
		WebElement navigate =driver.findElement(By.xpath("//li[@class='menuparent menu-item menu-item--expanded'][1]"));
		
		act.moveToElement(navigate).build().perform();
		Thread.sleep(3000);
		
		//click on Training n Assessment
		 WebElement training = driver.findElement(By.xpath("//a[@title='Trainings']"));
		 training.click();
		 Thread.sleep(5000);
		 
		 driver.findElement(By.xpath("//a[@title='Manage Attendance']")).sendKeys(Keys.ENTER);
		 Thread.sleep(1000);
		 
		 driver.findElement(By.xpath("//input[@type='search']")).sendKeys("Classroom Training Demo 15-oct");
		 Thread.sleep(1000);
		 System.out.println("Training is selected to Mark Attendance");
		 
		 driver.findElement(By.linkText("Update")).click();
		 Thread.sleep(4000);
		 
		 Select Date= new Select(driver.findElement(By.xpath("//select[@name='select_date']")));
		 Date.selectByVisibleText("Oct 30, 2020");
		 Thread.sleep(3000);
		 System.out.println("Training Date is selected ");
		 
		 Select User1= new Select(driver.findElement(By.id("attendance-form-9757-297")));
		 User1.selectByVisibleText("Attended");
		 Thread.sleep(2000);
		 System.out.println("Trainee 1 selected");
		 
		 
		 Select TraineeTwo= new Select(driver.findElement(By.id("attendance-form-9758-297")));
		 TraineeTwo.selectByVisibleText("Attended");
		 Thread.sleep(3000);
		 System.out.println("Trainee 2 selected");
		 
		 JavascriptExecutor js1 = (JavascriptExecutor) driver;
		 WebElement User3 = driver.findElement(By.xpath("//select[@name='submit_attendance[9759][297][attendance]']"));
		 js1.executeScript("arguments[0].scrollIntoView();", User3);
		 Thread.sleep(3000);
		 
		 Select User3Pic= new Select(driver.findElement(By.xpath("//select[@name='submit_attendance[9759][297][attendance]']")));
		 //Select FinalTrainee= new Select(driver.findElement(By.id("attendance-form-9759-297")));
		 User3Pic.selectByValue("1");
		 Thread.sleep(3000);
		 System.out.println("Trainee 3 selected");
		 
		 JavascriptExecutor js2 = (JavascriptExecutor) driver;
		 WebElement SaveButton = driver.findElement(By.xpath("//input[@value='Save']"));
		 js2.executeScript("arguments[0].scrollIntoView();", SaveButton);
		 
		 driver.findElement(By.xpath("//input[@value='Save']")).click();
		 Thread.sleep(3000); 
		 
		 
		 JavascriptExecutor js3 = (JavascriptExecutor) driver;
		 WebElement ButtonClose = driver.findElement(By.xpath("//input[@value='Save']"));
		 js3.executeScript("arguments[0].scrollIntoView();", ButtonClose);
		 Thread.sleep(3000); 
		 
		 driver.findElement(By.linkText("Close")).click();
		 Thread.sleep(3000);
		 System.out.println("Saved and Closed Attendance Page");
		 
		 driver.findElement(By.linkText("View")).click();
		 Thread.sleep(3000);
		 
		 driver.findElement(By.xpath("//span[@class='ui-button-icon ui-icon ui-icon-closethick']")).click();
		 Thread.sleep(2000);
		 
		 driver.findElement(By.linkText("Logout")).click();
		 System.out.println("Logged Out!!!!");
		 driver.get("http://bi-3.axxonet.com/isonlms/"); 
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