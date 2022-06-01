package com.mastery.java.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException {
    private EmployeeNotFoundException() {
    }

    public static ResponseStatusException objectNotFound(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with id " + id + " was not found");
    }
}
