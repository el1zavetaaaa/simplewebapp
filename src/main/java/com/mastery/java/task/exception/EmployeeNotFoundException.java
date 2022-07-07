package com.mastery.java.task.exception;

import lombok.Getter;

@Getter
public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
