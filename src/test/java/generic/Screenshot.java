package generic;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot extends BaseTest{
	
	public static String takeScreenshot(String shot) throws IOException 
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		
		File source = ts.getScreenshotAs(OutputType.FILE);
		File desctination = new File(SCREENSHOTPATH + shot + ".png");
		
		FileUtils.copyFile(source, desctination);
		
		return desctination.getAbsolutePath();
	}
}
