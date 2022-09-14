package com.ldt.Employee.exceloperations;

import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadingExcel {
    public static void main(String[] args) throws IOException {
        String path = ".//datafiles//Data Refresh Sample Data.xlsx";
        FileInputStream fileInputStream = new FileInputStream(path);
        System.out.println("finle"+fileInputStream);

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
        System.out.println(xssfWorkbook);
        XSSFSheet sheet = xssfWorkbook.getSheet("Sheet1");
        System.out.println(sheet);

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(2).getLastCellNum();

        for (int r = 0; r <= rows; r++) {
            XSSFRow row = sheet.getRow(r);
            for (int c = 0; c < cols; c++) {
                XSSFCell cell = row.getCell(c);

                switch (cell.getCellType()) {
                    case STRING:
                        System.out.println(cell.getStringCellValue());
                        break;

                    case NUMERIC:
                        System.out.println(cell.getNumericCellValue());
                        break;

                    case BOOLEAN:
                        System.out.println(cell.getBooleanCellValue());
                        break;
                }
            }
            System.out.println();
        }
    }
}
