package com.example.javista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.javista.entity.Survey;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Integer>, JpaSpecificationExecutor<Survey> {

    @Query(
            value = "SELECT COUNT(DISTINCT user_id) AS TotalParticipants " + "FROM ( "
                    + "SELECT ua.user_id "
                    + "FROM user_answers ua "
                    + "INNER JOIN answers a ON ua.answer_id = a.id "
                    + "INNER JOIN questions q ON a.question_id = q.id "
                    + "WHERE q.survey_id = :surveyId "
                    + "UNION "
                    + "SELECT oa.user_id "
                    + "FROM other_answers oa "
                    + "INNER JOIN questions q ON oa.question_id = q.id "
                    + "WHERE q.survey_id = :surveyId "
                    + ") AS CombinedUsers",
            nativeQuery = true)
    Integer getTotalParticipants(Integer surveyId);
}
