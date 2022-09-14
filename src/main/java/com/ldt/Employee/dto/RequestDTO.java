package com.ldt.Employee.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDTO {

    private int id;

    private String empName;

    private String projectName;

    private int salary;
}
