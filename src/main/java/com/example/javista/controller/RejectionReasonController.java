package com.example.javista.controller;

import com.example.javista.dto.request.rejectionReason.RejectionReasonCreationRequest;
import com.example.javista.dto.request.rejectionReason.RejectionReasonPatchRequest;
import com.example.javista.dto.request.rejectionReason.RejectionReasonQueryRequest;
import com.example.javista.dto.request.rejectionReason.RejectionReasonUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.rejectionReason.RejectionReasonResponse;
import com.example.javista.service.RejectionReasonService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rejectionReasons")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RejectionReasonController {
        RejectionReasonService rejectionReasonService;

        // test postman Http: http://localhost:8080/javista/rejectionReasons

        // Query
        @GetMapping
        PageResponse<RejectionReasonResponse> getRejectionReasons(@RequestParam RejectionReasonQueryRequest query) {
                return rejectionReasonService.getRejectionReasons(query);
        }

        // Query by id
        @GetMapping("/{id}")
        RejectionReasonResponse getRejectionReasonById(@PathVariable Integer id) {
                return rejectionReasonService.getRejectionReasonById(id);
        }

        // Create
        @PostMapping
        RejectionReasonResponse createRejectionReason(@RequestBody RejectionReasonCreationRequest request) {
                return rejectionReasonService.createRejectionReason(request);
        }

        // Update
        @PutMapping("/{id}")
        RejectionReasonResponse updateRejectionReason(@PathVariable Integer id, @RequestBody RejectionReasonUpdateRequest request) {
                return rejectionReasonService.updateRejectionReason(id, request);
        }

        //Delete
        @DeleteMapping("/{id}")
        void deleteRejectionReason(@PathVariable Integer id) {
                rejectionReasonService.deleteRejectionReason(id);
        }

        // Patch
        @PatchMapping("/{id}")
        void patchRejectionReason(@PathVariable Integer id, @RequestBody RejectionReasonPatchRequest request) {
                rejectionReasonService.patchRejectionReason(id, request);
        }

}
