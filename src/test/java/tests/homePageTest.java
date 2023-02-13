package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utilities.Constants;

public class homePageTest extends baseTest
{
	//public homePage hPage;
	@BeforeClass
	public void doLogin()
	{
		hPage  = lPage.doLogin(prop.getProperty("un"), prop.getProperty("pw"));
		//hPage = new homePage(driver);
	}
	
	@Test(priority=1)
	public void validateTitle()
	{
		String actualText = hPage.validateUserNameOnHomePage();
		System.out.println(actualText);
		System.out.println(Constants.homePageTitle);
		Assert.assertTrue(actualText.contains(Constants.homePageTitle));
		hPage.clickCompaniesOption();

	}
	

}
