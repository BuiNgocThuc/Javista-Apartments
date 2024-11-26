package com.example.javista.mapper;

import org.mapstruct.*;

import com.example.javista.dto.request.billDetail.BillDetailCreationRequest;
import com.example.javista.dto.request.billDetail.BillDetailPatchRequest;
import com.example.javista.dto.request.billDetail.BillDetailUpdateRequest;
import com.example.javista.dto.response.billDetail.BillDetailResponse;
import com.example.javista.entity.BillDetail;

@Mapper(componentModel = "spring")
public interface BillDetailMapper {
    // mapping creation request to entity
    @Mapping(target = "bill", ignore = true)
    @Mapping(target = "service", ignore = true)
    BillDetail creationRequestToEntity(BillDetailCreationRequest request);

    // mapping update request to entity
    @Mapping(target = "bill", ignore = true)
    @Mapping(target = "service", ignore = true)
    void updateRequestToEntity(@MappingTarget BillDetail billDetail, BillDetailUpdateRequest request);

    // mapping patch request to entity
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchRequestToEntity(@MappingTarget BillDetail billDetail, BillDetailPatchRequest request);

    // mapping entity to response
    @Mapping(source = "bill.id", target = "billId")
    @Mapping(source = "service.id", target = "serviceId")
    BillDetailResponse entityToResponse(BillDetail billDetail);
}
