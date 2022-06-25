package com.mastery.java.task.validation.impl;

import com.mastery.java.task.exception.ValidationException;
import com.mastery.java.task.validation.AgeValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class ConstraintValidationForAge implements ConstraintValidator<AgeValidator, LocalDate> {
    private static final Logger warn = LoggerFactory.getLogger("warn");

    @Override
    public void initialize(AgeValidator constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext context) {
        if (dateOfBirth.getYear() <= 2004){
            return true;
        } else {
            warn.warn("Employee wasn't saved as the user entered wrong date of birth {}", dateOfBirth);
            throw new ValidationException("Employee date of birth should be at least 2004 year!" +
                    "As we can hire only people since 18 years old!");
        }
    }
}
