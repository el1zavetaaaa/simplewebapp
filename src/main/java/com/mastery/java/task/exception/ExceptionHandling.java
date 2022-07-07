package com.mastery.java.task.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;


import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;


@RestControllerAdvice
public class ExceptionHandling {
    private final Logger logger = LoggerFactory.getLogger(ExceptionHandling.class);

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFoundException(HttpServletRequest request,
                                                                  EmployeeNotFoundException employeeException) {
        logger.error("Employee can not be found! URL:{}", request.getRequestURL());
        return new ResponseEntity<>(employeeException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException notValidException){
        logger.error("Employee wasn't saved to db! {}", notValidException.getMessage());
        return new ResponseEntity<>(notValidException.getMessage(),HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleValidationException(HttpMessageNotReadableException
                                                                    notReadableException){
        logger.error("Employee wasn't saved to db! {}", notReadableException.getMessage());
        return new ResponseEntity<>(notReadableException.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NoHandlerFoundException
                                                                      noHandlerFoundException){
        return new ResponseEntity<>(noHandlerFoundException.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleMethodNotSupportedExcetion(HttpRequestMethodNotSupportedException
                                                                               methodNotSupportedException){
        logger.error("Employee wasn't saved to db! {}", methodNotSupportedException.getMessage());
        return new ResponseEntity<>(methodNotSupportedException.getMessage(),HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(ConversionNotSupportedException.class)
    public ResponseEntity<String> handleInternalServerError(ConversionNotSupportedException
                                                                        conversionNotSupportedException){
        logger.error("Employee wasn't saved to db! {}", conversionNotSupportedException.getMessage());
        return new ResponseEntity<>(conversionNotSupportedException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
