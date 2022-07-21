package com.mastery.java.task.service;

import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.exception.EmployeeServiceNotFoundException;


import java.util.List;

public interface EmployeeService {
    List<Employee> findAllEmployees(String name, String surname);

    Employee findEmployeeById(Long id) throws EmployeeServiceNotFoundException;

    Employee addEmployee(Employee employee);

    Employee updateEmployeeById(Long id, Employee employee);

    void deleteEmployee(Long id);

    Employee messagesForAddingEmployee(Employee employee);
}
