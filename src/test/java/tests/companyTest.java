package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.companyPage;
import pages.homePage;
import utilities.Constants;
import utilities.excelUtils;

public class companyTest extends baseTest
{
	public companyPage cPage;
	public homePage hPage;
	
	@BeforeClass
	public void doLogin()
	{
		hPage = lPage.doLogin(prop.getProperty("un"), prop.getProperty("pw"));
		hPage.validateUserNameOnHomePage();
		cPage  = hPage.clickCompaniesOption();
	}

//new code	
	@Test
	public void registerCompany() throws InterruptedException
	{
//		cPage.newCompanyRegistration(companyname, industry, status);
////		String actualText = cPage.statusChildWindowText;
////		Assert.assertEquals(actualText, Constants.expectedChildWindowTitlte);
//		hPage.clickCompaniesOption();
	cPage.fetchCompanyTableData();
	}
	
//	@DataProvider
//	public Object[][] loadData()
//	{
//		return excelUtils.fetchExcelData("companyRegistration");
//	}

}
