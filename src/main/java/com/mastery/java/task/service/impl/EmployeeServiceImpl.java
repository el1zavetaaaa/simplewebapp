package com.mastery.java.task.service.impl;

import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.exception.ValidationException;
import com.mastery.java.task.repository.EmployeeRepository;
import com.mastery.java.task.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger warn = LoggerFactory.getLogger("warn");

    private final EmployeeRepository employeeDao;

    public EmployeeServiceImpl(EmployeeRepository employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> findAllEmployees(String name, String surname) {

        return employeeDao.findByFirstNameIsContainingAndLastNameIsContaining(name, surname);
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return employeeDao.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Check whether employee with entering id " + id + " exists!"));
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    public Employee updateEmployeeById(Long id, Employee employee) {
        Employee updatedEmployee = employeeDao.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Check whether employee with entering id " + id + " exists!"));
        if (Objects.equals(id, employee.getEmployeeId())) {
            updatedEmployee.setFirstName(employee.getFirstName());
            updatedEmployee.setLastName(employee.getLastName());
            updatedEmployee.setDepartmentId(employee.getDepartmentId());
            updatedEmployee.setJobTitle(employee.getJobTitle());
            updatedEmployee.setGender(employee.getGender());
            updatedEmployee.setBirthdayDate(employee.getBirthdayDate());
            return employeeDao.save(updatedEmployee);
        } else {
            warn.warn("Employee wasn't updated as the customer set the wrong id " + employee.getEmployeeId() +
                    ", however wanted to update the employee with id " + id + "!");
            throw new ValidationException("Employee wasn't updated as the customer set the wrong id " + employee.getEmployeeId() +
                    ", however wanted to update the employee with id " + id + "!");
        }
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeDao.deleteById(id);
    }
}
