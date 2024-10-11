package com.example.javista.mapper;

import com.example.javista.dto.request.rejectionReason.RejectionReasonCreationRequest;
import com.example.javista.dto.request.rejectionReason.RejectionReasonPatchRequest;
import com.example.javista.dto.request.rejectionReason.RejectionReasonUpdateRequest;
import com.example.javista.dto.response.rejectionReason.RejectionReasonResponse;
import com.example.javista.entity.RejectionReason;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RejectionReasonMapper {
        // mapping dto creation request to entity
        @Mapping(target = "report", ignore = true)
        RejectionReason creationRequestToEntity(RejectionReasonCreationRequest request);

        // mapping dto update request to entity
        @Mapping(target = "report", ignore = true)
        void updateRequestToEntity(@MappingTarget RejectionReason rejectionReason, RejectionReasonUpdateRequest request);

        // mapping dto patch request to entity
        @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
        void patchRequestToEntity(@MappingTarget RejectionReason rejectionReason, RejectionReasonPatchRequest request);

        // mapping entity to dto response
        @Mapping(source = "report.id", target = "reportId")
        RejectionReasonResponse entityToResponse(RejectionReason rejectionReason);
}
