package com.example.javista.service.Implements;

import com.example.javista.dto.request.apartment.ApartmentCreationRequest;
import com.example.javista.dto.request.apartment.ApartmentPatchRequest;
import com.example.javista.dto.request.apartment.ApartmentQueryRequest;
import com.example.javista.dto.request.apartment.ApartmentUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.apartment.ApartmentResponse;
import com.example.javista.entity.Apartment;
import com.example.javista.filter.FilterSpecification;
import com.example.javista.mapper.ApartmentMapper;
import com.example.javista.repository.ApartmentRepository;
import com.example.javista.service.ApartmentService;
import com.example.javista.utils.QueryUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApartmentServiceImpl implements ApartmentService {
        ApartmentRepository apartmentRepository;
        ApartmentMapper apartmentMapper;

        // class filters
        FilterSpecification filterSpecification;


        @Override
        public PageResponse<ApartmentResponse> getApartments(ApartmentQueryRequest query) {
                // Pagination and sorting
                Pageable pageable = QueryUtils.getPagination(query);

                // Filtering and searching by specification
                Specification<Apartment> spec = filterSpecification.filteringBySpecification(QueryUtils.getFilterCriterion(query));

                // Call repository
                var pageData = apartmentRepository.findAll(spec, pageable);

               // Use the utility method to build the PageResponse
                return QueryUtils.buildPageResponse(pageData, pageable, apartmentMapper::entityToResponse);
        }

        @Override
        public ApartmentResponse getApartmentById(String id) {
                return apartmentMapper.entityToResponse(apartmentRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Apartment Not Found")));
        }

        @Override
        public ApartmentResponse createApartment(ApartmentCreationRequest request) {
                Apartment apartment = apartmentMapper.creationRequestToEntity(request);
                return apartmentMapper.entityToResponse(apartmentRepository.save(apartment));
        }

        @Override
        public ApartmentResponse updateApartment(String id, ApartmentUpdateRequest request) {
                Apartment apartment = apartmentRepository.findById(id)
                                .orElseThrow(() ->  new RuntimeException("Apartment Not Found"));

                apartmentMapper.updateRequestToEntity(apartment, request);
                return apartmentMapper.entityToResponse(apartmentRepository.save(apartment));
        }

        @Override
        public ApartmentResponse patchApartment(String id, ApartmentPatchRequest request) {
                Apartment apartment = apartmentRepository.findById(id)
                                .orElseThrow(() ->  new RuntimeException("Apartment Not Found"));

                apartmentMapper.patchRequestToEntity(apartment, request);
                return apartmentMapper.entityToResponse(apartmentRepository.save(apartment));
        }

        @Override
        public void deleteApartment(String id) {
                Apartment apartment = apartmentRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Apartment Not Found"));

                apartment.setDeletedAt(LocalDateTime.now());
                apartmentRepository.save(apartment);
        }
}
