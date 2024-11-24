package com.example.javista.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.javista.dto.response.PageResponse;
import com.example.javista.filter.FilterCriteria;
import com.example.javista.filter.FilteringAndSearchingOperator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QueryUtils {

    public static <T> List<FilterCriteria> getFilterCriterion(T queryDto) {
        List<FilterCriteria> filterSpecifications = new ArrayList<>();
        for (Field field : queryDto.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                String fieldVal = (String) field.get(queryDto);
                //                                System.out.println(fieldVal);
                if (fieldVal != null) {
                    String operatorParam = StringManipulation.definingOperatorInParameters(fieldVal, ':');
                    String value = StringManipulation.definingValueInParameters(fieldVal, ':');
                    FilteringAndSearchingOperator operator =
                            FilteringAndSearchingOperator.convertParametersToOperators(operatorParam);
                    log.info("Field: {}, Value: {}, Operator: {}", field.getName(), value, operator);

                    filterSpecifications.add(new FilterCriteria(field.getName().replace('_','.'), value, operator));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Unable to access field: " + field.getName(), e);
            }
        }
        return filterSpecifications;
    }

    public static <T> Pageable getPagination(T queryDto) {
        try {
            // Get the `getCurrentPage` method and invoke it
            Method getCurrentPageMethod = queryDto.getClass().getMethod("getCurrentPage");
            int page = (int) getCurrentPageMethod.invoke(queryDto);

            // Get the `getSize` method and invoke it
            Method getSizeMethod = queryDto.getClass().getMethod("getSize");
            int size = (int) getSizeMethod.invoke(queryDto);

            // Get the `getSort` method and invoke it
            Method getSortMethod = queryDto.getClass().getMethod("getSort");
            String sortField = (String) getSortMethod.invoke(queryDto);

            // Create the Sort object and Pageable instance
            Sort sort = getSortCriterion(sortField);
            return PageRequest.of(page - 1, size, sort);
        } catch (Exception e) {
            // Handle possible exceptions (e.g., NoSuchMethodException, IllegalAccessException,
            // InvocationTargetException)
            throw new RuntimeException("Error retrieving pagination data", e);
        }
    }

    // get sort criterion
    public static Sort getSortCriterion(String sortParams) {
        String[] sortParamsArray = sortParams.split(",");
        List<Sort.Order> orders = new ArrayList<>();
        Boolean isDesc = false;
        for (String sortParam : sortParamsArray) {

            sortParam.trim();
            // Determine the sort direction
            if (sortParam.startsWith("-")) {
                isDesc = true;
                sortParam = sortParam.substring(1);
            }

            // Add the sort order to the list
            orders.add(new Sort.Order(isDesc ? Sort.Direction.DESC : Sort.Direction.ASC, sortParam));
        }
        log.info("Sort orders: {}", orders);
        return Sort.by(orders);
    }

    public static <T, R> PageResponse<R> buildPageResponse(Page<T> pageData, Pageable pageable, Function<T, R> mapper) {
        return PageResponse.<R>builder()
                .currentPage(pageable.getPageNumber() + 1) // Convert zero-based index to one-based
                .pageSize(pageData.getSize())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .data(pageData.getContent().stream()
                        .map(mapper) // Apply the mapping function
                        .toList())
                .build();
    }
}
