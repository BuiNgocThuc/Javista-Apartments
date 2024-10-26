package com.example.javista.controller;

import com.example.javista.dto.request.answer.AnswerCreationRequest;
import com.example.javista.dto.request.answer.AnswerPatchRequest;
import com.example.javista.dto.request.answer.AnswerQueryRequest;
import com.example.javista.dto.request.answer.AnswerUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.answer.AnswerResponse;
import com.example.javista.service.AnswerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnswerController {
        AnswerService answerService;

        // query
        @GetMapping
        PageResponse<AnswerResponse> getAnswers(@ModelAttribute
                                                AnswerQueryRequest query) {
                return answerService.getAnswers(query);
        }

        // get by id
        @GetMapping("/{id}")
        AnswerResponse getAnswerById(@PathVariable Integer id) {
                return answerService.getAnswerById(id);
        }

        // create
        @PostMapping
        AnswerResponse createAnswer(@RequestBody AnswerCreationRequest request) {
                return answerService.createAnswer(request);
        }

        // update
        @PutMapping("/{id}")
        AnswerResponse updateAnswer(@PathVariable Integer id, @RequestBody AnswerUpdateRequest request) {
                return answerService.updateAnswer(id, request);
        }

        // patch
        @PatchMapping("/id")
        AnswerResponse patchAnswer(@PathVariable Integer id, @RequestBody AnswerPatchRequest request) {
                return answerService.patchAnswer(id, request);
        }

        // delete
        @DeleteMapping
        void deleteAnswer(@PathVariable Integer id) {
                answerService.deleteAnswer(id);
        }
}
