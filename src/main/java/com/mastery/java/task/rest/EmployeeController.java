package com.mastery.java.task.rest;

import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("get All Employees")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved")})
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(defaultValue = "") String name,
                                                          @RequestParam(defaultValue = "") String surname) {
        final List<Employee> employees = employeeService.findAllEmployees(name, surname);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("get Employee by Id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved")
            , @ApiResponse(code = 404, message = "Employee not found")})
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) throws EmployeeNotFoundException {
        final Employee employee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("create Employee")
    @ApiResponses(value= {@ApiResponse(code = 200, message = "Successfully retrieved")
            , @ApiResponse(code = 400, message = "Employee data is not valid")})
    @Valid
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("update Employee")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved")
            , @ApiResponse(code = 400, message = "Employee data is not valid")
            , @ApiResponse(code = 404, message = "Employee not found")})
    @Valid
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,
                                                   @RequestBody Employee employee) throws EmployeeNotFoundException {
        if (Objects.equals(id, employee.getEmployeeId())) {
            employeeService.updateEmployeeById(id, employee);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("delete Employee")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved")})
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

}
