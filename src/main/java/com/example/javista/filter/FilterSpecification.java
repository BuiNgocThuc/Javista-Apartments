package com.example.javista.filter;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FilterSpecification<T> {
        public Specification<T> filteringBySpecification(List<FilterCriteria> criterion) {
                return (root, query, criteriaBuilder) -> {

                        List<Predicate> predicates = new ArrayList<>();

                        for (FilterCriteria criteria : criterion) {

                                switch (criteria.getOperator()) {

                                        case EQUAL_TO:
                                                Predicate equal = criteriaBuilder
                                                                .equal(root.get(criteria.getColumn()),
                                                                                criteria.getValue());
                                                predicates.add(equal);
                                                break;

                                        case NOT_EQUAL_TO:
                                                Predicate notEqual = criteriaBuilder
                                                                .notEqual(root.get(criteria.getColumn()),
                                                                                criteria.getValue());
                                                predicates.add(notEqual);
                                                break;

                                        case LESS_THAN:
                                                Predicate lessThan = criteriaBuilder
                                                                .lessThan(root.get(criteria.getColumn()),
                                                                                criteria.getValue());
                                                predicates.add(lessThan);
                                                break;

                                        case LESS_THAN_OR_EQUAL_TO:
                                                Predicate lessThanOrEqualTo = criteriaBuilder
                                                                .lessThanOrEqualTo(root.get(criteria.getColumn()),
                                                                                criteria.getValue());
                                                predicates.add(lessThanOrEqualTo);
                                                break;

                                        case GREATER_THAN:
                                                Predicate greaterThan = criteriaBuilder
                                                                .greaterThan(root.get(criteria.getColumn()),
                                                                                criteria.getValue());
                                                predicates.add(greaterThan);
                                                break;

                                        case GREATER_THAN_OR_EQUAL_TO:
                                                Predicate greaterThanOrEqualTo = criteriaBuilder
                                                                .greaterThanOrEqualTo(root.get(criteria.getColumn()),
                                                                                criteria.getValue());
                                                predicates.add(greaterThanOrEqualTo);
                                                break;

                                        case CONTAINS:
                                                Predicate contains = criteriaBuilder
                                                                .like(root.get(criteria.getColumn()),
                                                                                "%" + criteria.getValue() + "%");
                                                predicates.add(contains);
                                                break;

                                        default:
                                                throw new IllegalStateException("Unexpected value: " + criteria.getOperator());
                                }

                        }
                        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                };
        }
}
