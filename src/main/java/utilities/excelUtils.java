package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class excelUtils 
{
	public static Properties prop;
	public static FileInputStream fs= null;
	public static Workbook book;
	public static Sheet sheet;
	
	public static Object[][] fetchExcelData(String sheetName)
	{
		prop = new Properties();
		Object[][] data = null;
		try {
			fs = new FileInputStream("./src/test/resources/testData/data.xlsx");
			book = WorkbookFactory.create(fs);
			sheet = book.getSheet(sheetName);
		} catch (IOException | InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		data = new Object[rows][cols];
		
		for (int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				data[i][j]= sheet.getRow(i+1).getCell(j).toString();
			}
		}
		return data;
		
	}
//	public static void main (String args[])
//	{
//		Object[][] loadData= fetchExcelData("companyRegistration");
//		int rows = loadData.length;
//		int cols = loadData[0].length;
//		
//		for(int i =0;i<rows;i++)
//		{
//			for(int j=0;j<cols;j++)
//			{
//				System.out.println(loadData[i][j]);
//			}
//		}
//	}

}
