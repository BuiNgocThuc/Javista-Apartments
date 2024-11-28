package com.example.javista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.javista.entity.Question;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>, JpaSpecificationExecutor<Question> {
    boolean existsByContent(String content);

    @Query("SELECT q.id AS QuestionId," +
        "q.content AS QuestionContent," +
        "a.id AS AnswerId," +
        "a.content AS AnswerContent," +
        "COUNT(ua.user.id) AS AnswerCount, " +
        "oa.id AS OtherAnswerId," +
        "oa.content AS OtherAnswerContent " +
        "FROM Question q " +
        "LEFT JOIN  q.answers a " +
        "LEFT JOIN  a.userAnswers ua " +
        "LEFT JOIN  q.otherAnswers oa " +
        "WHERE q.survey.id = :surveyId " +
        "GROUP BY q.id, a.id, oa.id"
    )
    List<Object[]> getAnswerStatisticsAndOtherAnswerStatistics(Integer surveyId);
}
