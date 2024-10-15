package com.example.javista.service.Implements;

import com.example.javista.dto.request.relationship.RelationshipCreationRequest;
import com.example.javista.dto.request.relationship.RelationshipPatchRequest;
import com.example.javista.dto.request.relationship.RelationshipQueryRequest;
import com.example.javista.dto.request.relationship.RelationshipUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.relationship.RelationshipResponse;
import com.example.javista.entity.Relationship;
import com.example.javista.mapper.RelationshipMapper;
import com.example.javista.repository.ApartmentRepository;
import com.example.javista.repository.RelationshipRepository;
import com.example.javista.service.RelationshipService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class RelationshipServiceImpl implements RelationshipService {
        RelationshipMapper relationshipMapper;
        RelationshipRepository relationshipRepository;

        ApartmentRepository apartmentRepository;

        @Override
        public PageResponse<RelationshipResponse> getRelationships(RelationshipQueryRequest query) {
                return null;
        }

        @Override
        public RelationshipResponse getRelationshipById(Integer id) {
                return relationshipMapper.entityToResponse(relationshipRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Relationship Not Found")));
        }

        @Override
        public RelationshipResponse createRelationship(RelationshipCreationRequest request) {
                Relationship relationship = relationshipMapper.creationRequestToEntity(request);

                relationship.setApartment(apartmentRepository.findById(request.getApartmentId())
                                .orElseThrow(() -> new RuntimeException("Apartment Not Found")));

                return relationshipMapper.entityToResponse(relationshipRepository.save(relationship));
        }

        @Override
        public RelationshipResponse updateRelationship(Integer id, RelationshipUpdateRequest request) {
                Relationship relationship = relationshipRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Relationship Not Found"));

                relationship.setApartment(apartmentRepository.findById(request.getApartmentId())
                                .orElseThrow(() -> new RuntimeException("Apartment Not Found")));

                relationshipMapper.updateRequestToEntity(relationship, request);
                return relationshipMapper.entityToResponse(relationshipRepository.save(relationship));
        }

        @Override
        public RelationshipResponse patchRelationship(Integer id, RelationshipPatchRequest request) {
                Relationship relationship = relationshipRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Relationship Not Found"));

                relationshipMapper.patchRequestToEntity(relationship, request);
                return relationshipMapper.entityToResponse(relationshipRepository.save(relationship));
        }

        @Override
        public void deleteRelationship(Integer id) {
                Relationship relationship = relationshipRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Relationship Not Found"));

                relationship.setDeletedAt(LocalDateTime.now());
                relationshipRepository.save(relationship);
        }
}
