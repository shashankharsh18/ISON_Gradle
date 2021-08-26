import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class ManageAttendanceTrainer {
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
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @Test
  public void testEditUser() throws Exception {
	  driver.get("https://bi-3.axxonet.com/isonlms/");
	  driver.findElement(By.linkText("Login")).click();
	  driver.findElement(By.id("username")).click();
	  driver.findElement(By.id("username")).clear();
	  driver.findElement(By.id("username")).sendKeys("ISBL02068"); //Naveeen Naik
	  
	  driver.findElement(By.id("password")).clear();
	  driver.findElement(By.id("password")).sendKeys("ison12345");
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
		 
		 driver.findElement(By.xpath("//input[@type='search']")).sendKeys("Classroom training for Score and Attendance");
		 Thread.sleep(1000);
		 System.out.println("Training is selected to Mark Attendance");
		 
		 driver.findElement(By.linkText("Update")).click();
		 Thread.sleep(4000);
		 
		 Select Date= new Select(driver.findElement(By.xpath("//select[@name='select_date']")));
		 Date.selectByVisibleText("Jan 25, 2021");
		 Thread.sleep(3000);
		 System.out.println("Training Date is selected ");
		 
		 Select User1= new Select(driver.findElement(By.id("attendance-form-9778-613")));
		 User1.selectByVisibleText("Attended");
		 Thread.sleep(2000);
		 System.out.println("Trainee 1 selected");
		 
		 
		 Select User2= new Select(driver.findElement(By.id("attendance-form-9776-613")));
		 User2.selectByVisibleText("Attended");
		 Thread.sleep(2000);
		 System.out.println("Trainee 1 selected");

		 
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