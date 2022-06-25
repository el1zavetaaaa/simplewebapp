package com.mastery.java.task.validation;



import com.mastery.java.task.validation.impl.ConstraintValidationForAge;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target( ElementType.FIELD )
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConstraintValidationForAge.class)
public @interface AgeValidator {
    String message() default "Employee must be at least 18 years old person!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
