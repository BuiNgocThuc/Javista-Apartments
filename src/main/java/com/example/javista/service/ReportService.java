package com.example.javista.service;

import com.example.javista.dto.request.report.ReportCreationRequest;
import com.example.javista.dto.request.report.ReportPatchRequest;
import com.example.javista.dto.request.report.ReportQueryRequest;
import com.example.javista.dto.request.report.ReportUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.report.ReportResponse;

public interface ReportService {
        PageResponse<ReportResponse> getReports(ReportQueryRequest query);

        ReportResponse getReportById(Integer id);

        ReportResponse createReport(ReportCreationRequest request);

        ReportResponse updateReport(Integer id, ReportUpdateRequest request);

        ReportResponse patchReport(Integer id, ReportPatchRequest request);

        void deleteReport(Integer id);
}
