package com.mastery.java.task.dao;

import com.mastery.java.task.entity.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> findAllEmployees();

    Employee findEmployeeById(Long id);

    Employee addEmployee(Employee employee);

    Employee updateEmployeeById(Long id, Employee employee);

    void deleteEmployee(Long id);
}
