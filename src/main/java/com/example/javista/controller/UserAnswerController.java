package com.example.javista.controller;

import org.springframework.web.bind.annotation.*;

import com.example.javista.dto.request.userAnswer.UserAnswerCreationRequest;
import com.example.javista.dto.request.userAnswer.UserAnswerPatchRequest;
import com.example.javista.dto.request.userAnswer.UserAnswerQueryRequest;
import com.example.javista.dto.request.userAnswer.UserAnswerUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.userAnswer.UserAnswerResponse;
import com.example.javista.service.UserAnswerService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userAnswers")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserAnswerController {
    UserAnswerService userAnswerService;

    // test postman Http: http://localhost:8080/javista/userAnswers

    // Query
    @GetMapping
    PageResponse<UserAnswerResponse> getUserAnswers(@ModelAttribute UserAnswerQueryRequest query) {
        return userAnswerService.getUserAnswers(query);
    }

    // Query by id
    @GetMapping("/{id}")
    UserAnswerResponse getUserAnswerById(@PathVariable Integer id) {
        return userAnswerService.getUserAnswerById(id);
    }

    // Create
    @PostMapping
    UserAnswerResponse createUserAnswer(@RequestBody UserAnswerCreationRequest request) {
        return userAnswerService.createUserAnswer(request);
    }

    // Update
    @PutMapping("/{id}")
    UserAnswerResponse updateUserAnswer(@PathVariable Integer id, @RequestBody UserAnswerUpdateRequest request) {
        return userAnswerService.updateUserAnswer(id, request);
    }

    // Delete
    @DeleteMapping("/{id}")
    void deleteUserAnswer(@PathVariable Integer id) {
        userAnswerService.deleteUserAnswer(id);
    }

    // Patch
    @PatchMapping("/{id}")
    void patchUserAnswer(@PathVariable Integer id, @RequestBody UserAnswerPatchRequest request) {
        userAnswerService.patchUserAnswer(id, request);
    }
}
