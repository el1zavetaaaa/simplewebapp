package com.mastery.java.task.validation.impl;

import com.mastery.java.task.exception.ValidationException;
import com.mastery.java.task.validation.NameValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ConstraintValidationForName implements ConstraintValidator<NameValidator, String> {
    private static final Logger warn = LoggerFactory.getLogger("warn");

    @Override
    public void initialize(NameValidator constraintAnnotation) {

    }

    @Override
    public boolean isValid(String nameAndSurname, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern = Pattern.compile("^[a-zA-Z\\s]*$");
        Matcher matcher = pattern.matcher(nameAndSurname);
        if(matcher.matches()) {
            return true;
        } else {
            warn.warn("Employee wasn't saved as the user entered wrong full name data: {}", nameAndSurname);
            throw new ValidationException("Employee name and surname should only consist of letters!");
        }
    }
}
