package com.example.javista.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class DobValidator implements ConstraintValidator<DobConstraint, LocalDateTime> {
        int min;

        @Override
        public void initialize(DobConstraint constraintAnnotation) {
                ConstraintValidator.super.initialize(constraintAnnotation);
                min = constraintAnnotation.min();
        }

        @Override
        public boolean isValid(LocalDateTime localDateTime, ConstraintValidatorContext constraintValidatorContext) {
                if ((Objects.isNull(localDateTime)))
                        return true;

                long years = ChronoUnit.YEARS.between(localDateTime, LocalDateTime.now());

                return years >= min;
        }
}
