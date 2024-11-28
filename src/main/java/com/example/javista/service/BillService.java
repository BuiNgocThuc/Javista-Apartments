package com.example.javista.service;

import com.example.javista.dto.request.bill.*;
import com.example.javista.dto.request.payment.MomoCallbackRequest;
import com.example.javista.dto.request.payment.MomoPaymentCreationRequest;
import com.example.javista.dto.request.statistics.RevenueStatisticsRequest;
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

    void updateWaterReadings(WaterReadingsUpdateRequest request);
}
