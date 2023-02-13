package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import factory.driverFactory;
import utilities.elementUtils;

public class loginPage 
{
	public WebDriver driver;
	public driverFactory factory;
	public elementUtils eUtils;
	private By userName = By.xpath("//input[@name='username']");
	private By password = By.xpath("//input[@name='password']");
	private By loginBtn = By.xpath("//input[@value='Login']");
	Logger log = LogManager.getLogger("loginPage");
	public loginPage(WebDriver driver)
	{
		this.driver = driver;
		eUtils = new elementUtils(driver);
	}
	
	public homePage doLogin(String userName, String password)
	{
		eUtils.doSendKeys(this.userName, userName);
		log.info("user name entered successfully");
		eUtils.doSendKeys(this.password, password);
		log.info("password name entered successfully");
		factory.takeScreenshot();
		eUtils.doClick(loginBtn);
		factory.takeScreenshot();
		log.info("Login button clicked successfully");
		return new homePage(driver);

	}
	
	
	
	

}
