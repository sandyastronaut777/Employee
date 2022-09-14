package com.ldt.Employee.service.impl;

import com.ldt.Employee.dto.RequestDTO;
import com.ldt.Employee.dto.ResponseDTO;
import com.ldt.Employee.entity.Employee;
import com.ldt.Employee.repository.EmployeeRepository;
import com.ldt.Employee.service.EmployeeService;
import com.ldt.Employee.utility.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public APIResponse getData(RequestDTO requestDTO) {
        ArrayList<Employee> list = new ArrayList<Employee>();

        return new APIResponse();

    }
}
