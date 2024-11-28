package com.example.javista.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.javista.dto.request.relationship.RelationshipCreationRequest;
import com.example.javista.dto.request.relationship.RelationshipPatchRequest;
import com.example.javista.dto.request.relationship.RelationshipUpdateRequest;
import com.example.javista.dto.response.relationship.RelationshipResponse;
import com.example.javista.entity.Relationship;

@Mapper(componentModel = "spring")
public interface RelationshipMapper {
    // mapping dto creation request to entity
    @Mapping(target = "apartment", ignore = true)
    @Mapping(target = "user", ignore = true)
    Relationship creationRequestToEntity(RelationshipCreationRequest request);

    // mapping dto update request to entity
    @Mapping(target = "apartment", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateRequestToEntity(@MappingTarget Relationship relationship, RelationshipUpdateRequest request);

    // mapping dto patch request to entity
    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void patchRequestToEntity(@MappingTarget Relationship relationship, RelationshipPatchRequest request);

    // mapping entity to dto response
    @Mapping(source = "apartment.id", target = "apartmentId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user", target = "user")
    RelationshipResponse entityToResponse(Relationship relationship);
}
