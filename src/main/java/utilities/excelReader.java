package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelReader 
{
	public String path;
	public FileInputStream fis=null;
	public FileOutputStream fileOut=null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet=null;
	private XSSFRow row = null;
	private XSSFCell cell=null;

	public excelReader(String path)
	{
		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet  = workbook.getSheetAt(0);
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean addSheet(String sheetName)
	{
		FileOutputStream fileOut;
		workbook.createSheet(sheetName);
		try {
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean addColumn(String sheetName , String colName)
	{
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			int index = workbook.getSheetIndex(sheetName);
				if(index==-1)
				return false;
				
				XSSFCellStyle style = workbook.createCellStyle();
				sheet  = workbook.getSheetAt(index);
				row  = sheet.getRow(0);
				
				if(row==null)
				row = sheet.createRow(0);
				
				if(row.getLastCellNum()==-1)
				cell = row.createCell(0);
				else
				cell = row.createCell(row.getLastCellNum());
				cell.setCellValue(colName);
				cell.setCellStyle(style);
				
				fileOut = new FileOutputStream(path);
				workbook.write(fileOut);
				fileOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean setCellData(String sheetName, String colName, int rowNum, String data) {
		try {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		if (rowNum <= 0)
		return false;
		int index = workbook.getSheetIndex(sheetName);
		int colNum = -1;
		if (index == -1)
		return false;
		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(0);
		for (int i = 0; i < row.getLastCellNum(); i++) {
		// System.out.println(row.getCell(i).getStringCellValue().trim());
		if (row.getCell(i).getStringCellValue().trim().equals(colName))
		colNum = i;}
		if (colNum == -1)
		return false;
		sheet.autoSizeColumn(colNum);
		row = sheet.getRow(rowNum - 1);
		if (row == null)
		row = sheet.createRow(rowNum - 1);
		cell = row.getCell(colNum);
		if (cell == null)
		cell = row.createCell(colNum);
		cell.setCellValue(data);
		fileOut = new FileOutputStream(path);
		workbook.write(fileOut);
		fileOut.close();
		} catch (Exception e) {
		e.printStackTrace();
		return false;
		}
		return true;
	}
}
