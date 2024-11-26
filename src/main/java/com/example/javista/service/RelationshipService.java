package com.example.javista.service;

import com.example.javista.dto.request.relationship.RelationshipCreationRequest;
import com.example.javista.dto.request.relationship.RelationshipPatchRequest;
import com.example.javista.dto.request.relationship.RelationshipQueryRequest;
import com.example.javista.dto.request.relationship.RelationshipUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.relationship.RelationshipResponse;

public interface RelationshipService {
    PageResponse<RelationshipResponse> getRelationships(RelationshipQueryRequest query);

    RelationshipResponse getRelationshipById(Integer id);

    RelationshipResponse createRelationship(RelationshipCreationRequest request);

    RelationshipResponse updateRelationship(Integer id, RelationshipUpdateRequest request);

    RelationshipResponse patchRelationship(Integer id, RelationshipPatchRequest request);

    void deleteRelationship(Integer id);
}
