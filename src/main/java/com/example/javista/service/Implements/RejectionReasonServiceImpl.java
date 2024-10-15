package com.example.javista.service.Implements;

import com.example.javista.dto.request.rejectionReason.RejectionReasonCreationRequest;
import com.example.javista.dto.request.rejectionReason.RejectionReasonPatchRequest;
import com.example.javista.dto.request.rejectionReason.RejectionReasonQueryRequest;
import com.example.javista.dto.request.rejectionReason.RejectionReasonUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.rejectionReason.RejectionReasonResponse;
import com.example.javista.entity.RejectionReason;
import com.example.javista.mapper.RejectionReasonMapper;
import com.example.javista.repository.RejectionReasonRepository;
import com.example.javista.repository.ReportRepository;
import com.example.javista.service.RejectionReasonService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class RejectionReasonServiceImpl implements RejectionReasonService {
        RejectionReasonMapper rejectionReasonMapper;
        RejectionReasonRepository rejectionReasonRepository;

        ReportRepository reportRepository;

        @Override
        public PageResponse<RejectionReasonResponse> getRejectionReasons(RejectionReasonQueryRequest query) {
                return null;
        }

        @Override
        public RejectionReasonResponse getRejectionReasonById(Integer id) {
                return rejectionReasonMapper.entityToResponse(rejectionReasonRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("RejectionReason Not Found")));
        }

        @Override
        public RejectionReasonResponse createRejectionReason(RejectionReasonCreationRequest request) {
                RejectionReason rejectionReason = rejectionReasonMapper.creationRequestToEntity(request);

                rejectionReason.setReport(reportRepository.findById(request.getReportId())
                                .orElseThrow(() -> new RuntimeException("Report Not Found")));
                return rejectionReasonMapper.entityToResponse(rejectionReasonRepository.save(rejectionReason));
        }

        @Override
        public RejectionReasonResponse updateRejectionReason(Integer id, RejectionReasonUpdateRequest request) {
                RejectionReason rejectionReason = rejectionReasonRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("RejectionReason Not Found"));

                rejectionReason.setReport(reportRepository.findById(request.getReportId())
                                .orElseThrow(() -> new RuntimeException("Report Not Found")));

                rejectionReasonMapper.updateRequestToEntity(rejectionReason, request);
                return rejectionReasonMapper.entityToResponse(rejectionReasonRepository.save(rejectionReason));
        }

        @Override
        public RejectionReasonResponse patchRejectionReason(Integer id, RejectionReasonPatchRequest request) {
                RejectionReason rejectionReason = rejectionReasonRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("RejectionReason Not Found"));

                rejectionReasonMapper.patchRequestToEntity(rejectionReason, request);
                return rejectionReasonMapper.entityToResponse(rejectionReasonRepository.save(rejectionReason));
        }

        @Override
        public void deleteRejectionReason(Integer id) {
                RejectionReason rejectionReason = rejectionReasonRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("RejectionReason Not Found"));

                rejectionReason.setDeletedAt(LocalDateTime.now());
                rejectionReasonRepository.save(rejectionReason);
        }
}
