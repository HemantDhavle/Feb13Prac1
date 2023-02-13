package utilities;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class elementUtils 
{
	public WebDriver driver;
	public WebDriverWait wait;
	
	public elementUtils(WebDriver driver)
	{
		this.driver = driver;
	}
	public By getBy(String locatorType, String locatorValue)
	{
		By locator = null;
		
		switch (locatorType.toLowerCase()) {
		case "id":
			locator = By.id(locatorValue);
			break;
		case "name":
			locator = By.name(locatorValue);
			break;
		case "classname":
			locator = By.className(locatorValue);
			break;
		case "xpath":
			locator = By.xpath(locatorValue);
			break;
		case "cssselector":
			locator = By.cssSelector(locatorValue);
			break;
		case "linktext":
			locator = By.linkText(locatorValue);
			break;
		case "partiallinktext":
			locator = By.partialLinkText(locatorValue);
			break;
		default:
			System.out.println("Please pass the correct locator type "+locatorType) ;
			break;
		}
		return locator;
	}

	public WebElement getElement(By locator)
	{
		return driver.findElement(locator);
	}
	
	public List<WebElement> getElements(By locator)
	{
		return driver.findElements(locator);
	}
	
	public void doClick(By locator)
	{
		driver.findElement(locator).click();
	}
	
	public void doSendKeys(By locator, String value)
	{
		driver.findElement(locator).sendKeys(value);
	}
	
	public String getText(By locator)
	{
		return driver.findElement(locator).getText();
	}
	
	public WebElement webDriverWait(By locator, int duration)
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void clickAndWait(By locator, int duration)
	{
		webDriverWait(locator, duration).click();
	}
	
	public void sendKeysWait(By locator, int duration, String text)
	{
		webDriverWait(locator, duration).sendKeys(text);
	}
	
	public Select selectDropDown(By locator)
	{
		Select sel = new Select(getElement(locator));
		return sel;
	}
	
	public void selectByIndex(By locator, int index)
	{
		selectDropDown(locator).selectByIndex(index);
	}
	

	public void selectByValue(By locator, String value)
	{
		selectDropDown(locator).selectByValue(value);
	}
	
	public void selectByVisibleText(By locator, String text)
	{
		selectDropDown(locator).selectByVisibleText(text);
	}
	
	public List<WebElement> getAllSelectDropDownValue(By locator)
	{
		return selectDropDown(locator).getOptions();
	}
	
	public Actions actions()
	{
		Actions act = new Actions(driver);
		return act;
	}
	
	public void doDoubleClick(By locator)
	{
		actions().doubleClick(getElement(locator)).build().perform();	
	}
	
	public void doRightClick(By locator)
	{
		actions().contextClick(getElement(locator)).build().perform();	
	}
	
	public void clickAndHold(By locator)
	{
		actions().clickAndHold(getElement(locator)).build().perform();
	}
	
	public void moveToElement(By locator)
	{
		actions().moveToElement(getElement(locator)).build().perform();
	}
	public void handleFrame(String framename)
	{
		driver.switchTo().frame(framename);
}
	
	public Iterator<String> handleWindows()
	{
		//String parentWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();	
		return it;
	}
	
	
}
