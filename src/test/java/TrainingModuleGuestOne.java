import static org.testng.Assert.fail;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Utility.ConfigReader;

public class TrainingModuleGuestOne {	
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
	public void IsonLogin() throws InterruptedException
	{
		ConfigReader config= new ConfigReader();
	  driver.get(config.getApplicationUrl());
	  
	  driver.findElement(By.linkText("Login")).click();
	  driver.findElement(By.id("username")).click();
	  driver.findElement(By.id("username")).clear();
	  driver.findElement(By.id("username")).sendKeys(config.getGuestUserName());
	  driver.findElement(By.id("password")).clear();
	  driver.findElement(By.id("password")).sendKeys(config.getISONPassword());
	  driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
	  Thread.sleep(20000);

		// Click On Send Anyway button if the prompt is displaying

		Actions act = new Actions(driver);  	//Move to Left mouse over bar
		WebElement navigate =driver.findElement(By.xpath("//li[@class='menuparent menu-item menu-item--expanded'][1]"));
		act.moveToElement(navigate).build().perform();
		Thread.sleep(3000);

		//click on Training n Assessment
		WebElement training = driver.findElement(By.xpath("//a[@title='Trainings']"));
		training.click();
		Thread.sleep(3000);

		//click on manage Classroom and online training
		WebElement training1 = driver.findElement(By.xpath("//a[@title='Manage Classroom & Online Trainings']"));
		training1.click();
		Thread.sleep(3000);



		Select Region1 = new Select(driver.findElement(By.id("edit-select-region")));
		Region1.selectByVisibleText("India");
		Thread.sleep(2000);

		Select Site1 = new Select(driver.findElement(By.id("edit-select-site")));
		Site1.selectByVisibleText("Bangalore");
		Thread.sleep(2000);

		Select Client1 = new Select(driver.findElement(By.id("edit-select-client")));
		Client1.selectByVisibleText("VIL");
		Thread.sleep(2000);

		///////// SEND TRAINING NAME IN THE SEARCH PROMPT/////// 
		driver.findElement(By.id("edit-search-user-text")).sendKeys("Attendance Score Trainer Training");
		Thread.sleep(1000);


		///////////******* SEARCH Training in Search Bar Option *********//////
		driver.findElement(By.xpath("//input[@class='search-members-button button js-form-submit form-submit form-control']")).click();
		Thread.sleep(3000);
		System.out.println("Searched Training in the search bar");

		/////LOgOUT AFTER CREATING NEW TRAINING FROM THE AUTHOR///////
		driver.findElement(By.xpath("(//a[@title='Logout'])[2]")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		///End Of Author Creating Training//////
		System.out.println("logged out");
		driver.get("https://bi-3.axxonet.com/isonlms/"); 
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
