import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import com.google.common.base.Function;

import Utility.ConfigReader;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class addEditDelClient {
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
	public void testAddEditDelClient() throws Exception {
		ConfigReader config= new ConfigReader();
		driver.get(config.getApplicationUrl());

		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(config.getSGAUserName());
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(config.getSGAUserPassword());
		driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
		Thread.sleep(30000);
		// Move to Left Navigation Bar
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

		driver.findElement(By.linkText("Demography")).click();
		Thread.sleep(3000);

		driver.findElement(By.linkText("Clients")).click();
		Thread.sleep(3000);

		//Add Client

		driver.findElement(By.linkText("Add")).click();
		System.out.println("Added Client !!");
		Thread.sleep(3000);


		driver.findElement(By.name("client_name")).sendKeys("ClientTEst");
		Thread.sleep(4000);

		WebElement regionDropDown1 = driver.findElement(By.name("location_name[]"));  
		Select dropdown1 = new Select(regionDropDown1);
		dropdown1.selectByValue("1");
		Thread.sleep(4000);

		driver.findElement(By.xpath("//option[text()='Bangalore']")).click();
		Thread.sleep(4000);

		driver.findElement(By.xpath("//text()[contains(.,'Add')]/ancestor::li[1]")).click();
		Thread.sleep(4000);



		driver.findElement(By.name("op")).click(); //Save
		Thread.sleep(5000);
		System.out.println("Client added Successfully !!");
		Thread.sleep(8000);

		/*boolean var6= driver.findElement(By.xpath("//input[@type='search']")).isEnabled();
		System.out.println(var6);
		if (var6==true);
		{
			driver.findElement(By.xpath("//input[@type='search']")).sendKeys("ClientTEst");
		}
		Thread.sleep(2000); */

		//driver.findElement(By.xpath("//input[@type='search']")).sendKeys("ClientTEst");

		//driver.findElement(By.xpath("//*[@placeholder='Search Here']")).sendKeys("ClientTEst");
		//Thread.sleep(3000);

		driver.findElement(By.xpath("//text()[contains(.,'Search')]/ancestor::label[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//text()[contains(.,'Edit')]/ancestor::a[1]")).click();
		Thread.sleep(5000);
		//driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div/div[2]/div/div/div/div/div[2]/div/table/tbody/tr[7]/td[6]/a[1]")).click();//Edit
		driver.findElement(By.name("description")).click();
		Thread.sleep(5000);
		driver.findElement(By.name("description")).clear();
		Thread.sleep(3000);
		driver.findElement(By.name("description")).sendKeys("NewClient");
		Thread.sleep(2000);

		driver.findElement(By.name("op")).click();
		Thread.sleep(5000);
		System.out.println("Edited the Client Successfully !!"); 
		Thread.sleep(4000);


		//Delete Client
		System.out.println("Started Deleting the Client !!");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@placeholder='Search Here']")).sendKeys("ClientTEst");
		Thread.sleep(4000);

		boolean var3= driver.findElement(By.xpath("//text()[contains(.,'Search')]/ancestor::label[1]")).isDisplayed();
		System.out.println(var3);
		if (var3==true)
		{
			driver.findElement(By.xpath("//text()[contains(.,'Search')]/ancestor::label[1]")).click();
		}
		Thread.sleep(8000);

		boolean var4= driver.findElement(By.linkText("Delete")).isDisplayed();
		System.out.println(var4);
		if (var4==true)
		{
			driver.findElement(By.linkText("Delete")).sendKeys(Keys.ENTER);
			Thread.sleep(3000);
		}


		driver.findElement(By.linkText("Logout")).click();
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
