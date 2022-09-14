package com.ldt.Employee.exceloperations;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class DatabaseToExcel {
    public static void main(String[] args) throws SQLException, IOException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp", "root", "Root@123");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from employee");

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet xssfSheet = xssfWorkbook.createSheet("Employee Data");

        XSSFRow xssfRow = xssfSheet.createRow(0);
        xssfRow.createCell(0).setCellValue("id");
        xssfRow.createCell(1).setCellValue("emp_name");
        xssfRow.createCell(2).setCellValue("project_name");
        xssfRow.createCell(3).setCellValue("salary");

        int r=1;
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("emp_name");
            String project = resultSet.getString("project_name");
            int salary = resultSet.getInt("salary");

            XSSFRow row = xssfSheet.createRow(r++);
            row.createCell(0).setCellValue(id);
            row.createCell(1).setCellValue(name);
            row.createCell(2).setCellValue(project);
            row.createCell(3).setCellValue(salary);
        }

        FileOutputStream fileOutputStream = new FileOutputStream(".//datafiles//employeesDetail.xlsx");
        xssfWorkbook.write(fileOutputStream);

        xssfWorkbook.close();
        fileOutputStream.close();
        connection.close();

        System.out.println("Done Exporting");


    }
}
