package tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import factory.driverFactory;
import pages.companyPage;
import pages.homePage;
import pages.loginPage;

public class baseTest 
{
	
	public WebDriver driver;
	public Properties prop;
	public driverFactory factory;
	public loginPage lPage;
	public homePage hPage;
	public companyPage cPage;
	//String screenshot;
	
	@BeforeClass
	public void setUp()
	{
		factory = new driverFactory();
		prop = factory.init_prop();
		//factory.takeScreenshot();
		driver = factory.getBrowser(prop);
		lPage = new loginPage(driver);
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}

}
