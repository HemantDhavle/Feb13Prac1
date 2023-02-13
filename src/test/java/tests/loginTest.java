package tests;

import org.testng.annotations.Test;

public class loginTest extends baseTest{
	
	@Test
	public void doLogin()
	{
		lPage.doLogin(prop.getProperty("un"), prop.getProperty("pw"));
	}

}
