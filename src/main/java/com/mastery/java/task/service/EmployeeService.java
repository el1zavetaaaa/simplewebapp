package com.mastery.java.task.service;

import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.exception.ValidationException;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAllEmployees(String name, String surname);

    Employee findEmployeeById(Long id) throws EmployeeNotFoundException;

    Employee addEmployee(Employee employee);

    Employee updateEmployeeById(Long id, Employee employee) throws EmployeeNotFoundException, ValidationException;

    void deleteEmployee(Long id);
}
