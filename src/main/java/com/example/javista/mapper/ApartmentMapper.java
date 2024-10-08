package com.example.javista.mapper;

import com.example.javista.dto.request.apartment.ApartmentCreationRequest;
import com.example.javista.dto.request.apartment.ApartmentUpdateRequest;
import com.example.javista.dto.response.apartment.ApartmentResponse;
import com.example.javista.entity.Apartment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApartmentMapper {
        Apartment creationRequestToEntity(ApartmentCreationRequest request);
        void updateRequestToEntity(Apartment apartment, ApartmentUpdateRequest request);
        ApartmentResponse entityToResponse(Apartment apartment);
}
