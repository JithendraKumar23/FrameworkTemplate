package generic;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;

public class Listeners extends BaseTest implements ITestListener{
	
	public void onTestStart(ITestResult result)
	{
		Reporter.log("LIST : TestCase Started " , true);
	}
	
	public void onTestSuccess(ITestResult result)
	{
		extentTest = extentReports.createTest(result.getName());
		extentTest.pass(result.getName() + " : TestCase is Pass");
		extentTest.assignAuthor("Jithendra");
		extentTest.assignCategory("Smoke" , "Regression");
		extentTest.assignDevice("Chrome");
	}
	  
	public void onTestFailure(ITestResult result)
	{
		try {
			extentTest = extentReports.createTest(result.getName())
			//extentTest.fail(result.getName() + " : TestCase is Fail");
			.log(Status.FAIL, result.getThrowable())
			.info(MediaEntityBuilder.createScreenCaptureFromPath(Screenshot.takeScreenshot(result.getName())).build());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		try
//		{
//			String screenshot = Screenshot.takeScreenshot(result.getName());
//			extentTest.addScreenCaptureFromPath(screenshot);
//		} 
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
		
	}
	
}