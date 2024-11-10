package com.example.javista.service.impl;

import com.example.javista.configuration.MomoConfig;
import com.example.javista.dto.request.bill.BillCreationRequest;
import com.example.javista.dto.request.bill.BillPatchRequest;
import com.example.javista.dto.request.bill.BillQueryRequest;
import com.example.javista.dto.request.bill.BillUpdateRequest;
import com.example.javista.dto.request.payment.MomoCallbackRequest;
import com.example.javista.dto.request.payment.MomoPaymentCreationRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.bill.BillResponse;
import com.example.javista.dto.response.payment.MomoPaymentResponse;
import com.example.javista.entity.Bill;
import com.example.javista.enums.BillStatus;
import com.example.javista.filter.FilterSpecification;
import com.example.javista.mapper.BillMapper;
import com.example.javista.repository.BillRepository;
import com.example.javista.repository.RelationshipRepository;
import com.example.javista.service.BillService;
import com.example.javista.service.payment.MomoService;
import com.example.javista.utils.CreateSignature;
import com.example.javista.utils.QueryUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class BillServiceImpl implements BillService {
        BillMapper billMapper;
        BillRepository billRepository;

        RelationshipRepository relationshipRepository;
        FilterSpecification<Bill> filterSpecification;

        MomoConfig momoConfig;
        CreateSignature createSignature;
        MomoService momoService;

        @Override
        public void handleMomoCallback(Integer id, MomoCallbackRequest callbackDto) {
                // First, generate the signature to compare
                String rawData = String.format("accessKey=%s&amount=%.0f&extraData=%s&message=%s&orderId=%s" +
                                "&orderInfo=%s&orderType=%s&partnerCode=%s&payType=%s&requestId=%s" +
                                "&responseTime=%d&resultCode=%d&transId=%d",
                        momoConfig.getAccessKey(),
                        callbackDto.getAmount(),
                        callbackDto.getExtraData(),
                        callbackDto.getMessage(),
                        callbackDto.getOrderId(),
                        callbackDto.getOrderInfo(),
                        callbackDto.getOrderType(),
                        callbackDto.getPartnerCode(),
                        callbackDto.getPayType(),
                        callbackDto.getRequestId(),
                        callbackDto.getResponseTime(),
                        callbackDto.getResultCode(),
                        callbackDto.getTransId());

                // Create the expected signature from the rawData
                String expectedSignature = null;
                try {
                        expectedSignature = createSignature.computeHmacSha256(rawData, momoConfig.getSecretKey());
                } catch (Exception e) {
                        throw new RuntimeException(e);
                }

                // Compare the received signature with the expected signature
                if (callbackDto.getSignature().equals(expectedSignature) && callbackDto.getResultCode() == 0) {
                        // Proceed with processing the payment
                        Bill bill = billRepository.findById(id).orElse(null);
                        if (bill != null) {
                                bill.setStatus(BillStatus.PAID); // Update the bill status to PAID
                                billRepository.save(bill); // Save the updated bill
                        }
                } else {
                        // Handle signature mismatch or other issues
                        System.out.println("Signature mismatch or unsuccessful result code.");
                }
        }

        @Override
        public MomoPaymentResponse createMomoPayment(Integer billId, MomoPaymentCreationRequest request) {
                Bill bill = billRepository.findById(billId).orElseThrow(() -> new RuntimeException("Bill not found"));
                System.out.println(bill.toString());
                try {
                        return momoService.createPayment(bill, request);
                } catch (Exception e) {
                        throw new RuntimeException(e);
                }
        }

        @Override
        public PageResponse<BillResponse> getBills(BillQueryRequest query) {
                // Pagination and Sorting
                Pageable pageable = QueryUtils.getPagination(query);

                //Filtering and searching by specification
                Specification<Bill> spec = filterSpecification.filteringBySpecification(
                                QueryUtils.getFilterCriterion(query)
                );

                var pageData = billRepository.findAll(spec, pageable);

                return QueryUtils.buildPageResponse(pageData, pageable, billMapper::entityToResponse);
        }

        @Override
        public BillResponse getBillById(Integer id) {
                return billMapper.entityToResponse(billRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Bill Not Found")));
        }

        @Override
        public BillResponse createBill(BillCreationRequest request) {
                Bill bill = billMapper.creationRequestToEntity(request);

                bill.setRelationship(relationshipRepository.findById(request.getRelationshipId())
                                .orElseThrow(() -> new RuntimeException("Relationship Not Found")));

                return billMapper.entityToResponse(billRepository.save(bill));
        }

        @Override
        public BillResponse updateBill(Integer id, BillUpdateRequest request) {
                Bill bill = billRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Bill Not Found"));

                bill.setRelationship(relationshipRepository.findById(request.getRelationshipId())
                                .orElseThrow(() -> new RuntimeException("Relationship Not Found")));

                billMapper.updateRequestToEntity(bill, request);
                return billMapper.entityToResponse(billRepository.save(bill));
        }

        @Override
        public BillResponse patchBill(Integer id, BillPatchRequest request) {
                Bill bill = billRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Bill Not Found"));

                billMapper.patchRequestToEntity(bill, request);
                return billMapper.entityToResponse(billRepository.save(bill));
        }

        @Override
        public void deleteBill(Integer id) {
                Bill bill = billRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Bill Not Found"));

                bill.setDeletedAt(LocalDateTime.now());
                billRepository.save(bill);
        }
}
