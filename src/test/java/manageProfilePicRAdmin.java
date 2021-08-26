
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import Utility.ConfigReader;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;


public class manageProfilePicRAdmin {
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
		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
	}

	@Test
	public void testManageProfilepic() throws Exception {
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

		Actions act = new Actions(driver);  	//Move to Left mouse over bar
		WebElement navigate =driver.findElement(By.xpath("//li[@class='menuparent menu-item menu-item--expanded'][1]"));
		act.moveToElement(navigate).build().perform();
		Thread.sleep(10000);


		// Move To Navigation Menu
		boolean var= driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[3]/div/ul/li[3]/a")).isDisplayed();
		System.out.println(var);
		if (var==true)
		{
			WebElement menuBar1 = driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[3]/div/ul/li[3]/a"));
			Actions act1 = new Actions (driver);
			act1.moveToElement(menuBar1).perform();
		}
		Thread.sleep(8000);


		driver.findElement(By.linkText("User Management")).click();
		Thread.sleep(5000);
		driver.findElement(By.linkText("Manage Profile Pic's")).click(); //Manage Profile Pic's
		Thread.sleep(5000);

		driver.findElement(By.xpath("(//a[@class='btn btn-sm btn-info'])[2]")).click(); 
		//driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div/div[2]/div[2]/div/div/div/div/div[2]/div/table/tbody/tr[8]/td[7]/a")).click(); //Update
		Thread.sleep(5000);


		Actions upload =new Actions(driver);
		WebElement chooseFile1 = driver.findElement(By.id("edit-my-file-upload"));
		upload.moveToElement(chooseFile1).perform();

		Thread.sleep(2000);
		chooseFile1.sendKeys("D:\\SrinivasFiles\\Rahul.png"); //Selected File to Upload 
		Thread.sleep(2000);




		driver.findElement(By.id("edit-submit")).click(); //Save
		Thread.sleep(4000);

		System.out.println("Successfully Uploaded the Pic to the selected Profile..!!!");
		Thread.sleep(6000);

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
