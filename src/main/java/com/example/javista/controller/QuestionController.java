package com.example.javista.controller;

import com.example.javista.dto.request.question.QuestionCreationRequest;
import com.example.javista.dto.request.question.QuestionPatchRequest;
import com.example.javista.dto.request.question.QuestionQueryRequest;
import com.example.javista.dto.request.question.QuestionUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.question.QuestionResponse;
import com.example.javista.service.QuestionService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestionController {
        QuestionService questionService;

        // test postman Http: http://localhost:8080/javista/questions

        // Query
        @GetMapping
        PageResponse<QuestionResponse> getQuestions(@ModelAttribute
                                        QuestionQueryRequest query) {
                return questionService.getQuestions(query);
        }

        // Query by id
        @GetMapping("/{id}")
        QuestionResponse getQuestionById(@PathVariable Integer id) {
                return questionService.getQuestionById(id);
        }

        // Create
        @PostMapping
        QuestionResponse createQuestion(@RequestBody @Valid QuestionCreationRequest request) {
                return questionService.createQuestion(request);
        }

        // Update
        @PutMapping("/{id}")
        QuestionResponse updateQuestion(@PathVariable Integer id, @RequestBody QuestionUpdateRequest request) {
                return questionService.updateQuestion(id, request);
        }

        //Delete
        @DeleteMapping("/{id}")
        void deleteQuestion(@PathVariable Integer id) {
                questionService.deleteQuestion(id);
        }

        // Patch
        @PatchMapping("/{id}")
        void patchQuestion(@PathVariable Integer id, @RequestBody QuestionPatchRequest request) {
                questionService.patchQuestion(id, request);
        }

}
