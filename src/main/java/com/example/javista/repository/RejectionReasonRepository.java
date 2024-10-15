package com.example.javista.repository;

import com.example.javista.entity.RejectionReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RejectionReasonRepository extends JpaRepository<RejectionReason, Integer> {
}
