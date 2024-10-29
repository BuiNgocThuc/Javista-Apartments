package com.example.javista.controller;

import com.example.javista.dto.request.service.ServiceCreationRequest;
import com.example.javista.dto.request.service.ServicePatchRequest;
import com.example.javista.dto.request.service.ServiceQueryRequest;
import com.example.javista.dto.request.service.ServiceUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.service.ServiceResponse;
import com.example.javista.service.ServiceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/services")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceController {
        ServiceService serviceService;

        // test postman Http: http://localhost:8080/javista/services

        // Query
        @GetMapping
        PageResponse<ServiceResponse> getServices(@ModelAttribute ServiceQueryRequest query) {
                return serviceService.getServices(query);
        }

        // Query by id
        @GetMapping("/{id}")
        ServiceResponse getServiceById(@PathVariable Integer id) {
                return serviceService.getServiceById(id);
        }

        // Create
        @PostMapping
        ServiceResponse createService(@RequestBody ServiceCreationRequest request) {
                return serviceService.createService(request);
        }

        // Update
        @PutMapping("/{id}")
        ServiceResponse updateService(@PathVariable Integer id, @RequestBody ServiceUpdateRequest request) {
                return serviceService.updateService(id, request);
        }

        //Delete
        @DeleteMapping("/{id}")
        void deleteService(@PathVariable Integer id) {
                serviceService.deleteService(id);
        }

        // Patch
        @PatchMapping("/{id}")
        void patchService(@PathVariable Integer id, @RequestBody ServicePatchRequest request) {
                serviceService.patchService(id, request);
        }

}
