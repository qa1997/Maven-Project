package com.RB_Auction.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcellReader {

  public static String getData(String filename , String key) throws Exception {
		
		String filepath = ".//DataConfig//"+filename+".properties";
		FileInputStream instream = new FileInputStream(filepath);
		
		Properties pro = new Properties();
		pro.load(instream);
		return pro.getProperty(key);
	}
  
  public static String getData(String filename , String sheetname , int row , int cell) throws Exception {
	  String filepath = ".//TestData//"+filename+".xlsx";
		FileInputStream instream = new FileInputStream(filepath);
		XSSFWorkbook book = new XSSFWorkbook(instream);
		XSSFSheet sheet = book.getSheet(sheetname);
		return sheet.getRow(row).getCell(cell).getStringCellValue();
  }

  public static List<String> getData(String filename , int row,String sheetname) throws Exception {
	String filepath = ".//TestData//"+filename+".xlsx";
	FileInputStream instream = new FileInputStream(filepath);
	XSSFWorkbook book = new XSSFWorkbook(instream);
	XSSFSheet sheet = book.getSheet(sheetname);
	int rows = sheet.getRow(row).getLastCellNum();
	List<String> infolist = new ArrayList<String>();
	for(int i=0;i<rows;i++) {
		String data = sheet.getRow(row).getCell(i).getStringCellValue();
		infolist.add(data);
	}
	return infolist;
	
}
  
  public static List<Double> getReadData(String filename , int row,String sheetname) throws Exception {
		String filepath = ".//TestData//"+filename+".xlsx";
		FileInputStream instream = new FileInputStream(filepath);
		XSSFWorkbook book = new XSSFWorkbook(instream);
		XSSFSheet sheet = book.getSheet(sheetname);
		int rows = sheet.getRow(row).getLastCellNum();
		String data ="";
		double data1 = 0;
		boolean data2 = true;
		List<String> infolist = new ArrayList<String>();
		List<Double> infolist1 = new ArrayList<>();
		List<Boolean> infolist2 = new ArrayList<>();
		for(int i=0;i<rows;i++) {
			XSSFRow rowcount = sheet.getRow(rows);
			XSSFCell cellcount = rowcount.getCell(i);
			if(cellcount.getCellType() != null) {
			data = cellcount.getStringCellValue();
			}
			else if(cellcount.getCellType()!=null) {
			 data1 = cellcount.getNumericCellValue();
			}
			else {
				data2 =cellcount.getBooleanCellValue();
			}
			
			infolist.add(data);
			infolist1.add(data1);
			infolist2.add(data2);
		}
		return infolist1;
		
	}
  
  
	public static void main(String[] args) throws Exception {
		String data = ExcellReader.getData("config", "recurtorusername");
		System.out.println(data);
		
//		List<String> rowdata = ExcellReader.getData("info", 1, "Sheet1");
//		System.out.println(rowdata);
//		
//		String celldata = ExcellReader.getData("info", "Sheet1", 1, 1);
//		System.out.println(celldata);
		
		List<Double> rowdata1 = ExcellReader.getReadData("info", 2, "Sheet1");
		System.out.println(rowdata1);
		
		
	}
}
