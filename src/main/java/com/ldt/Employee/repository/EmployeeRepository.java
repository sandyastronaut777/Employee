package com.ldt.Employee.repository;

import com.ldt.Employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findById(int id);

    List<Employee> findByDateBetween(LocalDate to, LocalDate from);

//    @Query(value = "select * from employee where (Case when '0'=?1 then emp_name is not null else emp_name=?1 End)" +
//            "AND (Case when '0'=?2 then project_name is not null else project_name=?2 End)" +
//            "AND (Case when 0 =?3 then salary is not null else salary=?3 End)",
//            nativeQuery = true)
//    List<Employee> getData(String empName, String projectName, int salary);



}
