package com.example.javista.controller;

import com.example.javista.dto.PageResponse;
import com.example.javista.dto.request.apartment.ApartmentCreationRequest;
import com.example.javista.dto.response.apartment.ApartmentResponse;
import com.example.javista.service.ApartmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

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
                        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                        @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                        @RequestParam(value = "sort", required = false, defaultValue = "createdAt") String  sort) {
                return apartmentService.getApartments(page, size, sort);
        }

        // Create
        @PostMapping
        ApartmentResponse createApartment(@RequestBody ApartmentCreationRequest request) {
                return apartmentService.createApartment(request);
        }

}
