import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import Utility.ConfigReader;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class addEditDelSite {
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
	public void testAddEditDelSite() throws Exception {
		ConfigReader config= new ConfigReader();
		driver.get(config.getApplicationUrl());

		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(config.getSGAUserName());
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(config.getSGAUserPassword());
		driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
		Thread.sleep(60000);

		/// Click On Send Anyway button if the prompt is displaying
		boolean var=  driver.findElement(By.xpath("//li[@class='menuparent menu-item menu-item--expanded'][1]")).isDisplayed();
		System.out.println(var);
		if (var==true)
		{
			driver.findElement(By.xpath("//li[@class='menuparent menu-item menu-item--expanded'][1]")).click();
		}
		Thread.sleep(20000);

		WebElement menuBar1 = driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[3]/div/ul/li[3]/a"));
		Actions act1 = new Actions (driver);
		act1.moveToElement(menuBar1).perform();
		driver.findElement(By.linkText("Demography")).click();
		//Thread.sleep(5000);
		driver.findElement(By.linkText("Sites")).click();
		//Thread.sleep(3000);


		//Add Site
		System.out.println("Started to Add Site !!");
		driver.findElement(By.linkText("Add")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("site_name")).click();
		driver.findElement(By.name("site_name")).clear();
		driver.findElement(By.name("site_name")).sendKeys("Mysuru");
		Thread.sleep(2000);
		WebElement regionDropDown1 = driver.findElement(By.name("location_name"));  
		Select dropdown1 = new Select(regionDropDown1);
		dropdown1.selectByValue("1");
		Thread.sleep(2000);
		driver.findElement(By.name("description")).sendKeys("Mysuru desc");
		Thread.sleep(2000);
		driver.findElement(By.name("op")).click();
		//Thread.sleep(2000);
		System.out.println("Site added Successfully !!");
		Thread.sleep(8000);

		/* Pagination Code
    List<webElement> pagination =driver.findElement(By.xpath("//div[@class='nav-pages']//a")); 


    webElement NextButton= driver.findElement(By.xpath("//*[@id='nextbutton id']"));

    webElement prevButton= driver.findElement(By.xpath("//*[@id='prevButtonid']"));

    int LastPageNumber = (int)driver.findElement(By.xpath("//*[@text='>>]/preceding::/span[1]")); 

    // checkif pagination link exists
    if(pagination.size()>0)
    { 
    sop("pagination exists"); 

    // click on pagination link 

    for(int i=1; i <pagination.size(); i++)
    { 

    //Check if nextbutton is enable or not.

       if(NextButton.isEnabled())
    {

        NextButton.click();
    }
    else { 
    sop("pagination not exists"); 
    } 
    }}
		 */

		// Code to navigate the Page index
		//driver.findElement(By.linkText("2")).click();
		//Thread.sleep(5000); 


		//Edit Site
		System.out.println("Started Editing the Site !!");
		driver.findElement(By.xpath("//*[@placeholder='Search Here']")).sendKeys("Mysuru");
		Thread.sleep(6000);
		driver.findElement(By.xpath("//text()[contains(.,'Search')]/ancestor::label[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//text()[contains(.,'Edit')]/ancestor::a[1]")).click();
		Thread.sleep(3000);

		//driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div/div[2]/div[2]/div/div/div/div[2]/div/div/div/div/div[2]/div/table/tbody/tr[3]/td[6]/a[1]")).click();//Edit
		driver.findElement(By.name("description")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("description")).clear();
		Thread.sleep(2000);
		driver.findElement(By.name("description")).sendKeys("edit mysuru description");
		Thread.sleep(2000);
		driver.findElement(By.name("op")).click();
		Thread.sleep(2000);
		System.out.println("Editing the Site was Successful !!");
		//Thread.sleep(2000);


		//Delete Site
		System.out.println("Started Deleting the Site !!");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@placeholder='Search Here']")).sendKeys("Mysuru");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//text()[contains(.,'Search')]/ancestor::label[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//text()[contains(.,'Delete')]/ancestor::a[1]")).click();
		Thread.sleep(2000);
		//driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div/div[2]/div[2]/div/div/div/div[2]/div/div/div/div/div[2]/div/table/tbody/tr[3]/td[6]/a[2]")).click(); //Delete
		//Thread.sleep(3000);
		//driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div/div[2]/div[2]/div/div/form/div/input")).click(); //Delete it
		driver.findElement(By.id("edit-submit")).click();
		System.out.println("Deleted the Site Successfully !!");
		Thread.sleep(3000);


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
