package com.example.javista.mapper;

import com.example.javista.dto.request.service.ServiceCreationRequest;
import com.example.javista.dto.request.service.ServicePatchRequest;
import com.example.javista.dto.request.service.ServiceUpdateRequest;
import com.example.javista.dto.response.service.ServiceResponse;
import com.example.javista.entity.Service;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
        // mapping dto creation request to entity
        Service creationRequestToEntity(ServiceCreationRequest request);

        // mapping dto update request to entity
        void updateRequestToEntity(@MappingTarget Service service, ServiceUpdateRequest request);

        // mapping dto patch request to entity
        @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
        void patchRequestToEntity(@MappingTarget Service service, ServicePatchRequest request);

        // mapping entity to dto response
        ServiceResponse entityToResponse(Service service);
}
