package com.example.javista.service;

import com.example.javista.dto.request.billDetail.BillDetailCreationRequest;
import com.example.javista.dto.request.billDetail.BillDetailPatchRequest;
import com.example.javista.dto.request.billDetail.BillDetailQueryRequest;
import com.example.javista.dto.request.billDetail.BillDetailUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.billDetail.BillDetailResponse;

public interface BillDetailService {
    PageResponse<BillDetailResponse> getBillDetails(BillDetailQueryRequest query);

    BillDetailResponse getBillDetailById(Integer id);

    BillDetailResponse createBillDetail(BillDetailCreationRequest request);

    BillDetailResponse updateBillDetail(Integer id, BillDetailUpdateRequest request);

    BillDetailResponse patchBillDetail(Integer id, BillDetailPatchRequest request);

    void deleteBillDetail(Integer id);
}
