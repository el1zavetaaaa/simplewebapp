package com.mastery.java.task.dto.mapper;

import com.mastery.java.task.dto.model.Employee;
import com.mastery.java.task.dto.model.Gender;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class EmployeeMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();

        employee.setEmployeeId(rs.getLong("employeeId"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setDepartmentId(rs.getInt("department_id"));
        employee.setJobTitle(rs.getString("job_title"));
        employee.setGenderId(Gender.getGenderById(rs.getInt("gender_id")));
        employee.setBirthdayDate(rs.getDate("birthday_date"));

        return employee;
    }

}