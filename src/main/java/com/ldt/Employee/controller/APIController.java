package com.ldt.Employee.controller;

import com.ldt.Employee.dto.RequestDTO;
import com.ldt.Employee.entity.Employee;
import com.ldt.Employee.models.AuthenticationRequest;
import com.ldt.Employee.models.AuthenticationResponse;
import com.ldt.Employee.service.EmployeeService;
import com.ldt.Employee.service.impl.MyUserDetailsService;
import com.ldt.Employee.utility.APIResponse;
import com.ldt.Employee.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RequestMapping("/employee")
@RestController
public class APIController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService userDetailsService;
    @Autowired
    EmployeeService employeeService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @PostMapping("/create")
    APIResponse create(@RequestBody RequestDTO requestDTO) {
        return employeeService.create(requestDTO);
    }

    @PostMapping("/update/{id}")
    APIResponse update(@RequestBody RequestDTO requestDTO, int id) {
        return employeeService.update(requestDTO, id);
    }

    @GetMapping("/get/{id}")
    APIResponse get(@PathVariable int id) {
        return employeeService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    APIResponse delete(@PathVariable int id) {
        return employeeService.delete(id);
    }

    @PostMapping("/export")
    APIResponse export() throws IOException {
        return employeeService.export();
    }

    @GetMapping("/downloadTemplate")
    ResponseEntity<ByteArrayResource> downloadTemplate() throws Exception {
        return employeeService.downloadTemplate();
    }

    @GetMapping("/filterbydate")
    List<Employee> getAllBetweenDates(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        return employeeService.getAllBetweenDates(LocalDate.parse(startDate), (LocalDate.parse(endDate)));
    }


}



