package com.mastery.java.task.dao.impl;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.mapper.EmployeeMapper;
import com.mastery.java.task.dto.model.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    private final JdbcTemplate jdbcTemplate;

    public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Employee> findAllEmployees() {
        return jdbcTemplate.query("SELECT*FROM employees", new EmployeeMapper());
    }

    public Employee findEmployeeById(Long id) {
        return jdbcTemplate.query("SELECT*FROM employees WHERE employeeId=?", new Object[]{id},
                        new EmployeeMapper())
                .stream()
                .findAny()
                .orElseThrow(() -> EmployeeNotFoundException.objectNotFound(id));
    }

    public Employee addEmployee(Employee employee) {
        jdbcTemplate.update("INSERT INTO employees VALUES(?,?,?,?,?,?,?)",
                employee.getEmployeeId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTitle(),
                employee.getGenderId().getId(),
                employee.getBirthdayDate());
        return employee;
    }

    public Employee updateEmployeeById(Long id, Employee employee) {
        jdbcTemplate.update("UPDATE employees SET " +
                        "first_name=?, " +
                        "last_name=?, " +
                        "department_id=?, " +
                        "job_title=?, " +
                        "gender_id=?, " +
                        "birthday_date=? " +
                        "WHERE employeeId=?",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTitle(),
                employee.getGenderId().getId(),
                employee.getBirthdayDate(),
                id);
        return employee;
    }

    public void deleteEmployee(Long id) {
        jdbcTemplate.update("DELETE FROM employees WHERE employeeId=?",
                id);
    }
}
