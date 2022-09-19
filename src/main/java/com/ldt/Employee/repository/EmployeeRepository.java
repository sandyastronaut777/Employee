package com.ldt.Employee.repository;

import com.ldt.Employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findById(int id);

    List<Employee> findByDateBetween(LocalDate to, LocalDate from);


}
