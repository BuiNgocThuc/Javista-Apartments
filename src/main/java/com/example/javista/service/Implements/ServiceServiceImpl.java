package com.example.javista.service.Implements;

import com.example.javista.dto.request.service.ServiceCreationRequest;
import com.example.javista.dto.request.service.ServicePatchRequest;
import com.example.javista.dto.request.service.ServiceQueryRequest;
import com.example.javista.dto.request.service.ServiceUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.service.ServiceResponse;
import com.example.javista.entity.Service;
import com.example.javista.mapper.ServiceMapper;
import com.example.javista.repository.ServiceRepository;
import com.example.javista.service.ServiceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class ServiceServiceImpl implements ServiceService {
        ServiceMapper serviceMapper;
        ServiceRepository serviceRepository;

        @Override
        public PageResponse<ServiceResponse> getServices(ServiceQueryRequest query) {
                return null;
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
