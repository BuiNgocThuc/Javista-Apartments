package com.example.javista.repository;

import com.example.javista.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
        boolean existsByContent(String content);
}
