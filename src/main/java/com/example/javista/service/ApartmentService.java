package com.example.javista.service;

import com.example.javista.dto.PageResponse;
import com.example.javista.dto.request.apartment.ApartmentCreationRequest;
import com.example.javista.dto.request.apartment.ApartmentUpdateRequest;
import com.example.javista.dto.response.apartment.ApartmentResponse;


public interface ApartmentService {
        PageResponse<ApartmentResponse> getApartments(int page, int size, String sort);

        ApartmentResponse getApartmentById(String id);

        ApartmentResponse createApartment(ApartmentCreationRequest request);

        ApartmentResponse updateApartment(String id, ApartmentUpdateRequest request);

        void deleteApartment(String id);
}
