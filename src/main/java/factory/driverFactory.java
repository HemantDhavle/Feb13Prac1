package factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class driverFactory 
{
	public WebDriver driver;
	public Properties prop;
	public optionManager om;
	private static ThreadLocal<WebDriver> tl = new ThreadLocal<WebDriver>();
	
	public WebDriver getBrowser(Properties prop)
	{
		String browserName = prop.getProperty("browser");
		om = new optionManager(prop);
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			tl.set(new ChromeDriver(om.chromeOptions()));
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			tl.set(new FirefoxDriver(om.firefoxOptions()));
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			tl.set(new EdgeDriver(om.edgeOptions()));
		}
		else
		{
			System.out.println("Please pass the valid browser "+browserName);
		}
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();
		
	}
	
	public synchronized static WebDriver getDriver()
	{
		return tl.get();
	}
	
	public Properties init_prop()
	{
		prop = new Properties();
		FileInputStream fs = null;
		
		try {
			fs = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(fs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	

	public static String takeScreenshot()
	{
		File src  = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
		File des = new File(path);
		try {
			FileUtils.copyFile(src, des);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	

}
