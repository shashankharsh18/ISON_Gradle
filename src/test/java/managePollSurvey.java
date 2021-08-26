
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class managePollSurvey {
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
	public void testManagePollSurvey() throws Exception {
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

		// Click On Send Anyway button if the prompt is displaying
		/*	boolean var=  driver.findElement(By.xpath("//button[@id='proceed-button']")).isDisplayed();
		System.out.println(var);
		if (var==true)
		{
			 driver.findElement(By.xpath("//button[@id='proceed-button']")).click();
		}
		  Thread.sleep(8000);*/

		WebElement menuBar1 = driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[3]/div/ul/li[8]/a"));
		Actions act1 = new Actions (driver);
		act1.moveToElement(menuBar1).perform();
		System.out.println("MOved to Navigation Menu");
		Thread.sleep(10000);

		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		WebElement TraineeTab = driver.findElement(By.xpath("//a[@title='Poll / Survey']"));
		js3.executeScript("arguments[0].scrollIntoView();", TraineeTab);
		Thread.sleep(2000);

		WebElement TraineeClick;
		WebDriverWait wait1=new WebDriverWait(driver, 10);
		TraineeClick = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Poll / Survey']")));
		System.out.println(" Clicked Poll / Survey");
		TraineeClick.click();

		//driver.get("https://bi-3.axxonet.com/isonlms/poll/details");
		//Thread.sleep(6000);
		driver.findElement(By.linkText("Manage Poll/Survey")).click();
		Thread.sleep(10000);

		//Test code for Add New Poll/Survey

		driver.findElement(By.linkText("Create New Poll/Survey")).click();
		System.out.println("Clicked on Create New Poll");
		Thread.sleep(5000);

		String date = new SimpleDateFormat("yyyMMddHHmmssSS").format(new Date());
		driver.findElement(By.id("edit-create-poll-label-text")).sendKeys("Poll" +date);


		Select Duration= new Select(driver.findElement(By.id("edit-duration")));
		Duration.selectByValue("172800");
		Thread.sleep(5000);

		driver.findElement(By.xpath("//input[@value='Create Now']")).click(); //Create NOW Button
		Thread.sleep(5000);

		driver.findElement(By.linkText("Add New Question")).click();  //Add New Button
		Thread.sleep(5000);

		//Enter the Poll/Survey Question
		String date2 = new SimpleDateFormat("yyyMMddHHmmssSS").format(new Date());
		driver.findElement(By.xpath("//input[@id='edit-question-0-value']")).sendKeys("Sachin is Legend" +date2);  	  
		System.out.println("User Created a Poll Question...");
		Thread.sleep(5000);

		//Entering the Choices
		driver.findElement(By.xpath("//input[@name='choice[0][choice]']")).sendKeys("Yes");
		System.out.println("User entered choice as YES");
		Thread.sleep(4000);

		driver.findElement(By.xpath("//input[@id='edit-choice-add-more']")).sendKeys(Keys.ENTER);//Add another item button
		Thread.sleep(4000);

		driver.findElement(By.xpath("//input[@name='choice[1][choice]']")).sendKeys("No");
		System.out.println("User entered choice as NO");
		Thread.sleep(4000);

		driver.findElement(By.xpath("//input[@id='edit-submit']")).sendKeys(Keys.ENTER);//Save button
		Thread.sleep(2000);

		//2nd Question in Poll/Survey.

		driver.findElement(By.linkText("Add New Question")).click();  //Add New Button
		Thread.sleep(5000);

		//String date2 = new SimpleDateFormat("yyyMMddHHmmssSS").format(new Date());
		driver.findElement(By.xpath("//input[@id='edit-question-0-value']")).sendKeys("National Bird of India is" +date2);  	  
		System.out.println("User Created a Poll Question...");
		Thread.sleep(5000);

		//Entering the Choices
		driver.findElement(By.xpath("//input[@name='choice[0][choice]']")).sendKeys("Peacock");
		System.out.println("User entered choice as YES");
		Thread.sleep(4000);

		driver.findElement(By.xpath("//input[@id='edit-choice-add-more']")).sendKeys(Keys.ENTER);//Add another item button
		Thread.sleep(4000);

		driver.findElement(By.xpath("//input[@name='choice[1][choice]']")).sendKeys("Parrot");
		System.out.println("User entered choice as NO");
		Thread.sleep(4000);

		driver.findElement(By.xpath("//input[@id='edit-submit']")).sendKeys(Keys.ENTER);//Save button
		Thread.sleep(2000);

		//3nd Question in Poll/Survey.

		driver.findElement(By.linkText("Add New Question")).click();  //Add New Button
		Thread.sleep(5000);

		//String date2 = new SimpleDateFormat("yyyMMddHHmmssSS").format(new Date());
		driver.findElement(By.xpath("//input[@id='edit-question-0-value']")).sendKeys("Prime Minister Of India is" +date2);  	  
		System.out.println("User Created a Poll Question...");
		Thread.sleep(5000);

		//Entering the Choices
		driver.findElement(By.xpath("//input[@name='choice[0][choice]']")).sendKeys("Narendra Modi");
		System.out.println("User entered choice as YES");
		Thread.sleep(4000);

		driver.findElement(By.xpath("//input[@id='edit-choice-add-more']")).sendKeys(Keys.ENTER);//Add another item button
		Thread.sleep(4000);

		driver.findElement(By.xpath("//input[@name='choice[1][choice]']")).sendKeys("Amit Shah");
		System.out.println("User entered choice as NO");
		Thread.sleep(4000);

		driver.findElement(By.xpath("//input[@id='edit-submit']")).sendKeys(Keys.ENTER);//Save button
		Thread.sleep(2000);

		//4th Question in Poll/Survey.

		driver.findElement(By.linkText("Add New Question")).click();  //Add New Button
		Thread.sleep(5000);

		//String date2 = new SimpleDateFormat("yyyMMddHHmmssSS").format(new Date());
		driver.findElement(By.xpath("//input[@id='edit-question-0-value']")).sendKeys("Curret Captain of Indian Cricket Team is" +date2);  	  
		System.out.println("User Created a Poll Question...");
		Thread.sleep(5000);

		//Entering the Choices
		driver.findElement(By.xpath("//input[@name='choice[0][choice]']")).sendKeys("Rohit Sharma");
		System.out.println("User entered choice as YES");
		Thread.sleep(4000);

		driver.findElement(By.xpath("//input[@id='edit-choice-add-more']")).sendKeys(Keys.ENTER);//Add another item button
		Thread.sleep(4000);

		driver.findElement(By.xpath("//input[@name='choice[1][choice]']")).sendKeys("Virat Kholi");
		System.out.println("User entered choice as NO");
		Thread.sleep(4000);

		driver.findElement(By.xpath("//input[@id='edit-submit']")).sendKeys(Keys.ENTER);//Save button
		Thread.sleep(20000);


		System.out.println("Poll/Survey Question created Successfully..!!!");
		Thread.sleep(8000); 

		WebElement element22 = driver.findElement(By.linkText("Go to main content"));
		JavascriptExecutor executor22 = (JavascriptExecutor)driver;
		executor22.executeScript("arguments[0].click();", element22);
		Thread.sleep(8000); 


		//Add participants to existing Poll/Survey
		//String date = new SimpleDateFormat("yyyMMddHHmmssSS").format(new Date());
		driver.findElement(By.xpath("//*[@placeholder='Search Here']")).sendKeys("Poll" +date);
		System.out.println("Searched the poll");
		Thread.sleep(5000);

		//Test code for Add Participant
		// driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div/div[2]/div[2]/div/div/div/div[2]/div[2]/div/table/tbody/tr/td[8]/a[2]")).click();//Add Participant
		driver.findElement(By.linkText("Add Participant")).click();
		System.out.println("Clicked on Add Participant");
		Thread.sleep(6000);

		//Selecting users based on Region/Site/Client

		//select Region
		Select region = new Select(driver.findElement(By.name("select_region")));
		region.selectByValue("1");
		Thread.sleep(3000);

		//edit-select-client

		Select site = new Select(driver.findElement(By.id("edit-select-site")));
		site.selectByValue("1");
		Thread.sleep(3000);

		Select client = new Select(driver.findElement(By.id("edit-select-client")));
		client.selectByValue("1");
		Thread.sleep(3000);

		Select User1 = new Select(driver.findElement(By.xpath("//select[@id='Array-available']")));
		User1.selectByVisibleText("Mohit K (ISBL0430)");
		Thread.sleep(5000);
		System.out.println("user1 is selected");

		Select User2 = new Select(driver.findElement(By.xpath("//select[@id='Array-available']")));
		User2.selectByVisibleText("Sudesh D (ISBL02272)");
		Thread.sleep(5000);
		System.out.println("user2 is selected");

		Select User3 = new Select(driver.findElement(By.xpath("//select[@id='Array-available']")));
		User3.selectByVisibleText("Manish Kumar (13AZSB602)");
		Thread.sleep(5000);
		System.out.println("user3 is selected");


		Select User4 = new Select(driver.findElement(By.xpath("//select[@id='Array-available']")));
		User4.selectByVisibleText("Anusha G (ISBL02851)");
		Thread.sleep(5000);
		System.out.println("user4 is selected");

		Select User5 = new Select(driver.findElement(By.xpath("//select[@id='Array-available']")));
		User5.selectByVisibleText("guestone one (guestone)");
		Thread.sleep(5000);
		System.out.println("user5 is selected");

		Select User6 = new Select(driver.findElement(By.xpath("//select[@id='Array-available']")));
		User6.selectByVisibleText("Mahendra Kumar (ISBL0496)");
		Thread.sleep(5000);
		System.out.println("user6 is selected");


		Select User7 = new Select(driver.findElement(By.xpath("//select[@id='Array-available']")));
		User7.selectByVisibleText("Srinivas YV (13AZSB6090)");
		Thread.sleep(5000);
		System.out.println("user7 is selected");

		driver.findElement(By.xpath("//li[@class='multiselect-add']")).click();
		System.out.println("Clicked on add Icon to add user");
		Thread.sleep(4000);


		//Save
		WebElement element =driver.findElement(By.xpath("//input[@id='edit-submit']")); //Save
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		System.out.println("click Save to add participants");
		Thread.sleep(10000);

		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(60000);

		//1st Participant to Vote
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("username")).sendKeys(config.getRAdminUserName());//Region Admin
		driver.findElement(By.id("password")).sendKeys(config.getISONPassword());
		driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
		Thread.sleep(8000);

		driver.get(config. getmanagepoll());
		Thread.sleep(30000);

		//Search Poll/Survey Questions
		driver.findElement(By.id("edit-search-user-text")).sendKeys("Poll" +date);
		Thread.sleep(3000);

		driver.findElement(By.id("edit-submit")).click();
		Thread.sleep(5000);

		//Test code for Vote Now
		System.out.println("User starts voting now for his/her Poll/Survey Question..");
		driver.findElement(By.xpath("//a[@title='Vote Now']")).click();// Click VoteNow Button
		System.out.println("Clicked on Vote button");
		Thread.sleep(3000);

		//1st Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[1]")).click();
		//driver.findElement(By.xpath("(//input[@name='select_vote_choice'])[1]")).click(); //Option A
		System.out.println("Selected 1st chechbox option in popup Window");
		Thread.sleep(3000);

		//2nd Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[3]")).click();
		System.out.println("Answered for Second Question");
		Thread.sleep(3000);

		//3rd Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[5]")).click();
		System.out.println("Answered for Thrid question");
		Thread.sleep(3000);

		//4th Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[8]")).click();
		System.out.println("Answered for Thrid question");
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@value='Vote Now']")).click(); //Vote Now button
		System.out.println("Voted for the poll");
		Thread.sleep(3000);

		driver.findElement(By.linkText("Logout")).click();
		System.out.println("Logged Out");
		Thread.sleep(60000);



		//2nd Participant to Vote
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("username")).sendKeys(config.getSDHeadUserName());//SDHead
		driver.findElement(By.id("password")).sendKeys(config.getISONPassword());
		driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
		Thread.sleep(8000);

		driver.get(config. getmanagepoll());
		Thread.sleep(30000);

		//Search Poll/Survey Questions
		driver.findElement(By.id("edit-search-user-text")).sendKeys("Poll" +date);
		Thread.sleep(3000);

		driver.findElement(By.id("edit-submit")).click();
		Thread.sleep(5000);

		//Test code for Vote Now
		System.out.println("User starts voting now for his/her Poll/Survey Question..");
		driver.findElement(By.xpath("//a[@title='Vote Now']")).click();// Click VoteNow Button
		System.out.println("Clicked on Vote button");
		Thread.sleep(3000);

		//1st Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[1]")).click();
		//driver.findElement(By.xpath("(//input[@name='select_vote_choice'])[1]")).click(); //Option A
		System.out.println("Selected 1st chechbox option in popup Window");
		Thread.sleep(3000);

		//2nd Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[3]")).click();
		System.out.println("Answered for Second Question");
		Thread.sleep(3000);

		//3rd Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[5]")).click();
		System.out.println("Answered for Thrid question");
		Thread.sleep(3000);

		//4th Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[8]")).click();
		System.out.println("Answered for Thrid question");
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@value='Vote Now']")).click(); //Vote Now button
		System.out.println("Voted for the poll");
		Thread.sleep(3000);

		driver.findElement(By.linkText("Logout")).click();
		System.out.println("Logged Out");
		Thread.sleep(60000); 

		//3nd Participant to Vote
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("username")).sendKeys(config.getTrainerUserName());//Trainer
		driver.findElement(By.id("password")).sendKeys(config.getISONPassword());
		driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
		Thread.sleep(8000);

		driver.get(config. getmanagepoll());
		Thread.sleep(30000);

		//Search Poll/Survey Questions
		driver.findElement(By.id("edit-search-user-text")).sendKeys("Poll" +date);
		Thread.sleep(3000);

		driver.findElement(By.id("edit-submit")).click();
		Thread.sleep(5000);

		//Test code for Vote Now
		System.out.println("User starts voting now for his/her Poll/Survey Question..");
		driver.findElement(By.xpath("//a[@title='Vote Now']")).click();// Click VoteNow Button
		System.out.println("Clicked on Vote button");
		Thread.sleep(3000);

		//1st Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[1]")).click();
		//driver.findElement(By.xpath("(//input[@name='select_vote_choice'])[1]")).click(); //Option A
		System.out.println("Selected 1st chechbox option in popup Window");
		Thread.sleep(3000);

		//2nd Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[3]")).click();
		System.out.println("Answered for Second Question");
		Thread.sleep(3000);

		//3rd Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[5]")).click();
		System.out.println("Answered for Thrid question");
		Thread.sleep(3000);

		//4th Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[8]")).click();
		System.out.println("Answered for Thrid question");
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@value='Vote Now']")).click(); //Vote Now button
		System.out.println("Voted for the poll");
		Thread.sleep(3000);

		driver.findElement(By.linkText("Logout")).click();
		System.out.println("Logged Out");
		Thread.sleep(60000); 

		//4th Participant to Vote
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("username")).sendKeys(config.getTraineeUserName());//Trainee
		driver.findElement(By.id("password")).sendKeys(config.getISONPassword());
		driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
		Thread.sleep(8000);

		driver.get(config. getmanagepoll());
		Thread.sleep(30000);

		//Search Poll/Survey Questions
		driver.findElement(By.id("edit-search-user-text")).sendKeys("Poll" +date);
		Thread.sleep(3000);

		driver.findElement(By.id("edit-submit")).click();
		Thread.sleep(5000);

		//Test code for Vote Now
		System.out.println("User starts voting now for his/her Poll/Survey Question..");
		driver.findElement(By.xpath("//a[@title='Vote Now']")).click();// Click VoteNow Button
		System.out.println("Clicked on Vote button");
		Thread.sleep(3000);

		//1st Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[1]")).click();
		//driver.findElement(By.xpath("(//input[@name='select_vote_choice'])[1]")).click(); //Option A
		System.out.println("Selected 1st chechbox option in popup Window");
		Thread.sleep(3000);

		//2nd Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[3]")).click();
		System.out.println("Answered for Second Question");
		Thread.sleep(3000);

		//3rd Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[5]")).click();
		System.out.println("Answered for Thrid question");
		Thread.sleep(3000);

		//4th Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[8]")).click();
		System.out.println("Answered for Thrid question");
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@value='Vote Now']")).click(); //Vote Now button
		System.out.println("Voted for the poll");
		Thread.sleep(3000);

		driver.findElement(By.linkText("Logout")).click();
		System.out.println("Logged Out");
		Thread.sleep(60000); 

		//5th Participant to Vote
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("username")).sendKeys(config.getClientUserName());//Client
		driver.findElement(By.id("password")).sendKeys(config.getISONPassword());
		driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
		Thread.sleep(8000);

		driver.get(config. getmanagepoll());
		Thread.sleep(30000);

		//Search Poll/Survey Questions
		driver.findElement(By.id("edit-search-user-text")).sendKeys("Poll" +date);
		Thread.sleep(3000);

		driver.findElement(By.id("edit-submit")).click();
		Thread.sleep(5000);

		//Test code for Vote Now
		System.out.println("User starts voting now for his/her Poll/Survey Question..");
		driver.findElement(By.xpath("//a[@title='Vote Now']")).click();// Click VoteNow Button
		System.out.println("Clicked on Vote button");
		Thread.sleep(3000);

		//1st Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[1]")).click();
		//driver.findElement(By.xpath("(//input[@name='select_vote_choice'])[1]")).click(); //Option A
		System.out.println("Selected 1st chechbox option in popup Window");
		Thread.sleep(3000);

		//2nd Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[3]")).click();
		System.out.println("Answered for Second Question");
		Thread.sleep(3000);

		//3rd Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[5]")).click();
		System.out.println("Answered for Thrid question");
		Thread.sleep(3000);

		//4th Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[8]")).click();
		System.out.println("Answered for Thrid question");
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@value='Vote Now']")).click(); //Vote Now button
		System.out.println("Voted for the poll");
		Thread.sleep(3000);
		
		
		driver.findElement(By.linkText("Logout")).click();
		System.out.println("Logged Out");
		Thread.sleep(60000); 

		//6th Participant to Vote
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("username")).sendKeys(config.getGuestUserName());//guestone
		driver.findElement(By.id("password")).sendKeys(config.getISONPassword());
		driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
		Thread.sleep(8000);

		driver.get(config. getmanagepoll());
		Thread.sleep(30000);
		//Search Poll/Survey Questions
		driver.findElement(By.id("edit-search-user-text")).sendKeys("Poll" +date);
		Thread.sleep(3000);

		driver.findElement(By.id("edit-submit")).click();
		Thread.sleep(5000);

		//Test code for Vote Now
		System.out.println("User starts voting now for his/her Poll/Survey Question..");
		driver.findElement(By.xpath("//a[@title='Vote Now']")).click();// Click VoteNow Button
		System.out.println("Clicked on Vote button");
		Thread.sleep(3000);

		//1st Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[1]")).click();
		//driver.findElement(By.xpath("(//input[@name='select_vote_choice'])[1]")).click(); //Option A
		System.out.println("Selected 1st chechbox option in popup Window");
		Thread.sleep(3000);

		//2nd Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[3]")).click();
		System.out.println("Answered for Second Question");
		Thread.sleep(3000);

		//3rd Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[5]")).click();
		System.out.println("Answered for Thrid question");
		Thread.sleep(3000);

		//4th Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[8]")).click();
		System.out.println("Answered for Thrid question");
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@value='Vote Now']")).click(); //Vote Now button
		System.out.println("Voted for the poll");
		Thread.sleep(3000);


		driver.findElement(By.linkText("Logout")).click();
		System.out.println("Logged Out");
		Thread.sleep(60000); 

		//7th Participant to Vote
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("username")).sendKeys(config.getSiteHeadUserName());//siteHead
		driver.findElement(By.id("password")).sendKeys(config.getISONPassword());
		driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
		Thread.sleep(8000);

		driver.get(config. getmanagepoll());
		Thread.sleep(30000);

		//Search Poll/Survey Questions
		driver.findElement(By.id("edit-search-user-text")).sendKeys("Poll" +date);
		Thread.sleep(3000);

		driver.findElement(By.id("edit-submit")).click();
		Thread.sleep(5000);

		//Test code for Vote Now
		System.out.println("User starts voting now for his/her Poll/Survey Question..");
		driver.findElement(By.xpath("//a[@title='Vote Now']")).click();// Click VoteNow Button
		System.out.println("Clicked on Vote button");
		Thread.sleep(3000);

		//1st Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[1]")).click();
		//driver.findElement(By.xpath("(//input[@name='select_vote_choice'])[1]")).click(); //Option A
		System.out.println("Selected 1st chechbox option in popup Window");
		Thread.sleep(3000);

		//2nd Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[3]")).click();
		System.out.println("Answered for Second Question");
		Thread.sleep(3000);

		//3rd Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[5]")).click();
		System.out.println("Answered for Thrid question");
		Thread.sleep(3000);

		//4th Question
		driver.findElement(By.xpath("(//input[@class='form-radio form-check-input'])[8]")).click();
		System.out.println("Answered for Thrid question");
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@value='Vote Now']")).click(); //Vote Now button
		System.out.println("Voted for the poll");
		Thread.sleep(3000);

		driver.findElement(By.linkText("Logout")).click();
		System.out.println("Logged Out");
		Thread.sleep(60000); 


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
