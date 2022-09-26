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
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
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
        employee.setDate(requestDTO.getDate());
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
        employee.setDate(requestDTO.getDate());
        employeeRepository.save(employee);
        return new APIResponse("Success", "Successfully Updated", 200, null);
    }


    private ResponseDTO getDataFromEntity(Employee employee) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setEmpName(employee.getEmpName());
        responseDTO.setProjectName(employee.getProjectName());
        responseDTO.setSalary(employee.getSalary());
        responseDTO.setDate(employee.getDate());
        return responseDTO;
    }
//
//    @Override
//    public void getData() {
//        System.out.println("Method Started");
//        String empName = requestDTO.getEmpName();
//        if (empName == null || empName == "" || Objects.isNull(empName)) {
//
//            empName = "0";
//        }
//        String projectName = requestDTO.getProjectName();
//        if (projectName == null || projectName == "" || Objects.isNull(projectName)) {
//
//            projectName = "0";
//        }
//        int salary = employeeRepository.getSalary();
//        if (Objects.isNull(salary)) {
//
//            salary = 0;
//        }
//        List<Employee> employee = employeeRepository.getData(empName, projectName, salary);
////        List<ResponseDTO> responseDTO = new ArrayList<>();
////        if(!employee.isEmpty()) {
////            responseDTO = employee.stream().map(this::getDataFromEntity).collect(Collectors.toList());
//
//        }


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
        xssfRow.createCell(4).setCellValue("date");

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
                    row.createCell(4).setCellValue(i.getDate());

                }
        );

        FileOutputStream fileOutputStream = new FileOutputStream("null");
        xssfWorkbook.write(fileOutputStream);

        System.out.println("Done Exporting");
        return new APIResponse("Success", "Successfully Exported", 200, null);
    }

    @Override
    public ResponseEntity<ByteArrayResource> downloadTemplate() {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            XSSFWorkbook workbook = createWorkBook(); // creates the workbook
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "force-download"));
            header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ProductTemplate.xlsx");
            workbook.write(stream);
            workbook.close();
            return new ResponseEntity<>(new ByteArrayResource(stream.toByteArray()),
                    header, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private XSSFWorkbook createWorkBook() throws IOException {
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

        FileOutputStream fileOutputStream = new FileOutputStream(".//exporteddata//empNewDATA.xlsx");
        xssfWorkbook.write(fileOutputStream);

        System.out.println("Done Exporting");
        return xssfWorkbook;

    }

    @Override
    public List<Employee> getAllBetweenDates(LocalDate startDate, LocalDate endDate) {

        List<Employee> employee = employeeRepository.findByDateBetween(startDate, endDate);
        return employee;
    }
}

