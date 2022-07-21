package com.mastery.java.task.rest;

import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/messages")
public class MessageController {

    private final EmployeeService employeeService;

    public MessageController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping()
    public Employee publish(@RequestBody Employee employee){
        employeeService.messagesForAddingEmployee(employee);
        return employee;
    }

}

