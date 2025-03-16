package com.deepak.ems.service;

import com.deepak.ems.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Optional<Employee>getEmployeeById(Long id);
    Employee updateEmployee(Long id,Employee employee);
    void deleteEmployee(Long id);
}
