package com.mastery.java.task.service.impl;

import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.repository.EmployeeRepository;
import com.mastery.java.task.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger warn = LoggerFactory.getLogger("warn");

    private final EmployeeRepository employeeDao;

    public EmployeeServiceImpl(EmployeeRepository employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> findAllEmployees(String name, String surname) {

        return employeeDao.findByFirstNameContainingAndLastNameContaining(name, surname);
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return employeeDao.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("The chosen employee does not exist!"));
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    @Transactional
    public Employee updateEmployeeById(Long id, Employee employee) {
        Employee updatedEmployee = employeeDao.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("The chosen employee does not exist!"));
        updatedEmployee.setFirstName(employee.getFirstName());
        updatedEmployee.setLastName(employee.getLastName());
        updatedEmployee.setDepartmentId(employee.getDepartmentId());
        updatedEmployee.setJobTitle(employee.getJobTitle());
        updatedEmployee.setGender(employee.getGender());
        updatedEmployee.setBirthdayDate(employee.getBirthdayDate());
        return employeeDao.save(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeDao.deleteById(id);
    }
}
