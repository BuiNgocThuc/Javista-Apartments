package com.example.javista.validator;

import com.example.javista.dto.request.statistics.RevenueStatisticsRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.DateTimeException;
import java.time.YearMonth;

public class StatisticsDateValidator implements ConstraintValidator<StatisticsDateConstraint, RevenueStatisticsRequest> {
    @Override
    public void initialize(StatisticsDateConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(RevenueStatisticsRequest request, ConstraintValidatorContext constraintValidatorContext) {
        if (request.getStartMonth() == null || request.getEndMonth() == null) return false;

        String startMonth = request.getStartMonth();
        String endMonth = request.getEndMonth();

        try {
            YearMonth start = YearMonth.parse(startMonth);
            YearMonth end = YearMonth.parse(endMonth);

            return start.isBefore(end);
        } catch (DateTimeException e) {
            return false;
        }
    }
}
