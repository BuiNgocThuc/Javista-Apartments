package com.example.javista.service.Implements;

import com.example.javista.dto.request.report.ReportCreationRequest;
import com.example.javista.dto.request.report.ReportPatchRequest;
import com.example.javista.dto.request.report.ReportQueryRequest;
import com.example.javista.dto.request.report.ReportUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.report.ReportResponse;
import com.example.javista.entity.Report;
import com.example.javista.mapper.ReportMapper;
import com.example.javista.repository.RelationshipRepository;
import com.example.javista.repository.ReportRepository;
import com.example.javista.service.ReportService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class ReportServiceImpl implements ReportService {
        ReportMapper reportMapper;
        ReportRepository reportRepository;

        RelationshipRepository relationshipRepository;

        @Override
        public PageResponse<ReportResponse> getReports(ReportQueryRequest query) {
                return null;
        }

        @Override
        public ReportResponse getReportById(Integer id) {
                return reportMapper.entityToResponse(reportRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Report Not Found")));
        }

        @Override
        public ReportResponse createReport(ReportCreationRequest request) {
                Report report = reportMapper.creationRequestToEntity(request);

                report.setRelationship(relationshipRepository.findById(request.getRelationshipId())
                                .orElseThrow(() -> new RuntimeException("Relationship Not Found")));
                return reportMapper.entityToResponse(reportRepository.save(report));
        }

        @Override
        public ReportResponse updateReport(Integer id, ReportUpdateRequest request) {
                Report report = reportRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Report Not Found"));

                report.setRelationship(relationshipRepository.findById(request.getRelationshipId())
                                .orElseThrow(() -> new RuntimeException("Relationship Not Found")));

                reportMapper.updateRequestToEntity(report, request);
                return reportMapper.entityToResponse(reportRepository.save(report));
        }

        @Override
        public ReportResponse patchReport(Integer id, ReportPatchRequest request) {
                Report report = reportRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Report Not Found"));

                reportMapper.patchRequestToEntity(report, request);
                return reportMapper.entityToResponse(reportRepository.save(report));
        }

        @Override
        public void deleteReport(Integer id) {
                Report report = reportRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Report Not Found"));

                report.setDeletedAt(LocalDateTime.now());
                reportRepository.save(report);
        }
}
