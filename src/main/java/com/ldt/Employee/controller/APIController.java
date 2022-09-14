package com.ldt.Employee.controller;

import com.ldt.Employee.dto.RequestDTO;
import com.ldt.Employee.service.EmployeeService;
import com.ldt.Employee.utility.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class APIController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    APIResponse create(@RequestBody RequestDTO requestDTO) {
        return employeeService.create(requestDTO);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    APIResponse update(@RequestBody RequestDTO requestDTO, int id){
        return employeeService.update(requestDTO, id);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    APIResponse get(@PathVariable int id) {
        return employeeService.get(id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    APIResponse delete(@PathVariable int id) {
        return employeeService.delete(id);
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    APIResponse exportToExcel(HttpServletResponse response){
        return null;
    }



}
