package com.example.javista.controller;

import com.example.javista.dto.request.relationship.RelationshipCreationRequest;
import com.example.javista.dto.request.relationship.RelationshipPatchRequest;
import com.example.javista.dto.request.relationship.RelationshipQueryRequest;
import com.example.javista.dto.request.relationship.RelationshipUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.relationship.RelationshipResponse;
import com.example.javista.service.RelationshipService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/relationships")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RelationshipController {
        RelationshipService relationshipService;

        // test postman Http: http://localhost:8080/javista/relationships

        // Query
        @GetMapping
        PageResponse<RelationshipResponse> getRelationships(@ModelAttribute RelationshipQueryRequest query) {
                return relationshipService.getRelationships(query);
        }

        // Query by id
        @GetMapping("/{id}")
        RelationshipResponse getRelationshipById(@PathVariable Integer id) {
                return relationshipService.getRelationshipById(id);
        }

        // Create
        @PostMapping
        RelationshipResponse createRelationship(@RequestBody RelationshipCreationRequest request) {
                return relationshipService.createRelationship(request);
        }

        // Update
        @PutMapping("/{id}")
        RelationshipResponse updateRelationship(@PathVariable Integer id, @RequestBody RelationshipUpdateRequest request) {
                return relationshipService.updateRelationship(id, request);
        }

        //Delete
        @DeleteMapping("/{id}")
        void deleteRelationship(@PathVariable Integer id) {
                relationshipService.deleteRelationship(id);
        }

        // Patch
        @PatchMapping("/{id}")
        void patchRelationship(@PathVariable Integer id, @RequestBody RelationshipPatchRequest request) {
                relationshipService.patchRelationship(id, request);
        }

}
