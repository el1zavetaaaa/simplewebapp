package com.mastery.java.task.rest;

import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.exception.ValidationException;
import com.mastery.java.task.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {
    private static final Logger info = LoggerFactory.getLogger("info");
    private static final Logger warn = LoggerFactory.getLogger("warn");

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam String name,
                                                          @RequestParam String surname) {
        final List<Employee> employees = employeeService.findAllEmployees(name, surname);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) throws EmployeeNotFoundException {
        final Employee employee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) throws ValidationException {
        employeeService.addEmployee(employee);
        info.info("Employee with id {} was saved!", employee.getEmployeeId());
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,
                                                   @RequestBody Employee employee) throws EmployeeNotFoundException
            , ValidationException {
        employeeService.updateEmployeeById(id, employee);
        info.info("Employee with id {} was updated!", id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        warn.warn("Employee with id {} was deleted!", id);
    }
}
