package com.ldt.Employee.service.impl;

import com.ldt.Employee.dto.RequestDTO;
import com.ldt.Employee.dto.ResponseDTO;
import com.ldt.Employee.entity.Employee;
import com.ldt.Employee.repository.EmployeeRepository;
import com.ldt.Employee.service.EmployeeService;
import com.ldt.Employee.utility.APIResponse;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public APIResponse create(RequestDTO requestDTO) {
        Employee employee = new Employee();
        employee = putDataInEntity(employee, requestDTO);
        employeeRepository.save(employee);
        return new APIResponse("Success", "Successfully Created", 200, null);
    }

    private Employee putDataInEntity(Employee employee, RequestDTO requestDTO) {
        employee.setEmpName(requestDTO.getEmpName());
        employee.setProjectName(requestDTO.getProjectName());
        employee.setSalary(requestDTO.getSalary());
        return employee;
    }

    @Override
    public APIResponse get(int id) {
        Employee employee = employeeRepository.findById(id);
        return new APIResponse("Success", "Successfully Fetched", 200, getDataFromEntity(employee));
    }

    @Override
    public APIResponse update(RequestDTO requestDTO, int id) {
        Employee employee = employeeRepository.findById(id);
        employee.setEmpName(requestDTO.getEmpName());
        employee.setProjectName(requestDTO.getProjectName());
        employee.setSalary(requestDTO.getSalary());
        employeeRepository.save(employee);
        return new APIResponse("Success", "Successfully Updated", 200, null);
    }

    private ResponseDTO getDataFromEntity(Employee employee) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setEmpName(employee.getEmpName());
        responseDTO.setProjectName(employee.getProjectName());
        responseDTO.setSalary(employee.getSalary());
        return responseDTO;
    }

    @Override
    public APIResponse delete(int id) {

        Employee employee = employeeRepository.findById(id);

        employeeRepository.delete(employee);
        return new APIResponse("Success", "Successfully Deleted", 200, null);
    }


    @Override
    public APIResponse export() throws IOException {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet xssfSheet = xssfWorkbook.createSheet("Employee Data");

        XSSFRow xssfRow = xssfSheet.createRow(0);
        xssfRow.createCell(0).setCellValue("id");
        xssfRow.createCell(1).setCellValue("emp_name");
        xssfRow.createCell(2).setCellValue("project_name");
        xssfRow.createCell(3).setCellValue("salary");

        List<Employee> all = employeeRepository.findAll();
        System.out.println(all.size());

        AtomicInteger count = new AtomicInteger(1);
        all.forEach(i ->
                {
                    XSSFRow row = xssfSheet.createRow(count.getAndIncrement());
                    row.createCell(0).setCellValue(i.getId());
                    row.createCell(1).setCellValue(i.getEmpName());
                    row.createCell(2).setCellValue(i.getProjectName());
                    row.createCell(3).setCellValue(i.getSalary());

                }
        );

        FileOutputStream fileOutputStream = new FileOutputStream(".//exporteddata//employeeDATA.xlsx");
        xssfWorkbook.write(fileOutputStream);

        System.out.println("Done Exporting");
        return new APIResponse("Success", "Successfully Exported", 200, null);
    }


    @Override
    public APIResponse download(HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException {

        final String EXTERNAL_FILE_PATH = "/Users/ldttechnology/Downloads/Employee/exporteddata/";
        File file = new File(EXTERNAL_FILE_PATH + fileName);
        if (file.exists()) {
            System.out.println("File exsist.");
            //get the mimetype
            String name = file.getName();
            System.out.println(name);

            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) {
                //unknown mimetype so set the mimetype to application/octet-stream
                mimeType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            }

            response.setContentType(mimeType);

            response.setHeader("Content-Disposition", String.format("inline; filename=/" + file.getName() + "/"));

            response.setContentLength((int) file.length());

            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            FileCopyUtils.copy(inputStream, response.getOutputStream());

        }
        return new APIResponse("Success","Successfully Downloaded", 200, null);

    }
}

