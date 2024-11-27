package com.example.javista.service.impl;

import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.javista.dto.request.relationship.RelationshipCreationRequest;
import com.example.javista.dto.request.relationship.RelationshipPatchRequest;
import com.example.javista.dto.request.relationship.RelationshipQueryRequest;
import com.example.javista.dto.request.relationship.RelationshipUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.relationship.RelationshipResponse;
import com.example.javista.entity.Relationship;
import com.example.javista.filter.FilterSpecification;
import com.example.javista.mapper.RelationshipMapper;
import com.example.javista.repository.ApartmentRepository;
import com.example.javista.repository.RelationshipRepository;
import com.example.javista.service.RelationshipService;
import com.example.javista.utils.QueryUtils;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RelationshipServiceImpl implements RelationshipService {
    RelationshipMapper relationshipMapper;
    RelationshipRepository relationshipRepository;

    ApartmentRepository apartmentRepository;

    FilterSpecification<Relationship> filterSpecification;

    @Override
    public PageResponse<RelationshipResponse> getRelationships(RelationshipQueryRequest query) {
        // Pagination and Sorting
        Pageable pageable = QueryUtils.getPagination(query);

        // Filtering and searching by specification
        Specification<Relationship> spec =
                filterSpecification.filteringBySpecification(QueryUtils.getFilterCriterion(query));

        var pageData = relationshipRepository.findAll(spec, pageable);

        return QueryUtils.buildPageResponse(pageData, pageable, relationshipMapper::entityToResponse);
    }

    @Override
    public RelationshipResponse getRelationshipById(Integer id) {
        return relationshipMapper.entityToResponse(
                relationshipRepository.findById(id).orElseThrow(() -> new RuntimeException("Relationship Not Found")));
    }

    @Override
    public RelationshipResponse createRelationship(RelationshipCreationRequest request) {
        Relationship relationship = relationshipMapper.creationRequestToEntity(request);

        relationship.setApartment(apartmentRepository
                .findById(request.getApartmentId())
                .orElseThrow(() -> new RuntimeException("Apartment Not Found")));

        return relationshipMapper.entityToResponse(relationshipRepository.save(relationship));
    }

    @Override
    public RelationshipResponse updateRelationship(Integer id, RelationshipUpdateRequest request) {
        Relationship relationship =
                relationshipRepository.findById(id).orElseThrow(() -> new RuntimeException("Relationship Not Found"));

        relationship.setApartment(apartmentRepository
                .findById(request.getApartmentId())
                .orElseThrow(() -> new RuntimeException("Apartment Not Found")));

        relationshipMapper.updateRequestToEntity(relationship, request);
        return relationshipMapper.entityToResponse(relationshipRepository.save(relationship));
    }

    @Override
    public RelationshipResponse patchRelationship(Integer id, RelationshipPatchRequest request) {
        Relationship relationship =
                relationshipRepository.findById(id).orElseThrow(() -> new RuntimeException("Relationship Not Found"));

        relationshipMapper.patchRequestToEntity(relationship, request);
        return relationshipMapper.entityToResponse(relationshipRepository.save(relationship));
    }

    @Override
    public void deleteRelationship(Integer id) {
        Relationship relationship =
                relationshipRepository.findById(id).orElseThrow(() -> new RuntimeException("Relationship Not Found"));

        relationship.setDeletedAt(LocalDateTime.now());
        relationshipRepository.save(relationship);
    }
}
