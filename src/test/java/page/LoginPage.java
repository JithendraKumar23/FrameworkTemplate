package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class LoginPage {
		
	// Declaration
	@FindBy(id = "input-username")
	private WebElement userName;
	
	@FindBy(id = "input-password")
	private WebElement password;
	
	@FindBy(name = "login-button")
	private WebElement loginBtn;
	
	@FindBy(linkText = "Logout")
	private WebElement logoutBtn;
	
	// Initialization
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	// Utilization
	public void login(String un, String pw) 
	{
		userName.sendKeys(un);
		password.sendKeys(pw);
		loginBtn.click();
	}
	 
	public boolean validateLogin() 
	{
		String actualText = logoutBtn.getText();
		String expectedText = "Logout";
		
		boolean flags = false;
		
		if(actualText.equalsIgnoreCase(expectedText))
		{
			Reporter.log("**Login is success**" , true );
			flags = true;
		} 
		else
		{
			Reporter.log("XX Login is failed XX" , true );
		}
		
		return flags;
	}
	

}
