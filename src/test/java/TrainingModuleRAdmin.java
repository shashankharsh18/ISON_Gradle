import static org.testng.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.Date;
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

public class TrainingModuleRAdmin {	
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
	public void IsonLogin() throws InterruptedException
	{
		ConfigReader config= new ConfigReader();
		driver.get(config.getApplicationUrl());

		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(config.getRAdminUserName());
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

		//click on Training n Assessment
		WebElement training = driver.findElement(By.xpath("//a[@title='Trainings']"));
		training.click();
		Thread.sleep(3000);

		//click on manage Classroom and online training
		WebElement training1 = driver.findElement(By.xpath("//a[@title='Manage Classroom & Online Trainings']"));
		training1.click();
		Thread.sleep(3000);


		//Click on Add new Training
		driver.findElement(By.xpath("//a[@class='btn btn-sm btn-info add-training-btn pull-right']/span")).click();
		Thread.sleep(3000);


		String date = new SimpleDateFormat("yyyMMddHHmmssSS").format(new Date());
		driver.findElement(By.id("edit-label-0-value")).sendKeys("RAdmin TRAINING" +date);
		System.out.println("Creating  New Training");


		/*	driver.findElement(By.id("edit-field-learning-path-enable-forum-value")).click();
		Thread.sleep(3000);

		//driver.findElement(By.id("edit-field-certificate-expire-value")).click();
		//Thread.sleep(5000);

		Select results= new Select(driver.findElement(By.id("edit-field-certificate-expire-results")));
		results.selectByValue("3");
		Thread.sleep(5000); */

		Select OnlineTr= new Select(driver.findElement(By.id("edit-training-offline-online-type")));
		OnlineTr.selectByValue("1");
		Thread.sleep(5000);


		Select TrType= new Select(driver.findElement(By.id("edit-training-type")));
		TrType.selectByValue("0");
		Thread.sleep(5000);

		Select Category= new Select(driver.findElement(By.id("edit-training-category-bevab")));
		Category.selectByValue("Competency Enhancement & Deployment Program (CEDP)");
		Thread.sleep(3000);

		Select Certificate= new Select(driver.findElement(By.id("edit-certificate-display")));
		Certificate.selectByValue("1");
		Thread.sleep(3000);

		Select CertificateType= new Select(driver.findElement(By.id("edit-certificate-type")));
		CertificateType.selectByValue("0");
		Thread.sleep(3000);

		WebElement element= driver.findElement(By.id("edit-training-start-date-date"));
		String dateString= config.getTrainingStartDate();
		datePickerByJs1(driver,dateString,element);
		Thread.sleep(3000);

		//Start Time
		WebElement starttime= driver.findElement(By.id("edit-training-start-date-time"));
		String time1= "10:00:00";
		TimePickerByJs1(driver,time1,starttime);
		Thread.sleep(3000);

		//End Date
		WebElement element2= driver.findElement(By.id("edit-training-end-date-date"));
		String dateString2= config.getTrainingEndDate();
		datePickerByJs2(driver,dateString2,element2);
		Thread.sleep(3000);

		//End Time
		WebElement endtime= driver.findElement(By.id("edit-training-end-date-time"));
		String time2= "03:00:00";
		TimePickerByJs2(driver,time2,endtime);
		Thread.sleep(3000);


		// Duration select Hours and Minutes
		Select hours = new Select(driver.findElement(By.id("edit-training-duration-hr")));
		hours.selectByValue("90");
		Thread.sleep(3000);

		//select Minutes
		Select mins = new Select(driver.findElement(By.id("edit-training-duration-min")));
		mins.selectByValue("0");
		Thread.sleep(3000);

		//select Region
		Select region = new Select(driver.findElement(By.id("edit-training-region")));
		region.selectByValue("1");
		Thread.sleep(3000);

		//select Site
		Select site = new Select(driver.findElement(By.id("edit-training-sites")));
		site.selectByValue("1");
		Thread.sleep(3000);

		//select Client
		Select client = new Select(driver.findElement(By.id("edit-training-clients")));
		client.selectByValue("7");
		Thread.sleep(3000);

		//select Place
		driver.findElement(By.id("edit-training-place")).sendKeys("Bangalore");
		Thread.sleep(3000);

		//SAVE BUTTON
		driver.findElement(By.xpath("//input[@id='edit-submit']")).click();
		System.out.println("New Training Created");
		Thread.sleep(60000);

		//adding ADDNEW BUTTON Assessment Module
		driver.findElement(By.xpath("//a[@class='use-ajax btn btn-info btn-sm float-right open-module open-module btn-add-feedback-four']/span")).click();
		Thread.sleep(60000);

		//Click on online Assessment Button
		driver.findElement(By.xpath("//a[@class='use-ajax btn btn-dark btn-sm open-module'][2]")).click();
		Thread.sleep(5000);

		//Assessment Name
		driver.switchTo().frame("module_submit_add_frame");
		Thread.sleep(5000);

		String date1 = new SimpleDateFormat("yyyMMddHHmmssSS").format(new Date());
		//driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/form/div[1]/div/div/input")).sendKeys("SGA Assessment" +date1);
		driver.findElement(By.id("edit-name-0-value")).sendKeys("RAdmin Assessment" +date1);
		Thread.sleep(7000);

		//Assessment Type
		Select type = new Select(driver.findElement(By.id("edit-assessment-type")));
		type.selectByVisibleText("Certification Scores Assessment");
		//tr.selectByVisibleText("Online");
		Thread.sleep(7000);

		//Hours
		Select duration = new Select(driver.findElement(By.id("edit-module-duration-hr")));
		duration.selectByValue("2");
		//tr.selectByVisibleText("Online");
		Thread.sleep(7000);

		//Minutes
		Select duration1 = new Select(driver.findElement(By.id("edit-module-duration-min")));
		duration1.selectByValue("30");
		//tr.selectByVisibleText("Online");
		Thread.sleep(10000);


		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		WebElement SaveButton = driver.findElement(By.id("edit-submit"));
		js2.executeScript("arguments[0].scrollIntoView();", SaveButton);
		Thread.sleep(25000);


		////////***Save Button Assessment******///////////////
		WebDriverWait wait = new WebDriverWait(driver, 180);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-submit"))).sendKeys(Keys.ENTER);
		Thread.sleep(20000);

		try {

			driver.findElement(By.xpath("//a[contains(text(),'Manage')]//span[@class='glyphicon glyphicon-cog']")).click();
			Thread.sleep(8000);
		}

		catch(Exception e) {

			WebDriverWait wait7 = new WebDriverWait(driver, 180);
			wait7.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-submit"))).sendKeys(Keys.ENTER); //Save Button
			Thread.sleep(12000);

			driver.findElement(By.xpath("//a[contains(text(),'Manage')]//span[@class='glyphicon glyphicon-cog']")).click();
			Thread.sleep(2000);
		}



		/////////******CLICK ON MANAGE TO ADD TO ADD QUESTIONS*********/////////////



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

		WebElement web3=driver.findElement(By.xpath("//html"));


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
		Thread.sleep(4000);

		boolean var4=  driver.findElement(By.xpath("//button[@title='Close']/span[1]")).isDisplayed();
		System.out.println(var4);
		if (var4==true)
		{
			driver.findElement(By.xpath("//button[@title='Close']/span[1]")).click();
			Thread.sleep(3000);
		}
		Thread.sleep(2000);


		driver.findElement(By.xpath("//a[text()='Trainees']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[@title='Add Trainee']")).click();
		Thread.sleep(2000);

		Select Region = new Select(driver.findElement(By.id("edit-select-region")));
		Region.selectByVisibleText("India");
		Thread.sleep(2000);

		Select Site = new Select(driver.findElement(By.id("edit-select-site")));
		Site.selectByVisibleText("Bangalore");
		Thread.sleep(2000);

		Select Client = new Select(driver.findElement(By.id("edit-select-client")));
		Client.selectByVisibleText("VIL");
		Thread.sleep(8000);


		////// ADD TRAINEES TO THE TRAINING//////

		Select User1 = new Select(driver.findElement(By.id("Array-available")));
		User1.selectByVisibleText("Manish Kumar (13AZSB602)");
		Thread.sleep(2000);


		driver.findElement(By.xpath("//li[@class='multiselect-add']")).click();
		Thread.sleep(5000);

		driver.findElement(By.xpath("//input[@id='edit-submit']")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		System.out.println("Trainees added to the assessment");

		///////////***********CLICK ON PUBLISH BUTTON**********///////////
		driver.findElement(By.xpath("//a[@title='Publish']")).click();
		Thread.sleep(3000);
		System.out.println("Clicked on Publish Button to Publish Training");

		Select Region1 = new Select(driver.findElement(By.id("edit-select-region")));
		Region1.selectByVisibleText("India");
		Thread.sleep(2000);

		Select Site1 = new Select(driver.findElement(By.id("edit-select-site")));
		Site1.selectByVisibleText("Bangalore");
		Thread.sleep(2000);

		Select Client1 = new Select(driver.findElement(By.id("edit-select-client")));
		Client1.selectByVisibleText("SBI");
		Thread.sleep(2000);

		///////// SEND TRAINING NAME IN THE SEARCH PROMPT/////// 
		driver.findElement(By.id("edit-search-user-text")).sendKeys("RAdmin TRAINING" +date);
		Thread.sleep(1000);


		///////////******* SEARCH Training in Search Bar Option *********//////
		driver.findElement(By.xpath("//input[@class='search-members-button button js-form-submit form-submit form-control']")).click();
		Thread.sleep(3000);
		System.out.println("Searched Training in the search bar");  //Add Preview Option

		//Training is searched to Preview 
		driver.findElement(By.xpath("//input[@class='search-members-button button js-form-submit form-submit form-control']")).click();
		Thread.sleep(1000);
		System.out.println("Training is Seacrched to Preview");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[@title='Preview']/span")).click();
		Thread.sleep(2000);
		System.out.println("Clicking on Preview Button");
		Thread.sleep(6000);

		driver.switchTo().frame("iFrameResizer0");
		Thread.sleep(3000);

		driver.findElement(By.xpath("//div[contains(text(),'True')]")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//button[@title='Check']")).click();
		Thread.sleep(3000);

		driver.findElement(By.linkText("Exit")).click();
		Thread.sleep(3000);
		System.out.println("True or False Question is Previewed"); 


		/////LOgOUT AFTER CREATING NEW TRAINING FROM THE AUTHOR///////
		driver.findElement(By.xpath("(//a[@title='Logout'])[2]")).sendKeys(Keys.ENTER);
		Thread.sleep(60000);

		///End Of Author Creating Training//////
		System.out.println("Author logged out after creating Training and Preview option");

		Thread.sleep(8000);

		//Trainee Log In to take Training
		WebElement Login=driver.findElement(By.xpath("//a[@title='Sign In']"));
		Login.click();
		Thread.sleep(3000);

		driver.findElement(By.name("username")).sendKeys(config.getTrainerUserName());


		driver.findElement(By.name("password")).sendKeys(config.getISONPassword());
		driver.findElement(By.id("chkRemember")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		System.out.println(" Trainee Login Successfull");
		Thread.sleep(60000);

		 boolean var8=  driver.findElement(By.xpath("//li[@class='menuparent menu-item menu-item--expanded'][1]")).isDisplayed();
			System.out.println(var8);
			if (var8==true)
			{
				Actions act = new Actions(driver);  	//Move to Left mouse over bar
				WebElement navigate =driver.findElement(By.xpath("//li[@class='menuparent menu-item menu-item--expanded'][1]"));
				act.moveToElement(navigate).build().perform();
				System.out.println("moved to Left Navigation Menu");
				Thread.sleep(5000);
			}

		//click on Training n Assessment
		WebElement training3 = driver.findElement(By.xpath("//a[@title='Trainings']"));
		training3.click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//a[@title='My Trainings / Assessments']")).click();
		Thread.sleep(20000);



		driver.findElement(By.xpath("(//input[@type='search'])[1]")).sendKeys("RAdmin TRAINING" +date);
		Thread.sleep(10000);

		WebElement element7 =driver.findElement(By.linkText("Next"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element7);
		Thread.sleep(60000);

		//Start Assessment
		WebElement element5=driver.findElement(By.xpath("//a[contains(text(),'Start')]"));
		JavascriptExecutor executor5 = (JavascriptExecutor)driver;
		executor5.executeScript("arguments[0].click();", element5);
		Thread.sleep(25000);

		//Continue Assessment
		//driver.findElement(By.xpath("//a[contains(text(),'Continue training')]")).click();
		//Thread.sleep(4000);

		driver.findElement(By.xpath("//div[contains(text(),'True')]")).click();
		Thread.sleep(6000);

		driver.findElement(By.xpath("//button[@title='Check']")).click();
		Thread.sleep(6000);

		driver.findElement(By.xpath("//input[@id='edit-submit']")).sendKeys(Keys.ENTER);
		Thread.sleep(10000);

		//driver.findElement(By.xpath("//a[contains(text(),'Back to training homepage')]")).sendKeys(Keys.ENTER);
		driver.findElement(By.id("edit-submit")).sendKeys(Keys.ENTER);
		Thread.sleep(8000);

		driver.findElement(By.linkText("Logout")).click();
		System.out.println("Logged Out!!!!");
		Thread.sleep(60000);

		WebElement Login2=driver.findElement(By.xpath("//a[@title='Sign In']"));
		Login2.click();
		Thread.sleep(3000);

		driver.findElement(By.name("username")).sendKeys(config.getSGAUserName());

		driver.findElement(By.name("password")).sendKeys(config.getISONPassword());
		driver.findElement(By.id("chkRemember")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		System.out.println(" Trainee Login Successfull");
		Thread.sleep(60000);

		Actions act3 = new Actions(driver);  	//Move to Left mouse over bar
		WebElement navigate3 =driver.findElement(By.xpath("//li[@class='menuparent menu-item menu-item--expanded'][1]"));
		act3.moveToElement(navigate3).build().perform();
		Thread.sleep(3000);

		//click on Training n Assessment
		WebElement training13 = driver.findElement(By.xpath("//a[@title='Trainings']"));
		training13.click();
		Thread.sleep(3000);

		//click on manage Classroom and online training
		WebElement training33 = driver.findElement(By.xpath("//a[@title='Manage Classroom & Online Trainings']"));
		training33.click();
		Thread.sleep(20000);

		Select Region11 = new Select(driver.findElement(By.id("edit-select-region")));
		Region11.selectByVisibleText("India");
		Thread.sleep(2000);

		Select Site11 = new Select(driver.findElement(By.id("edit-select-site")));
		Site11.selectByVisibleText("Bangalore");
		Thread.sleep(2000);

		Select Client11 = new Select(driver.findElement(By.id("edit-select-client")));
		Client11.selectByVisibleText("SBI");
		Thread.sleep(2000);

		//String date2 = new SimpleDateFormat("yyyMMddHHmmssSS").format(new Date());
		driver.findElement(By.id("edit-search-user-text")).sendKeys("RAdmin TRAINING" +date);
		Thread.sleep(5000);

		driver.findElement(By.xpath("//input[@class='search-members-button button js-form-submit form-submit form-control']")).click();
		Thread.sleep(3000);
		System.out.println("Searched Training in the search bar");

		driver.findElement(By.linkText("Edit")).click();
		Thread.sleep(60000);

		driver.findElement(By.linkText("Content & Assessments")).click();
		Thread.sleep(5000);

		driver.findElement(By.linkText("Manage")).click();
		Thread.sleep(5000);

		WebElement element22= driver.findElement(By.xpath("//a[@class='use-ajax btn btn-sm btn-warning open-activity']"));
		Thread.sleep(2000);
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element22);
		Thread.sleep(5000);

		driver.findElement(By.linkText("No")).click();
		Thread.sleep(20000);


		driver.findElement(By.xpath("//button[@title='Close']/span[1]")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//a[text()='Trainees']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[@title='Publish']")).click();
		Thread.sleep(3000);
		System.out.println("Clicked on Publish Button to Publish Training");


		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(5000);


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

	public static void datePickerByJs2(WebDriver driver,String date,WebElement element2)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','"+date+"')", element2);

	}

	public static void TimePickerByJs2(WebDriver driver,String time,WebElement endtime)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','"+time+"')", endtime);

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
