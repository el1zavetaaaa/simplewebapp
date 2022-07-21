package com.mastery.java.task.exception;

import lombok.Getter;

@Getter
public class EmployeeServiceNotFoundException extends RuntimeException {
    public EmployeeServiceNotFoundException(String message) {
        super(message);
    }
}
