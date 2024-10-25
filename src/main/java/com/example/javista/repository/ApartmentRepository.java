package com.example.javista.repository;

import com.example.javista.entity.Apartment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository  extends JpaRepository<Apartment, String>, JpaSpecificationExecutor<Apartment> {
}
