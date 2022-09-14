package com.ldt.Employee.service;


import com.ldt.Employee.dto.RequestDTO;
import com.ldt.Employee.utility.APIResponse;

public interface EmployeeService {

    APIResponse create(RequestDTO requestDTO);

    APIResponse get(int id);

    APIResponse update(RequestDTO requestDTO, int id);

    APIResponse delete(int id);

    APIResponse getData(RequestDTO requestDTO);


}
