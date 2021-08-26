import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Utility.ConfigReader;

public class CopyTrainingRAdmin {
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

		driver.manage().window().maximize();  //Prathyusha T (ISBL00414)
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

	}

	@Test
	public void SignIn() throws InterruptedException
	{
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
		System.out.println("moved to Left Navigation Menu");
		Thread.sleep(5000);

		//click on Training n Assessment
		WebElement training = driver.findElement(By.xpath("//a[@title='Trainings']"));
		training.click();
		System.out.println("Clicked on Trainings and assessments");
		Thread.sleep(5000);

		//click on manage Classroom and online training
		WebElement training1 = driver.findElement(By.xpath("//a[@title='Manage Classroom & Online Trainings']"));
		training1.click();
		System.out.println("Clicked on Classroom & Online Trainings");
		Thread.sleep(3000);

		///////////*******To SEARCH TRAINING*********///////////////////
		Select Region1 = new Select(driver.findElement(By.id("edit-select-region")));
		Region1.selectByVisibleText("India");
		System.out.println("Selected Region");
		Thread.sleep(2000);

		Select Site1 = new Select(driver.findElement(By.id("edit-select-site")));
		Site1.selectByVisibleText("Bangalore");
		System.out.println("Selected Site");
		Thread.sleep(2000);

		Select Client1 = new Select(driver.findElement(By.id("edit-select-client")));
		Client1.selectByVisibleText("SBI");
		Thread.sleep(2000);
		System.out.println("Selected Client");

		///////// SEND TRAINING NAME IN THE SEARCH PROMPT/////// 
		driver.findElement(By.id("edit-search-user-text")).sendKeys("SGA TRAINING");
		Thread.sleep(3000);


		///////////******* SEARCH Training in Search Bar Option *********//////
		driver.findElement(By.xpath("//input[@class='search-members-button button js-form-submit form-submit form-control']")).click();
		Thread.sleep(1000);
		System.out.println("TRAINING is Searched");
		Thread.sleep(7000);

		driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-copy']")).click();
		System.out.println("Clicked on Copy Button");
		Thread.sleep(12000);

		Select Certificate= new Select(driver.findElement(By.id("edit-certificate-display")));
		System.out.println("Selected Certificate as Yes");
		Certificate.selectByValue("1");
		Thread.sleep(6000);


		//SAVE BUTTON
		driver.findElement(By.xpath("//input[@id='edit-submit']")).click();
		System.out.println(" Clicked Save to create Copy Of Training");
		Thread.sleep(15000);

		//adding ADDNEW BUTTON Assessment Module
		driver.findElement(By.xpath("//a[@class='use-ajax btn btn-info btn-sm float-right open-module open-module btn-add-feedback-four']/span")).click();
		Thread.sleep(15000);

		//Click on online Assessment Button
		driver.findElement(By.xpath("//a[@class='use-ajax btn btn-dark btn-sm open-module'][2]")).click();
		Thread.sleep(5000);

		//Assessment Name
		driver.switchTo().frame("module_submit_add_frame");
		Thread.sleep(3000);

		driver.findElement(By.id("edit-name-0-value")).sendKeys("SGA Assessment");
		Thread.sleep(20000);

		//Assessment Type
		Select type = new Select(driver.findElement(By.id("edit-assessment-type")));
		type.selectByVisibleText("Certification Scores Assessment");
		//tr.selectByVisibleText("Online");
		Thread.sleep(5000);

		//Hours
		Select duration = new Select(driver.findElement(By.id("edit-module-duration-hr")));
		duration.selectByValue("2");
		//tr.selectByVisibleText("Online");
		Thread.sleep(5000);

		//Minutes
		Select duration1 = new Select(driver.findElement(By.id("edit-module-duration-min")));
		duration1.selectByValue("30");
		//tr.selectByVisibleText("Online");
		Thread.sleep(5000);

		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		WebElement SaveButton = driver.findElement(By.id("edit-submit"));
		js2.executeScript("arguments[0].scrollIntoView();", SaveButton);
		Thread.sleep(15000);


		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-submit"))).sendKeys(Keys.ENTER);
		Thread.sleep(14000);

		try {

			driver.findElement(By.xpath("//a[contains(text(),'Manage')]//span[@class='glyphicon glyphicon-cog']")).click();
			Thread.sleep(2000);
		}
		catch(Exception e) {

			WebDriverWait wait7 = new WebDriverWait(driver, 120);
			wait7.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-submit"))).sendKeys(Keys.ENTER); //Save Button
			Thread.sleep(12000);

			driver.findElement(By.xpath("//a[contains(text(),'Manage')]//span[@class='glyphicon glyphicon-cog']")).click();
			Thread.sleep(2000);
		}



		//driver.findElement(By.xpath("//a[contains(text(),'Manage')]//span[@class='glyphicon glyphicon-cog']")).click();
		//Thread.sleep(2000);

		driver.findElement(By.xpath("//a[@class='use-ajax btn btn-info btn-sm float-right open-module btn-add-feedback-two']/span")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[contains(text(),'True False')]")).click();
		Thread.sleep(2000);

		System.out.println(driver.findElements(By.tagName("iframe")).size());

		driver.switchTo().frame("iFrameResizer0");
		Thread.sleep(5000);

		driver.findElement(By.id("edit-name-0-value")).sendKeys("True or False");
		Thread.sleep(10000);

		System.out.println(driver.findElements(By.tagName("iframe")).size());
		Thread.sleep(2000);

		driver.switchTo().frame(0);//Switched to Iframe
		Thread.sleep(5000);
		// Click On Send Anyway button if the prompt is displaying
		boolean var=  driver.findElement(By.xpath("//input[@id='field-extratitle--1']")).isDisplayed();
		System.out.println(var);
		if (var==true)
		{
			//Enter Title
			driver.findElement(By.id("field-extratitle--1")).sendKeys("Q1");
		}
		Thread.sleep(8000);



		///////////////////SEND TEXT TO CK EDITOR///////////////////////


		boolean var2=  driver.findElement(By.id("field-question-15")).isDisplayed();
		System.out.println(var2);
		if (var2==true)
		{
			//Enter Title
			driver.findElement(By.id("field-question-15")).click();
		}
		Thread.sleep(3000);
		
		System.out.println(driver.findElements(By.tagName("iframe")).size());
		Thread.sleep(3000);
		
		WebElement web3=driver.findElement(By.xpath("//html"));
		Thread.sleep(3000);

		boolean var3=  driver.findElement(By.xpath("//html")).isDisplayed();
		System.out.println(var3);
		if (var3==true)
		{
			Actions act1=new Actions(driver);
			act1.moveToElement(web3).sendKeys("Peacock is the National Bird Of India").build().perform();
			Thread.sleep(2000);
		}
		Thread.sleep(8000);


		driver.switchTo().parentFrame();
		Thread.sleep(5000);

		JavascriptExecutor js10 = (JavascriptExecutor) driver;
		WebElement Save = driver.findElement(By.id("edit-submit"));
		js10.executeScript("arguments[0].scrollIntoView();", Save);
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//input[@id='edit-submit']")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		System.out.println("Question Created for Assessment");
		Thread.sleep(3000);
		
		boolean var4=  driver.findElement(By.xpath("//button[@title='Close']/span[1]")).isDisplayed();
		System.out.println(var4);
		if (var4==true)
		{
			driver.findElement(By.xpath("//button[@title='Close']/span[1]")).click();
			Thread.sleep(3000);
		}
		Thread.sleep(2000);

		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		WebElement TraineeTab = driver.findElement(By.xpath("//a[text()='Trainees']"));
		js3.executeScript("arguments[0].scrollIntoView();", TraineeTab);
		Thread.sleep(2000);

		WebElement TraineeClick;
		WebDriverWait wait1=new WebDriverWait(driver, 10);
		TraineeClick = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Trainees']")));
		TraineeClick.click(); 
		Thread.sleep(8000);

		
		driver.findElement(By.xpath("//a[text()='Trainees']")).click();
		Thread.sleep(6000);

		driver.findElement(By.xpath("//a[@title='Add Trainee']")).click();
		Thread.sleep(4000);

		Select Region = new Select(driver.findElement(By.id("edit-select-region")));
		Region.selectByVisibleText("India");
		Thread.sleep(2000);

		Select Site = new Select(driver.findElement(By.id("edit-select-site")));
		Site.selectByVisibleText("Bangalore");
		Thread.sleep(2000);

		Select Client = new Select(driver.findElement(By.id("edit-select-client")));
		Client.selectByVisibleText("VIL");
		Thread.sleep(2000);

		////// ADD TRAINEES TO THE TRAINING//////

		Select User1 = new Select(driver.findElement(By.id("Array-available")));
		User1.selectByVisibleText("Naveen Naik (ISBL02068)");
		Thread.sleep(2000);

		/*Select User2 = new Select(driver.findElement(By.id("Array-available")));
		User2.selectByVisibleText("Chethan H (ISBL02069)");
		Thread.sleep(2000);

		Select User3 = new Select(driver.findElement(By.id("Array-available")));
		User3.selectByVisibleText("Sridevi S (ISBL01830)");
		Thread.sleep(2000);*/

		driver.findElement(By.xpath("//li[@class='multiselect-add']")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@id='edit-submit']")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		System.out.println("Trainees added to the assessment");  

		///////////***********CLICK ON PUBLISH BUTTON**********///////////
		driver.findElement(By.xpath("//a[@title='Publish']")).click();
		Thread.sleep(3000);
		System.out.println("Clicked on Publish Button to Publish Training");


		/////LOgOUT AFTER CREATING NEW TRAINING FROM THE AUTHOR///////
		driver.findElement(By.linkText("Logout")).click(); 
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
