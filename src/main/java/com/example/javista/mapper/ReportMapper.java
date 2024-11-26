package com.example.javista.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.javista.dto.request.report.ReportCreationRequest;
import com.example.javista.dto.request.report.ReportPatchRequest;
import com.example.javista.dto.request.report.ReportUpdateRequest;
import com.example.javista.dto.response.report.ReportResponse;
import com.example.javista.entity.Report;

@Mapper(componentModel = "spring")
public interface ReportMapper {
    // mapping dto creation request to entity
    @Mapping(target = "relationship", ignore = true)
    @Mapping(target = "rejectionReason", ignore = true)
    Report creationRequestToEntity(ReportCreationRequest request);

    // mapping dto update request to entity
    @Mapping(target = "relationship", ignore = true)
    @Mapping(target = "rejectionReason", ignore = true)
    void updateRequestToEntity(@MappingTarget Report report, ReportUpdateRequest request);

    // mapping dto patch request to entity
    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void patchRequestToEntity(@MappingTarget Report report, ReportPatchRequest request);

    // mapping entity to dto response
    @Mapping(source = "relationship.id", target = "relationshipId")
    @Mapping(source = "rejectionReason.id", target = "rejectionReasonId")
    ReportResponse entityToResponse(Report report);
}
