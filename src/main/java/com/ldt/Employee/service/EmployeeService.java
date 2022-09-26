package com.ldt.Employee.service;


import com.ldt.Employee.dto.RequestDTO;
import com.ldt.Employee.entity.Employee;
import com.ldt.Employee.utility.APIResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface EmployeeService {

    APIResponse create(RequestDTO requestDTO);

    APIResponse get(int id);

    APIResponse update(RequestDTO requestDTO, int id);

    APIResponse delete(int id);

    APIResponse export() throws IOException;

    ResponseEntity<ByteArrayResource> downloadTemplate();

    List<Employee> getAllBetweenDates(LocalDate startDate, LocalDate endDate);


}
