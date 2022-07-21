package com.mastery.java.task.rest;

import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.exception.EmployeeServiceNotFoundException;
import com.mastery.java.task.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/employees")
@Validated
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    @ApiOperation("get All Employees")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved")})
    public List<Employee> getAllEmployees(@RequestParam(defaultValue = "") String name,
                                                          @RequestParam(defaultValue = "") String surname) {
        log.info("Enter getAllEmployees: name - {}, surname - {}", name,surname);
        return employeeService.findAllEmployees(name, surname);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("get Employee by Id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved")
            , @ApiResponse(code = 404, message = "Employee not found")})
    public Employee getEmployeeById(@PathVariable @Positive Long id) throws EmployeeServiceNotFoundException {
        log.info("Enter getEmployeeById: id - {}", id);
        return employeeService.findEmployeeById(id);
    }

    @PostMapping()
    @ApiOperation("create Employee")
    @ApiResponses(value= {@ApiResponse(code = 201, message = "Created")
            , @ApiResponse(code = 400, message = "Employee data is not valid")})
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        log.info("Enter createEmployee: employee - {}", employee);
        return employeeService.addEmployee(employee);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("update Employee")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved")
            , @ApiResponse(code = 400, message = "Employee data is not valid")
            , @ApiResponse(code = 404, message = "Employee not found")})
    public ResponseEntity<Employee> updateEmployee(@PathVariable @Positive Long id,
                                         @RequestBody Employee employee) throws EmployeeServiceNotFoundException {
            log.info("Enter updateEmployee: id - {}, employee - {}", id, employee);
        if (Objects.equals(id, employee.getEmployeeId())) {
            employeeService.updateEmployeeById(id, employee);
            return new ResponseEntity<>(employee,HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("delete Employee")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved")})
    public void deleteEmployee(@PathVariable @Positive Long id) {
        log.info("Enter deleteEmployee: id - {}", id);
        employeeService.deleteEmployee(id);
    }

}
