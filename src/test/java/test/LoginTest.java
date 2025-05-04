package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Utilities;
import page.LoginPage;

public class LoginTest extends BaseTest {
	
	@Test
	public void loginTestCase() throws InterruptedException
	{
		LoginPage loginPage = new LoginPage(driver);
		String userName = Utilities.getDataFromExcel(EXCELPATH, "LoginData", 0, 1);
		String password = Utilities.getDataFromExcel(EXCELPATH, "LoginData", 1, 1);
		loginPage.login(userName , password);
		//Thread.sleep(5000);
		boolean flag = loginPage.validateLogin();
		Assert.assertEquals(flag, true);
	}

}
