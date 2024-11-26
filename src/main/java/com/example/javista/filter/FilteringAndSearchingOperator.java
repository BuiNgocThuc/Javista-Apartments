package com.example.javista.filter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum FilteringAndSearchingOperator {
    // Filtering
    EQUAL_TO,
    NOT_EQUAL_TO,
    LESS_THAN,
    LESS_THAN_OR_EQUAL_TO,
    GREATER_THAN,
    GREATER_THAN_OR_EQUAL_TO,

    // Searching
    CONTAINS,

    // Join table
    JOIN_TABLE;

    public static FilteringAndSearchingOperator convertParametersToOperators(String input) {
        if (input == null) {
            return CONTAINS; // Or any other default operator
        }
        return switch (input) {
            case "eq" -> EQUAL_TO;
            case "neq" -> NOT_EQUAL_TO;
            case "gt" -> GREATER_THAN;
            case "lt" -> LESS_THAN;
            case "gte" -> GREATER_THAN_OR_EQUAL_TO;
            case "lte" -> LESS_THAN_OR_EQUAL_TO;
            default -> CONTAINS;
        };
    }
}
