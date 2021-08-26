import static org.testng.Assert.fail;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class ClassRoomTraining {
	
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
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void ClassTraining() throws Exception {
	  driver.get("https://bi-3.axxonet.com/isonlms/");
	  driver.findElement(By.linkText("Login")).click();
	  driver.findElement(By.id("username")).click();
	  driver.findElement(By.id("username")).clear();
	  driver.findElement(By.id("username")).sendKeys("brajesh");
	  driver.findElement(By.id("password")).clear();
	  driver.findElement(By.id("password")).sendKeys("Axxonet@123");
	  driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
	 
	  //driver.get("http://bi-3.axxonet.com/isonlms/gcc-dashboard");
	  Thread.sleep(8000);
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
		Thread.sleep(3000);
		
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
		 
		 
		 driver.findElement(By.id("edit-label-0-value")).sendKeys("ISON ClassRoom Training 17th december");
		 Thread.sleep(3000);
		 System.out.println("CLASSRoom Name is Given");
		 
		 JavascriptExecutor js1 = (JavascriptExecutor) driver;
		 js1.executeScript("window.scrollBy(0,200)");
		 
		 
		 driver.findElement(By.id("edit-field-learning-path-enable-forum-value")).click();
		 Thread.sleep(3000);
		 
		 driver.findElement(By.id("edit-field-certificate-expire-value")).click();
		 Thread.sleep(5000);
		 
		 Select results= new Select(driver.findElement(By.id("edit-field-certificate-expire-results")));
		 results.selectByValue("24");
		 Thread.sleep(5000);
		 
		 JavascriptExecutor js2 = (JavascriptExecutor) driver;
		 js2.executeScript("window.scrollBy(0,200)");
		 
		 
		 Select OfflineTr= new Select(driver.findElement(By.id("edit-training-offline-online-type")));
		 OfflineTr.selectByValue("0");
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
		 CertificateType.selectByValue("2");
		 Thread.sleep(3000);
		 
		 WebElement element= driver.findElement(By.id("edit-training-start-date-date"));
		 String dateString= "2020-12-16";
		 datePickerByJs1(driver,dateString,element);
		 Thread.sleep(3000);
		 
		 JavascriptExecutor js4 = (JavascriptExecutor) driver;
		 js4.executeScript("window.scrollBy(0,200)");
		 
		 
		 //Start Time
		 WebElement starttime= driver.findElement(By.id("edit-training-start-date-time"));
		 String time1= "10:00:00";
		 TimePickerByJs1(driver,time1,starttime);
		 Thread.sleep(3000);
		 
		//End Date
		 WebElement element2= driver.findElement(By.id("edit-training-end-date-date"));
		 String dateString2= "2021-01-31";
		 datePickerByJs2(driver,dateString2,element2);
		 Thread.sleep(3000);
		 
		//End Time
		 WebElement endtime= driver.findElement(By.id("edit-training-end-date-time"));
		 String time2= "03:00:00";
		 TimePickerByJs2(driver,time2,endtime);
		 Thread.sleep(3000);
		 
		 JavascriptExecutor js3 = (JavascriptExecutor) driver;
		 js3.executeScript("window.scrollBy(0,200)");
		 
		 
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
		 
		 Select OneTrainer= new Select(driver.findElement(By.id("Array-available")));
		 OneTrainer.selectByValue("9762");
		 Thread.sleep(5000);
		 
		 driver.findElement(By.xpath("//li[@class='multiselect-add']")).click();
		 Thread.sleep(2000);
		 
		 Select Report = new Select(driver.findElement(By.xpath("//select[@name='kya_applicable_status']")));
		 Report.selectByValue("1");
		 Thread.sleep(3000);
		 
		//SAVE BUTTON
		 driver.findElement(By.xpath("//input[@id='edit-submit']")).click();
		 Thread.sleep(2000);
		 System.out.println("Click Save to Create Training");
		 Thread.sleep(3000);
		 
		//Click on ADDNEW BUTTON Assessment Module
		 driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-plus']")).click();
		 Thread.sleep(3000);
		                     
		  //Assessment Name
		 driver.switchTo().frame("module_submit_add_frame");
		 Thread.sleep(3000);
		 
		 driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/form/div[1]/div/div/input")).sendKeys("ClassRoom Assessment 17th december");
		 Thread.sleep(5000);
		 
		 //Assessment Type
		 Select type = new Select(driver.findElement(By.id("edit-assessment-type")));
		 type.selectByVisibleText("Operation KPI Assessment");
		 Thread.sleep(5000);
		 
		 //Hours
		 Select duration = new Select(driver.findElement(By.id("edit-module-duration-hr")));
		 duration.selectByValue("8");
		 Thread.sleep(5000);
		 
		//Minutes
		 Select duration1 = new Select(driver.findElement(By.id("edit-module-duration-min")));
		 duration1.selectByValue("30");
		 //tr.selectByVisibleText("Online");
		 Thread.sleep(10000);
		 
		 
		 ////////***Save Button Assessment******///////////////
		 driver.findElement(By.id("edit-submit")).sendKeys(Keys.ENTER);
		 Thread.sleep(3000);
		 System.out.println("New Assessment created");
		 
	     /////////******CLICK ON MANAGE TO ADD TO ADD QUESTIONS*********/////////////
		 
		    //driver.get(" http://bi-3.axxonet.com/isonlms/group/487/assessmentstructure");
			//Thread.sleep(3000);		 
	        
		    driver.findElement(By.xpath("//a[contains(text(),'Manage')]//span[@class='glyphicon glyphicon-cog']")).click();
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//a[@class='use-ajax btn btn-info btn-sm float-right open-module btn-add-feedback-two']/span")).click();
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//a[contains(text(),'True False')]")).click();
			Thread.sleep(2000);
			
			System.out.println(driver.findElements(By.tagName("iframe")).size());
			
			driver.switchTo().frame("iFrameResizer0");
			Thread.sleep(5000);
			
			driver.findElement(By.id("edit-name-0-value")).sendKeys("True or False");
			Thread.sleep(10000);
			
			driver.switchTo().parentFrame();
			Thread.sleep(10000);
			
			///////////////////SEND TEXT TO CK EDITOR///////////////////////
			 
			WebElement iframe3 = driver.findElement(By.id("iFrameResizer0"));
	        driver.switchTo().frame(iframe3);
	        WebElement web3=driver.findElement(By.xpath("//html[@dir='ltr']"));
	        web3.click();
	        Thread.sleep(2000);
	        Actions act1=new Actions(driver);
	        act1.moveToElement(web3).sendKeys("Peacock is the National Bird Of India").build().perform();
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//input[@id='edit-submit']")).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			System.out.println("Question Created for Assessment");
			
			driver.switchTo().parentFrame();
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//button[@title='Close']/span[1]")).click();
	 
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
			Thread.sleep(2000);
			
			/*Select Lob = new Select(driver.findElement(By.id("edit-select-process")));
			Lob.selectByVisibleText("SBI Recovery");
			Thread.sleep(2000);
			
			Select Circle = new Select(driver.findElement(By.id("edit-select-circle")));
			Circle.selectByVisibleText("Karnataka SBI");
			Thread.sleep(2000);
			
			Select Designation = new Select(driver.findElement(By.id("edit-select-designation")));
			Designation.selectByVisibleText("AM Operations ");
			Thread.sleep(2000);
			
			Select Trainee = new Select(driver.findElement(By.id("edit-select-role")));
			Trainee.selectByVisibleText("Trainee");
			Thread.sleep(2000);
			
			Select Batch = new Select(driver.findElement(By.id("edit-select-batch")));
			Batch.selectByVisibleText("Support");
			Thread.sleep(2000);*/
			
			   ////// ADD TRAINEES TO THE TRAINING//////
			
			
			/*Select Users = new Select(driver.findElement(By.id("Array-available")));
			Users.selectByVisibleText("Bijoy John (ISCH03110)");
			Thread.sleep(2000);*/
			
			Select User2 = new Select(driver.findElement(By.id("Array-available")));
			User2.selectByVisibleText("Sridevi S (ISBL01830)");
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//li[@class='multiselect-add']")).click();
			Thread.sleep(3000);
			
			driver.findElement(By.xpath("//input[@id='edit-submit']")).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			System.out.println("Trainees added to the assessment");
			
			///////////***********CLICK ON PUBLISH BUTTON**********///////////
			driver.findElement(By.xpath("//a[@title='Publish']")).click();
			Thread.sleep(3000);
			System.out.println("Clicked on Publish Button to Publish Training");
			
			////To Search Training after it is Created/////////////
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
			driver.findElement(By.id("edit-search-user-text")).sendKeys("ISON ClassRoom Training 17th december");
			Thread.sleep(1000);
			
			
		    ///////////******* SEARCH Training in Search Bar Option *********//////
			driver.findElement(By.xpath("//input[@class='search-members-button button js-form-submit form-submit form-control']")).click();
			Thread.sleep(3000);
			System.out.println("Searched Training in the search bar");
			Thread.sleep(3000);
		  
            driver.findElement(By.linkText("Logout")).click();
            System.out.println("Logged Out!!!!");
            driver.get("http://bi-3.axxonet.com/isonlms/"); 
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