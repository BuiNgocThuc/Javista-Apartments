package com.example.javista.controller;

import org.springframework.web.bind.annotation.*;

import com.example.javista.dto.request.report.ReportCreationRequest;
import com.example.javista.dto.request.report.ReportPatchRequest;
import com.example.javista.dto.request.report.ReportQueryRequest;
import com.example.javista.dto.request.report.ReportUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.report.ReportResponse;
import com.example.javista.service.ReportService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReportController {
    ReportService reportService;

    // test postman Http: http://localhost:8080/javista/reports

    // Query
    @GetMapping
    PageResponse<ReportResponse> getReports(@ModelAttribute ReportQueryRequest query) {
        return reportService.getReports(query);
    }

    // Query by id
    @GetMapping("/{id}")
    ReportResponse getReportById(@PathVariable Integer id) {
        return reportService.getReportById(id);
    }

    // Create
    @PostMapping
    ReportResponse createReport(@RequestBody ReportCreationRequest request) {
        return reportService.createReport(request);
    }

    // Update
    @PutMapping("/{id}")
    ReportResponse updateReport(@PathVariable Integer id, @RequestBody ReportUpdateRequest request) {
        return reportService.updateReport(id, request);
    }

    // Delete
    @DeleteMapping("/{id}")
    void deleteReport(@PathVariable Integer id) {
        reportService.deleteReport(id);
    }

    // Patch
    @PatchMapping("/{id}")
    void patchReport(@PathVariable Integer id, @RequestBody ReportPatchRequest request) {
        reportService.patchReport(id, request);
    }
}
