package generic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public abstract class Utilities {
	
	public static String getProperties(String env , String keyValue) 
	{ 
		String value = "";
		try 
		{
			Properties properties = new Properties();
			properties.load(new FileInputStream(env));
			value = properties.getProperty(keyValue);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return value;
	}
	
	public static String getDataFromExcel(String file , String sheet , int row , int cell) 
	{
		String valueExcel = "";
		try 
		{
			Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
			valueExcel = workbook.getSheet(sheet).getRow(row).getCell(cell).toString();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return valueExcel ;	
	}

}
