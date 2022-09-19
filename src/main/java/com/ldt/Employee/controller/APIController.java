package com.ldt.Employee.controller;

import com.ldt.Employee.dto.RequestDTO;
import com.ldt.Employee.entity.Employee;
import com.ldt.Employee.service.EmployeeService;
import com.ldt.Employee.utility.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
public class APIController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    APIResponse create(@RequestBody RequestDTO requestDTO) {
        return employeeService.create(requestDTO);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    APIResponse update(@RequestBody RequestDTO requestDTO, int id) {
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

    @RequestMapping(value = "/export", method = RequestMethod.POST)
    APIResponse export() throws IOException {
        return employeeService.export();
    }

    @RequestMapping(value = "/downloadTemplate", method = RequestMethod.GET)
    ResponseEntity<ByteArrayResource> downloadTemplate() throws Exception {
        return employeeService.downloadTemplate();
    }

    @RequestMapping(value = "/filterbydate", method = RequestMethod.GET)
    List<Employee> getAllBetweenDates(@RequestParam("startDate")String startDate, @RequestParam("endDate")String endDate){
        return employeeService.getAllBetweenDates(LocalDate.parse(startDate), (LocalDate.parse(endDate)));
    }

}

