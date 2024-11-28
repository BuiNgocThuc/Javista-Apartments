package com.example.javista.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {StatisticsDateValidator.class})
public @interface StatisticsDateConstraint {
    String message() default "Invalid date range: startMonth must be before endMonth.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
