package com.mastery.java.task.dao.impl;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
        return jdbcTemplate.query("SELECT*FROM employees", new BeanPropertyRowMapper<>(Employee.class));
    }

    public Employee findEmployeeById(Long id) {
        return jdbcTemplate.query("SELECT*FROM employees WHERE employee_id=?",
                        new BeanPropertyRowMapper<>(Employee.class), new Object[]{id})
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
                employee.getGender().getName(),
                employee.getBirthdayDate());
        return employee;
    }

    public Employee updateEmployeeById(Long id, Employee employee) {
        jdbcTemplate.update("UPDATE employees SET " +
                        "first_name=?, " +
                        "last_name=?, " +
                        "department_id=?, " +
                        "job_title=?, " +
                        "gender=?, " +
                        "birthday_date=? " +
                        "WHERE employee_id=?",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTitle(),
                employee.getGender().getName(),
                employee.getBirthdayDate(),
                id);
        return employee;
    }

    public void deleteEmployee(Long id) {
        jdbcTemplate.update("DELETE FROM employees WHERE employee_id=?",
                id);
    }
}
