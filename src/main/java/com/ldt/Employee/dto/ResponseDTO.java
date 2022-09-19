package com.ldt.Employee.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class ResponseDTO {

    private int id;

    private String empName;

    private String projectName;

    private int salary;

    @CreationTimestamp
    private LocalDate date;
}
