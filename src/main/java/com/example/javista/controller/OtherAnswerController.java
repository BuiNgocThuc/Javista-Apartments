package com.example.javista.controller;

import com.example.javista.dto.request.otherAnswer.OtherAnswerCreationRequest;
import com.example.javista.dto.request.otherAnswer.OtherAnswerPatchRequest;
import com.example.javista.dto.request.otherAnswer.OtherAnswerQueryRequest;
import com.example.javista.dto.request.otherAnswer.OtherAnswerUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.otherAnswer.OtherAnswerResponse;
import com.example.javista.service.OtherAnswerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/otherAnswers")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OtherAnswerController {
        OtherAnswerService otherAnswerService;

        // test postman Http: http://localhost:8080/javista/otherAnswers

        // Query
        @GetMapping
        PageResponse<OtherAnswerResponse> getOtherAnswers(@RequestParam OtherAnswerQueryRequest query) {
                return otherAnswerService.getOtherAnswers(query);
        }

        // Query by id
        @GetMapping("/{id}")
        OtherAnswerResponse getOtherAnswerById(@PathVariable Integer id) {
                return otherAnswerService.getOtherAnswerById(id);
        }

        // Create
        @PostMapping
        OtherAnswerResponse createOtherAnswer(@RequestBody OtherAnswerCreationRequest request) {
                return otherAnswerService.createOtherAnswer(request);
        }

        // Update
        @PutMapping("/{id}")
        OtherAnswerResponse updateOtherAnswer(@PathVariable Integer id, @RequestBody OtherAnswerUpdateRequest request) {
                return otherAnswerService.updateOtherAnswer(id, request);
        }

        //Delete
        @DeleteMapping("/{id}")
        void deleteOtherAnswer(@PathVariable Integer id) {
                otherAnswerService.deleteOtherAnswer(id);
        }

        // Patch
        @PatchMapping("/{id}")
        void patchOtherAnswer(@PathVariable Integer id, @RequestBody OtherAnswerPatchRequest request) {
                otherAnswerService.patchOtherAnswer(id, request);
        }

}
