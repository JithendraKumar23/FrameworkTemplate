package generic;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseTest implements IAutoConstant{ 
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	
	public static ExtentReports extentReports; 
	public static ExtentSparkReporter extentSparkReporter;
	public static ExtentTest extentTest;
	
	@Parameters({"grid" , "grid_url" , "browser" , "app_url" , "env"})
	
	@BeforeTest
	public void launchBrowser(@Optional(GRID) String grid , @Optional(GRID_URL) String grid_url, @Optional(BROWSER) String browser , @Optional(APP_URL)String app_url , @Optional(ENV) String env) throws MalformedURLException
	{ 
		
		if(grid.equalsIgnoreCase("yes"))
		{
			URL url = new URL(grid_url);
			
			if(browser.equalsIgnoreCase("chrome"))
			{
				ChromeOptions chromeOptions = new ChromeOptions();
				driver = new RemoteWebDriver(url, chromeOptions);
				Reporter.log("Server : Chrome Browser Launched " , true);
			}
			else if (browser.equalsIgnoreCase("firefox")) 
			{
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				driver = new RemoteWebDriver(url, firefoxOptions);
				Reporter.log("Server : Firefox Browser Launched " , true);
			}
			else
			{
				EdgeOptions edgeOptions = new EdgeOptions();
				driver = new RemoteWebDriver(url, edgeOptions);
				Reporter.log("Server : Edge Browser Launched " , true);
			}
		}
		else
		{
			if(browser.equalsIgnoreCase("chrome"))
			{
				driver = new ChromeDriver();
				Reporter.log("Local : Chrome Browser Launched " , true);
			}
			else if (browser.equalsIgnoreCase("firefox")) 
			{
				driver = new FirefoxDriver();
				Reporter.log("Local : Firefox Browser Launched " , true);
			}
			else
			{
				driver = new EdgeDriver();
				Reporter.log("Local : Edge Browser Launched " , true);
			}
		}
		
		// Launch Browser from PROPERTIES file
		String appn_url = Utilities.getProperties(env, "APPPLICATION_URL");
		driver.get(appn_url);
		Reporter.log("APP URL : " + appn_url, true); 
		
		//maximize the browser
	    driver.manage().window().maximize();
		
		// Launch Browser from TESTNG/OPTIONAL file
//		 driver.get(app_url);	
//		 Reporter.log("APP URL : " + app_url, true);
		
		// ITO
		String ITO = Utilities.getProperties(env, "ITO");
		int implictWait = Integer.parseInt(ITO);
		Reporter.log("ITO : " + implictWait, true);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implictWait));

		// ETO
		String ETO = Utilities.getProperties(env, "ETO");
		int explicitWait = Integer.parseInt(ETO);
		Reporter.log("ITO : " + explicitWait, true);
		wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
	}
	
	@AfterMethod
	public void takeScreenshot(ITestResult result) throws IOException
	{ 
		Reporter.log("TestCase Name : " + result.getName() , true);
		String testCaseName = result.getName();
		int status = result.getStatus();
		Reporter.log("TestStatus : " + status , true);
		Reporter.log("SUCCESS = 1 , FAILURE = 2 , SKIP = 3;" , true);
		
		if(status == 2)
		{
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File scr_file = screenshot.getScreenshotAs(OutputType.FILE);
//			File dest_file = new File(SCREENSHOTPATH+ testCaseName +".png");
//			
//			FileUtils.copyFile(scr_file, dest_file);
//			Reporter.log(testCaseName + " : TestCase is failed & Screenshot is taken" , true);
//			System.out.println();
			
			Screenshot.takeScreenshot(testCaseName);
			Reporter.log(testCaseName + " : TestCase is failed & Screenshot is taken" , true);
		}
		else
		{
			Reporter.log(testCaseName + " : TestCase is passed..!!!" , true);
			System.out.println();
		}	
	}
	
	@AfterTest
	public void closeBroser()
	{
		//driver.quit();
	}
	
	@BeforeSuite
	public void createExtentReport() throws IOException
	{
		extentReports = new ExtentReports();
		extentSparkReporter = new ExtentSparkReporter("./extentReport/ExtentReport.html");
		extentReports.attachReporter(extentSparkReporter);
		
		// System releted info to the report
		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		
		extentSparkReporter.loadJSONConfig(new File("./extentConfig/spark-config.json"));
	}
	
	@AfterSuite
	public void flushReport() throws IOException
	{
		extentReports.flush();
		
		// Static Report Path
		//Desktop.getDesktop().browse(new File("C:\\Users\\jeeth\\OneDrive\\Documents\\Workspace\\Project1\\extentReport\\ExtentReport.html").toURI());
		
        // Get the current working directory (i.e., project root)
        String projectPath = System.getProperty("user.dir");
        
        // Build the path to the report file
        String reportPath = projectPath + File.separator + "extentReport" + File.separator + "ExtentReport.html";
        
        // Open the report in the default browser
        File reportFile = new File(reportPath);
        if (reportFile.exists()) 
        {
            Desktop.getDesktop().browse(reportFile.toURI());
        } 
        else 
        {
        	Reporter.log("Report file not found: " + reportPath , true);
        }

	}
}
