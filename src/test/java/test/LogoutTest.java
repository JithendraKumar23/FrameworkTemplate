package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import page.LogoutPage;


public class LogoutTest extends BaseTest{
	
	@Test
	public void logoutTestCase() throws InterruptedException
	{
		LogoutPage logoutPage = new LogoutPage(driver);
		logoutPage.logout(); 
		//Assert.fail();
	}
}
