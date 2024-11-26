package com.example.javista.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.javista.dto.request.apartment.ApartmentCreationRequest;
import com.example.javista.dto.request.apartment.ApartmentPatchRequest;
import com.example.javista.dto.request.apartment.ApartmentUpdateRequest;
import com.example.javista.dto.response.apartment.ApartmentResponse;
import com.example.javista.entity.Apartment;

@Mapper(componentModel = "spring")
public interface ApartmentMapper {
    // mapping dto creation request to entity
    Apartment creationRequestToEntity(ApartmentCreationRequest request);

    // mapping dto update request to entity
    void updateRequestToEntity(@MappingTarget Apartment apartment, ApartmentUpdateRequest request);

    // mapping dto patch request to entity
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchRequestToEntity(@MappingTarget Apartment apartment, ApartmentPatchRequest request);

    // mapping entity to dto response
    ApartmentResponse entityToResponse(Apartment apartment);
}
