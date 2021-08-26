package ConfigurationDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.testng.annotations.Test;

public class TestSeleniumBlog {

	@Test
	public  void testConfig() throws Exception {
		
		File src=new File("./Configuration/Config.property");
		FileInputStream fis = new FileInputStream(src);
		Properties pro = new Properties();
		pro.load(fis);
		
		String URLPath= pro.getProperty("URL");
		System.out.println("URllocation  == " +URLPath);
		

	}

}
