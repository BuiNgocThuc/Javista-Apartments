package com.example.javista.service.Implements;

import com.example.javista.dto.request.billDetail.BillDetailCreationRequest;
import com.example.javista.dto.request.billDetail.BillDetailPatchRequest;
import com.example.javista.dto.request.billDetail.BillDetailQueryRequest;
import com.example.javista.dto.request.billDetail.BillDetailUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.billDetail.BillDetailResponse;
import com.example.javista.entity.BillDetail;
import com.example.javista.mapper.BillDetailMapper;
import com.example.javista.repository.BillDetailRepository;
import com.example.javista.repository.BillRepository;
import com.example.javista.repository.ServiceRepository;
import com.example.javista.service.BillDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BillDetailServiceImpl implements BillDetailService {
        BillDetailMapper billDetailMapper;
        BillDetailRepository billDetailRepository;

        BillRepository billRepository;
        ServiceRepository serviceRepository;

        @Override
        public PageResponse<BillDetailResponse> getBillDetails(BillDetailQueryRequest query) {
                return null;
        }

        @Override
        public BillDetailResponse getBillDetailById(Integer id) {
                return billDetailMapper.entityToResponse(billDetailRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("BillDetail Not Found")));
        }

        @Override
        public BillDetailResponse createBillDetail(BillDetailCreationRequest request) {
                BillDetail billDetail = billDetailMapper.creationRequestToEntity(request);

                billDetail.setBill(billRepository.findById(request.getBillId())
                                .orElseThrow(() -> new RuntimeException("Bill Not Found")));

                billDetail.setService(serviceRepository.findById(request.getServiceId())
                                .orElseThrow(() -> new RuntimeException("Service Not Found")));

                return billDetailMapper.entityToResponse(billDetailRepository.save(billDetail));
        }

        @Override
        public BillDetailResponse updateBillDetail(Integer id, BillDetailUpdateRequest request) {
                BillDetail billDetail = billDetailRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Bill detail Not Found"));

                billDetail.setBill(billRepository.findById(request.getBillId())
                                .orElseThrow(() -> new RuntimeException("Bill Not Found")));

                billDetail.setService(serviceRepository.findById(request.getServiceId())
                                .orElseThrow(() -> new RuntimeException("Service Not Found")));

                billDetailMapper.updateRequestToEntity(billDetail, request);
                return billDetailMapper.entityToResponse(billDetailRepository.save(billDetail));
        }

        @Override
        public BillDetailResponse patchBillDetail(Integer id, BillDetailPatchRequest request) {
                BillDetail billDetail = billDetailRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Bill detail Not Found"));

                billDetailMapper.patchRequestToEntity(billDetail, request);
                return billDetailMapper.entityToResponse(billDetailRepository.save(billDetail));
        }

        @Override
        public void deleteBillDetail(Integer id) {
                BillDetail billDetail = billDetailRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Bill detail Not Found"));

                billDetail.setDeletedAt(LocalDateTime.now());
                billDetailRepository.save(billDetail);
        }
}
