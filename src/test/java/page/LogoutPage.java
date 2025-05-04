package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage {
		
	@FindBy(linkText = "Logout")
	private WebElement logoutBtn;
	
	public LogoutPage(WebDriver driver)  
	{
		PageFactory.initElements(driver, this);
	}
	
	public void logout() 
	{
		//wait.until(ExpectedConditions.elementToBeClickable(logoutBtn));
		logoutBtn.click();
	}
	

}
