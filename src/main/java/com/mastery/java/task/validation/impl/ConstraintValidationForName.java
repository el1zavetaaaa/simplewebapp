package com.mastery.java.task.validation.impl;

import com.mastery.java.task.validation.NameValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ConstraintValidationForName implements ConstraintValidator<NameValidator, String> {

    @Override
    public boolean isValid(String nameAndSurname, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern = Pattern.compile("^[a-zA-Z\\s]*$");
        Matcher matcher = pattern.matcher(nameAndSurname);
        return matcher.matches();
    }
}
