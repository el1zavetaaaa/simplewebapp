package com.mastery.java.task.exception.handling;

import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.time.LocalDateTime.now;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleEmployeeNotFoundException(EmployeeNotFoundException employeeException) {
        return ExceptionResponse.builder()
                .message(employeeException.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .timestamp(now())
                .build();
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleEmployeeBadRequestException(ValidationException validationException) {
        return ExceptionResponse.builder()
                .message(validationException.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(now())
                .build();
    }
}
