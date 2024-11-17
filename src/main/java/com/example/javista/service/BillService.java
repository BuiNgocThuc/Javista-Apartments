package com.example.javista.service;

import com.example.javista.dto.request.bill.BillCreationRequest;
import com.example.javista.dto.request.bill.BillPatchRequest;
import com.example.javista.dto.request.bill.BillQueryRequest;
import com.example.javista.dto.request.bill.BillUpdateRequest;
import com.example.javista.dto.request.payment.MomoCallbackRequest;
import com.example.javista.dto.request.payment.MomoPaymentCreationRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.bill.BillResponse;
import com.example.javista.dto.response.payment.MomoPaymentResponse;

public interface BillService {
    void handleMomoCallback(Integer billId, MomoCallbackRequest request);

    MomoPaymentResponse createMomoPayment(Integer billId, MomoPaymentCreationRequest request);

    PageResponse<BillResponse> getBills(BillQueryRequest query);

    BillResponse getBillById(Integer id);

    BillResponse createBill(BillCreationRequest request);

    BillResponse updateBill(Integer id, BillUpdateRequest request);

    BillResponse patchBill(Integer id, BillPatchRequest request);

    void deleteBill(Integer id);
}
