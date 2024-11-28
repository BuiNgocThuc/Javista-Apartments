package com.example.javista.mapper;

import com.example.javista.dto.response.statistics.survey.AnswerStatisticsResponse;
import com.example.javista.dto.response.statistics.survey.OtherAnswerStatisticsResponse;
import com.example.javista.dto.response.statistics.survey.QuestionStatisticsResponse;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface StatisticsMapper {

    // Main mapping method for QuestionStatisticsResponse
    default QuestionStatisticsResponse mapToQuestionStatisticsResponse(List<Object[]> rows) {
        if (rows == null || rows.isEmpty()) {
            return null; // Handle empty or null rows
        }

        // Extract the first row for questionId and content
        Object[] firstRow = rows.getFirst();

        // Build and return the QuestionStatisticsResponse
        return QuestionStatisticsResponse.builder()
            .questionId((Integer) firstRow[0]) // Extract questionId
            .content((String) firstRow[1]) // Extract content
            .answers(mapAnswers(rows)) // Map answers
            .otherAnswers(mapOtherAnswers(rows)) // Map other answers
            .build();
    }

    // Custom method to map answers
    default Set<AnswerStatisticsResponse> mapAnswers(List<Object[]> rows) {
        return rows.stream()
            .filter(row -> row[2] != null) // Filter out rows without answers
            .map(row -> AnswerStatisticsResponse.builder()
                .answerId((Integer) row[2])
                .content((String) row[3])
                .count(((Number) row[4]).intValue())
                .isMostSelected(false) // Add logic for isMostSelected if needed
                .build())
            .collect(Collectors.toSet());
    }

    // Custom method to map other answers
    default Set<OtherAnswerStatisticsResponse> mapOtherAnswers(List<Object[]> rows) {
        return rows.stream()
            .filter(row -> row[5] != null) // Filter out rows without other answers
            .map(row -> OtherAnswerStatisticsResponse.builder()
                .otherAnswerId((Integer) row[5])
                .content((String) row[6])
                .build())
            .collect(Collectors.toSet());
    }
}

