package com.example.javista.service;

import com.example.javista.dto.request.service.ServiceCreationRequest;
import com.example.javista.dto.request.service.ServicePatchRequest;
import com.example.javista.dto.request.service.ServiceQueryRequest;
import com.example.javista.dto.request.service.ServiceUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.service.ServiceResponse;

public interface ServiceService {
        PageResponse<ServiceResponse> getServices(ServiceQueryRequest query);

        ServiceResponse getServiceById(Integer id);

        ServiceResponse createService(ServiceCreationRequest request);

        ServiceResponse updateService(Integer id, ServiceUpdateRequest request);

        ServiceResponse patchService(Integer id, ServicePatchRequest request);

        void deleteService(Integer id);
}
