package com.mastery.java.task.service;

import com.mastery.java.task.dto.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAllEmployees();

    Employee findEmployeeById(Long id);

    Employee addEmployee(Employee employee);

    Employee updateEmployeeById(Long id, Employee employee);

    void deleteEmployee(Long id);
}
