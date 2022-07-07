package com.mastery.java.task.validation.impl;

import com.mastery.java.task.validation.AgeValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class ConstraintValidationForAge implements ConstraintValidator<AgeValidator, LocalDate> {
    final int availableAge = 18;

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext context) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears() >= availableAge;
    }
}
