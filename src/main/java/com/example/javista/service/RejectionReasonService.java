package com.example.javista.service;

import com.example.javista.dto.request.rejectionReason.RejectionReasonCreationRequest;
import com.example.javista.dto.request.rejectionReason.RejectionReasonPatchRequest;
import com.example.javista.dto.request.rejectionReason.RejectionReasonQueryRequest;
import com.example.javista.dto.request.rejectionReason.RejectionReasonUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.rejectionReason.RejectionReasonResponse;

public interface RejectionReasonService {
        PageResponse<RejectionReasonResponse> getRejectionReasons(RejectionReasonQueryRequest query);

        RejectionReasonResponse getRejectionReasonById(Integer id);

        RejectionReasonResponse createRejectionReason(RejectionReasonCreationRequest request);

        RejectionReasonResponse updateRejectionReason(Integer id, RejectionReasonUpdateRequest request);

        RejectionReasonResponse patchRejectionReason(Integer id, RejectionReasonPatchRequest request);

        void deleteRejectionReason(Integer id);
}
