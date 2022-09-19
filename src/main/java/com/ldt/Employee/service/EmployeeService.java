package com.ldt.Employee.service;


import com.ldt.Employee.dto.RequestDTO;
import com.ldt.Employee.utility.APIResponse;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public interface EmployeeService {

    APIResponse create(RequestDTO requestDTO);

    APIResponse get(int id);

    APIResponse update(RequestDTO requestDTO, int id);

    APIResponse delete(int id);

//    void export(RequestDTO requestDTO) throws IOException;

    APIResponse export() throws IOException;


//    APIResponse download(HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException;

    ResponseEntity<ByteArrayResource> downloadTemplate();

}
