package com.example.javista.service.impl;

import com.example.javista.dto.request.service.ServiceCreationRequest;
import com.example.javista.dto.request.service.ServicePatchRequest;
import com.example.javista.dto.request.service.ServiceQueryRequest;
import com.example.javista.dto.request.service.ServiceUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.service.ServiceResponse;
import com.example.javista.entity.Service;
import com.example.javista.filter.FilterSpecification;
import com.example.javista.mapper.ServiceMapper;
import com.example.javista.repository.ServiceRepository;
import com.example.javista.service.ServiceService;
import com.example.javista.utils.QueryUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class ServiceServiceImpl implements ServiceService {
        ServiceMapper serviceMapper;
        ServiceRepository serviceRepository;

        FilterSpecification<Service> filterSpecification;

        @Override
        public PageResponse<ServiceResponse> getServices(ServiceQueryRequest query) {
                // Pagination and Sorting
                Pageable pageable = QueryUtils.getPagination(query);

                //Filtering and searching by specification
                Specification<Service> spec = filterSpecification.filteringBySpecification(
                                QueryUtils.getFilterCriterion(query)
                );

                var pageData = serviceRepository.findAll(spec, pageable);

                return QueryUtils.buildPageResponse(pageData, pageable, serviceMapper::entityToResponse);
        }

        @Override
        public ServiceResponse getServiceById(Integer id) {
                return serviceMapper.entityToResponse(serviceRepository.findById(id)
                          .orElseThrow(() -> new RuntimeException("Service Not Found")));
        }

        @Override
        public ServiceResponse createService(ServiceCreationRequest request) {
                Service service = serviceMapper.creationRequestToEntity(request);
                return serviceMapper.entityToResponse(serviceRepository.save(service));
        }

        @Override
        public ServiceResponse updateService(Integer id, ServiceUpdateRequest request) {
                Service service = serviceRepository.findById(id)
                          .orElseThrow(() -> new RuntimeException("Service Not Found"));

                serviceMapper.updateRequestToEntity(service, request);
                return serviceMapper.entityToResponse(serviceRepository.save(service));
        }

        @Override
        public ServiceResponse patchService(Integer id, ServicePatchRequest request) {
                Service service = serviceRepository.findById(id)
                          .orElseThrow(() -> new RuntimeException("Service Not Found"));

                serviceMapper.patchRequestToEntity(service, request);
                return serviceMapper.entityToResponse(serviceRepository.save(service));
        }

        @Override
        public void deleteService(Integer id) {
                Service service = serviceRepository.findById(id)
                          .orElseThrow(() -> new RuntimeException("Service Not Found"));

                service.setDeletedAt(LocalDateTime.now());
                serviceRepository.save(service);
        }
}
