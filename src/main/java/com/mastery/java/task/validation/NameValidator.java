package com.mastery.java.task.validation;

import com.mastery.java.task.validation.impl.ConstraintValidationForName;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target( ElementType.FIELD )
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConstraintValidationForName.class)
public @interface NameValidator {
    String message() default "Employee name and surname should only consist of letters!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
