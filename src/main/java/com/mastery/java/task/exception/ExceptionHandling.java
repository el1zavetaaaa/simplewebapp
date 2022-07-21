package com.mastery.java.task.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;


@Slf4j
@RestControllerAdvice
public class    ExceptionHandling {
    String errorMsg;

    @ExceptionHandler(EmployeeServiceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEmployeeNotFoundException(EmployeeServiceNotFoundException employeeException) {
        log.error("Employee can not be found! ", employeeException);
        return employeeException.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationException(MethodArgumentNotValidException notValidException){
        log.error("Employee wasn't saved to db! ", notValidException);
        return notValidException.getMessage();

    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConstraintViolationException(ConstraintViolationException constraintViolationException){
        log.error("Employee wasn't saved to db! ", constraintViolationException);
        return constraintViolationException.getMessage();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationException(HttpMessageNotReadableException
                                                                    notReadableException){
        log.error("Employee wasn't saved to db! ", notReadableException);
        return notReadableException.getMessage();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public String handleMethodNotSupportedException(HttpRequestMethodNotSupportedException
                                                                               methodNotSupportedException){
        log.error("Employee wasn't saved to db! ", methodNotSupportedException);
        return methodNotSupportedException.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleInternalServerError(Exception exception){
        errorMsg = "There is a Server error!";
        log.error("There is a server Error! ", exception);
        return errorMsg;
    }

}
