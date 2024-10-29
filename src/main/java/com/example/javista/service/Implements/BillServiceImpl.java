package com.example.javista.service.Implements;

import com.example.javista.dto.request.bill.BillCreationRequest;
import com.example.javista.dto.request.bill.BillPatchRequest;
import com.example.javista.dto.request.bill.BillQueryRequest;
import com.example.javista.dto.request.bill.BillUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.bill.BillResponse;
import com.example.javista.entity.Bill;
import com.example.javista.filter.FilterSpecification;
import com.example.javista.mapper.BillMapper;
import com.example.javista.repository.BillRepository;
import com.example.javista.repository.RelationshipRepository;
import com.example.javista.service.BillService;
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
