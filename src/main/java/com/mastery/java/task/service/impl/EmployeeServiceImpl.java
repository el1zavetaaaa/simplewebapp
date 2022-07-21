package com.mastery.java.task.service.impl;

import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.jms.MessageProducer;
import com.mastery.java.task.exception.EmployeeServiceNotFoundException;
import com.mastery.java.task.repository.EmployeeRepository;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeDao;
    private final MessageProducer employeeProducer;

    public EmployeeServiceImpl(EmployeeRepository employeeDao, MessageProducer employeeProducer) {
        this.employeeDao = employeeDao;
        this.employeeProducer = employeeProducer;
    }

    @Override
    public List<Employee> findAllEmployees(String name, String surname) {

        return employeeDao.findByFirstNameContainingAndLastNameContaining(name, surname);
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return employeeDao.findById(id)
                .orElseThrow(() ->
                        new EmployeeServiceNotFoundException(String.format("Employee with id %d does not exist!",id)));
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
                        new EmployeeServiceNotFoundException(String.format("Employee with id %d does not exist!",id)));
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

    @Override
    public Employee messagesForAddingEmployee(Employee employee) {
        employeeProducer.sendTo(employee);
        return employee;
    }
}
