package com.example.javista.utils;

import com.example.javista.filter.FilterCriteria;
import com.example.javista.filter.FilteringAndSearchingOperator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FilteringUtils {

        public static <T> List<FilterCriteria> setUpFilterCriterion(T queryDto) {
                List<FilterCriteria> filterSpecifications = new ArrayList<>();
                for (Field field : queryDto.getClass().getDeclaredFields()) {
                        field.setAccessible(true);
                        try {
                                String fieldVal = (String)  field.get(queryDto);
                                if(fieldVal != null)  {
                                        String operatorParam = StringManipulation.definingOperatorInParameters(fieldVal, ':');
                                        String value = StringManipulation.definingValueInParameters(fieldVal, ':');
                                        FilteringAndSearchingOperator operator = FilteringAndSearchingOperator.convertParametersToOperators(operatorParam);

                                        filterSpecifications.add(new FilterCriteria(field.getName(), value, operator));
                                }
                        } catch (IllegalAccessException e) {
                                throw new RuntimeException("Unable to access field: " + field.getName(), e);
                        }
                }
                return filterSpecifications;
        }
}
