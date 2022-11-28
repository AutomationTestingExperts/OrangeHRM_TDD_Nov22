package com.orangeHRM.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	public static String readSingleValueExcel(int rowNum, int colNum) {
		String str = "";
		try {
			File file = new File(System.getProperty("user.dir") + "\\TestData.xlsx");
			FileInputStream fi = new FileInputStream(file);
			Workbook book = new XSSFWorkbook(fi);// .xlsx
			// book = new HSSFWorkbook();//.xls
			Sheet sheet = book.getSheet("Sheet1");
			Row row = sheet.getRow(rowNum);
			Cell cell = row.getCell(colNum);
			str = cell.getStringCellValue();
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static List<String> readSigleColumnExcel(int colNum) {
		List<String> list = new ArrayList<>();
		try {
			File file = new File(System.getProperty("user.dir") + "\\TestData.xlsx");
			FileInputStream fi = new FileInputStream(file);
			Workbook book = new XSSFWorkbook(fi);// .xlsx
			// book = new HSSFWorkbook();//.xls
			Sheet sheet = book.getSheet("Sheet1");
			int lastRow = sheet.getLastRowNum();

			for (int i = 0; i < lastRow; i++) {
				Row row = sheet.getRow(i);
				Cell cell = row.getCell(colNum);
				String str = cell.getStringCellValue();
				list.add(str);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
