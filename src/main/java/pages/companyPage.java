package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.elementUtils;
import utilities.excelReader;

public class companyPage 
{
	public WebDriver driver;
	public elementUtils eUtils;
	private By companyName = By.xpath("//*[@id='company_name']");
	private By industry = By.xpath("//*[@name='industry']");
	private By statusDropDown = By.xpath("//*[@id='companyForm']//tbody/tr[5]/td[2]/select");
	private By statusQuestion = By.xpath("//*[@id='companyForm']//tbody/tr[5]/td[2]/select/following-sibling::input");
	private By childWindowHeaderText = By.xpath("/html/body/table/tbody/tr[1]/td[1]/p/span");
	public String statusChildWindowText = null;
	private By savebutton = By.xpath("//*[@id='companyForm']/table/tbody/tr[1]/td/input");
	public excelReader reader;
	private By companyInMenu = By.xpath("//a[@title='Companies']");
	private By companyTableHeader = By.xpath("//*[@id='vCompaniesForm']/table/tbody/tr[1]/td/table/tbody/tr/td[1]");
	private String beforeXpath = "//*[@id='vCompaniesForm']/table/tbody/tr[";
	private String afterXpath = "]/td[2]/a";
	private By companyRows = By.xpath("//*[@id='vCompaniesForm']/table/tbody/tr");
	
	public companyPage(WebDriver driver)
	{
		this.driver = driver;
		eUtils = new elementUtils(driver);
	}
	
	
	public void newCompanyRegistration(String companyName, String industry, String status) throws InterruptedException
	{
		eUtils.doSendKeys(this.companyName, companyName);
		eUtils.doSendKeys(this.industry, industry);
		eUtils.clickAndWait(statusDropDown, 2);
		eUtils.selectByVisibleText(statusDropDown, status);
		//eUtils.doClick(statusQuestion);
		//Iterator<String> it = eUtils.handleWindows();
		//String parent = it.next();
		//String child  = it.next();
		//driver.switchTo().window(child);
		//statusChildWindowText = eUtils.getText(childWindowHeaderText).trim();
		//driver.switchTo().defaultContent();
		Thread.sleep(5000);
		eUtils.doClick(savebutton);
		//System.out.println(text);
	}
	
	
	public void fetchCompanyTableData()
	{
		eUtils.doClick(companyInMenu);
		eUtils.webDriverWait(companyTableHeader, 5);
		reader = new excelReader("./src/test/resources/testData/data.xlsx");
		reader.addSheet("companyData");
		reader.addColumn("companyData", "companyName");
		List<WebElement> rowsCount = eUtils.getElements(companyRows);
		int row = rowsCount.size();
		for(int i=5;i<=row;i++)
		{
			String company = beforeXpath+i+afterXpath;
			String companyName = eUtils.getText(By.xpath(company));
			
			reader.setCellData("companyData", "companyName", i, companyName);	
		}
	

	}
	
	
	
	
	
	

}
