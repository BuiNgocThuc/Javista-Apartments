package com.example.javista.controller;

import com.example.javista.dto.request.apartment.ApartmentPatchRequest;
import com.example.javista.dto.request.apartment.ApartmentQueryRequest;
import com.example.javista.dto.response.ApiResponse;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.request.apartment.ApartmentCreationRequest;
import com.example.javista.dto.request.apartment.ApartmentUpdateRequest;
import com.example.javista.dto.response.apartment.ApartmentResponse;
import com.example.javista.service.ApartmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/apartments")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApartmentController {
        ApartmentService apartmentService;

        // test postman Http: http://localhost:8080/javista/apartments

        // Query
        @GetMapping
        PageResponse<ApartmentResponse> getApartments(
                        @ModelAttribute ApartmentQueryRequest query) {
                return apartmentService.getApartments(query);
        }

        // Query by id
        @GetMapping("/{id}")
        ApartmentResponse getApartmentById(@PathVariable String id) {
                return apartmentService.getApartmentById(id);
        }

        // Create
        @PostMapping
        ApiResponse<ApartmentResponse>  createApartment(@RequestBody ApartmentCreationRequest request) {

                return ApiResponse.<ApartmentResponse>builder()
                        .result(apartmentService.createApartment(request))
                        .build();
        }

        // Update
        @PutMapping("/{id}")
        ApartmentResponse updateApartment(@PathVariable String id, @RequestBody ApartmentUpdateRequest request) {
                return apartmentService.updateApartment(id, request);
        }

        //Delete
        @DeleteMapping("/{id}")
        void deleteApartment(@PathVariable String id) {
                apartmentService.deleteApartment(id);
        }

        // Patch
        @PatchMapping("/{id}")
        void patchApartment(@PathVariable String id, @RequestBody ApartmentPatchRequest request) {
                apartmentService.patchApartment(id, request);
        }

}
