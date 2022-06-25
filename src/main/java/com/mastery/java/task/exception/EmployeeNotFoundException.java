package com.mastery.java.task.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class EmployeeNotFoundException extends RuntimeException {
    private final String message;
}
