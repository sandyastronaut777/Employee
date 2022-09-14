package com.ldt.Employee.exceloperations;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WritingExcelDemo1 {
    public static void main(String[] args) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Emp Info");
        Object empdata[][] = {{"EmpID", "Name", "Job"},
                {101, "Sandhya", "Developer"},
                {105, "Urvashi", "Engineer"},
                {120, "Juhi", "Analyst"}
        };

        int rows = empdata.length;
        int cols = empdata[0].length;

        System.out.println(rows);
        System.out.println(cols);

        for(int r=0; r<rows; r++){
            XSSFRow row = sheet.createRow(r);
            for (int c=0; c<cols; c++){
                XSSFCell cell = row.createCell(c);
                Object value = empdata[r][c];

                if(value instanceof String)
                    cell.setCellValue((String) value);
                if(value instanceof Integer)
                    cell.setCellValue((Integer) value);
                if (value instanceof Boolean)
                    cell.setCellValue((Boolean) value);
            }
        }

        String path = ".//datafiles//employee.xlsx";
        FileOutputStream outStream = new FileOutputStream(path);
        workbook.write(outStream);

        outStream.close();

        System.out.println("File written successfully");

    }
}
