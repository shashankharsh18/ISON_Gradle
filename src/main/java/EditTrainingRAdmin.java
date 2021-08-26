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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Utility.ConfigReader;

public class EditTrainingRAdmin {
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
		Thread.sleep(15000);

		//Click on Add new Training
		driver.findElement(By.xpath("//a[@class='btn btn-sm btn-info add-training-btn pull-right']/span")).click();
		Thread.sleep(3000);


		String date = new SimpleDateFormat("yyyMMddHHmmssSS").format(new Date());
		driver.findElement(By.id("edit-label-0-value")).sendKeys("SGA TRAINING" +date);
		System.out.println("Creating  New Training");


		/*	driver.findElement(By.id("edit-field-learning-path-enable-forum-value")).click();
		Thread.sleep(3000);

		//driver.findElement(By.id("edit-field-certificate-expire-value")).click();
		//Thread.sleep(5000);

		Select results= new Select(driver.findElement(By.id("edit-field-certificate-expire-results")));
		results.selectByValue("3");
		Thread.sleep(5000);  */

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

		//Start Date
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
		String dateString2=config.getTrainingEndDate();
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
		client.selectByValue("1");
		Thread.sleep(3000);

		//select Place
		driver.findElement(By.id("edit-training-place")).sendKeys("Bangalore");
		Thread.sleep(3000);

		//SAVE BUTTON
		driver.findElement(By.xpath("//input[@id='edit-submit']")).click();
		System.out.println("New Training Created");
		Thread.sleep(14000);

		//Edit Training
		driver.findElement(By.linkText("Overview")).click();
		Thread.sleep(8000);

		driver.findElement(By.id("edit-label-0-value")).click();
		Thread.sleep(3000);

		driver.findElement(By.id("edit-label-0-value")).clear();
		Thread.sleep(3000);

		driver.findElement(By.id("edit-label-0-value")).sendKeys("SGA ClassRoom Training");
		Thread.sleep(3000); 

		/*	driver.findElement(By.id("edit-field-learning-path-enable-forum-value")).click();
		Thread.sleep(3000);

		driver.findElement(By.id("edit-field-certificate-expire-value")).click();
		Thread.sleep(5000);  */

		Select OfflineTr= new Select(driver.findElement(By.id("edit-training-offline-online-type")));
		OfflineTr.selectByValue("1"); //Classroom Trainining
		Thread.sleep(5000);

		Select CertificateType1= new Select(driver.findElement(By.id("edit-certificate-type")));
		CertificateType1.selectByValue("2");
		Thread.sleep(3000);

		// Duration select Hours and Minutes
		Select hours1 = new Select(driver.findElement(By.id("edit-training-duration-hr")));
		hours1.selectByValue("15");
		Thread.sleep(3000);

		//select Minutes
		Select mins1 = new Select(driver.findElement(By.id("edit-training-duration-min")));
		mins1.selectByValue("40");
		Thread.sleep(3000);
		System.out.println("Duration is Changed");
		Thread.sleep(5000);

		//select Place
		driver.findElement(By.id("edit-training-place")).click();
		Thread.sleep(3000);

		driver.findElement(By.id("edit-training-place")).clear();
		Thread.sleep(3000);

		driver.findElement(By.id("edit-training-place")).sendKeys("Delhi");
		Thread.sleep(3000);

		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		WebElement SaveButton = driver.findElement(By.id("edit-submit"));
		js2.executeScript("arguments[0].scrollIntoView();", SaveButton);
		Thread.sleep(5000);

		//SAVE BUTTON
		driver.findElement(By.xpath("//input[@id='edit-submit']")).click();
		System.out.println("New Training Edited Successfully");
		Thread.sleep(10000);

		/////LOgOUT AFTER CREATING NEW TRAINING FROM THE AUTHOR///////
		driver.findElement(By.linkText("Logout")).click();
		System.out.println("Logged Out!!!!");
		driver.get("https://bi-3.axxonet.com/isonlms/"); 

	}


	public static void datePickerByJs1(WebDriver driver,String date,WebElement Date1)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','"+date+"')", Date1);

	}

	public static void TimePickerByJs1(WebDriver driver,String time,WebElement starttime)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','"+time+"')", starttime);

	}

	public static void datePickerByJs2(WebDriver driver,String date,WebElement Date2)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','"+date+"')", Date2);

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
