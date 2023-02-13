package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.elementUtils;

public class homePage {
	public WebDriver driver;
	public elementUtils eUtils;
	private By welcomePageTitle = By.xpath("//td[contains(text(),'Hemant')]");
	private By companyInMenu = By.xpath("//a[@title='Companies']");
	private By newCompanyOption = By.xpath("//*[@id='navmenu']/ul/li[3]/ul/li[1]/a");
	
	public homePage(WebDriver driver)
	{
		this.driver = driver;
		eUtils = new elementUtils(driver);
	}
	
	public String validateUserNameOnHomePage()
	{
		eUtils.handleFrame("mainpanel");
		WebElement visible= eUtils.webDriverWait(welcomePageTitle, 10);
		if(visible.isDisplayed())
		{
			return eUtils.getText(welcomePageTitle);
		}
		else
		{
			return null;
		}
	}
	
	public companyPage clickCompaniesOption()
	{
		eUtils.webDriverWait(companyInMenu, 10);
		eUtils.moveToElement(companyInMenu);
		eUtils.doClick(companyInMenu);
		return new companyPage(driver);
		
	}
	
}
