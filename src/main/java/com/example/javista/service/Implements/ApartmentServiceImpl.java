package com.example.javista.service.Implements;

import com.example.javista.dto.request.apartment.ApartmentQueryRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.request.apartment.ApartmentCreationRequest;
import com.example.javista.dto.request.apartment.ApartmentUpdateRequest;
import com.example.javista.dto.response.apartment.ApartmentResponse;
import com.example.javista.entity.Apartment;
import com.example.javista.mapper.ApartmentMapper;
import com.example.javista.repository.ApartmentRepository;
import com.example.javista.service.ApartmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApartmentServiceImpl implements ApartmentService {
        ApartmentRepository apartmentRepository;
        ApartmentMapper apartmentMapper;

        @Override
        public PageResponse<ApartmentResponse> getApartments(ApartmentQueryRequest query) {

                int page = query.getCurrentPage();
                int size = query.getSize();
                Sort sort = Sort.by(query.getSort()).descending();
                Pageable pageable = PageRequest.of(page - 1, size, sort);

                var pageData = apartmentRepository.findAll(pageable);

                return PageResponse.<ApartmentResponse>builder()
                                .currentPage(page)
                                .pageSize(pageData.getSize())
                                .totalPages(pageData.getTotalPages())
                                .totalElements(pageData.getTotalElements())
                                .data(pageData.getContent().stream()
                                                .map(apartmentMapper::entityToResponse)
                                                .toList())
                                .build();
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
                return apartmentMapper.entityToResponse(apartmentRepository.save((apartment)));
        }

        @Override
        public void deleteApartment(String id) {
                apartmentRepository.deleteById(id);
        }

        public void patchApartment(String id, ApartmentUpdateRequest request) {
                Apartment apartment = apartmentRepository.findById(id)
                                .orElseThrow(() ->  new RuntimeException("Apartment Not Found"));

                apartmentMapper.updateRequestToEntity(apartment, request);
                apartment.setUpdatedAt(LocalDateTime.now());
                apartmentRepository.save((apartment));
        }
}
