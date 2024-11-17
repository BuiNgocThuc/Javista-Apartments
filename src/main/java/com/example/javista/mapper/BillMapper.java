package com.example.javista.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.javista.dto.request.bill.BillCreationRequest;
import com.example.javista.dto.request.bill.BillPatchRequest;
import com.example.javista.dto.request.bill.BillUpdateRequest;
import com.example.javista.dto.response.bill.BillResponse;
import com.example.javista.entity.Bill;

@Mapper(componentModel = "spring")
public interface BillMapper {
    // mapping creation request to entity
    @Mapping(target = "relationship", ignore = true)
    Bill creationRequestToEntity(BillCreationRequest request);

    // mapping update request to entity
    @Mapping(target = "relationship", ignore = true)
    void updateRequestToEntity(@MappingTarget Bill bill, BillUpdateRequest request);

    // mapping patch request to entity
    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void patchRequestToEntity(@MappingTarget Bill bill, BillPatchRequest request);

    // mapping entity to response
    @Mapping(source = "relationship.id", target = "relationshipId")
    BillResponse entityToResponse(Bill bill);
}
