package com.example.javista.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.javista.entity.Bill;
import com.example.javista.entity.Relationship;
import com.example.javista.enums.BillStatus;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer>, JpaSpecificationExecutor<Bill> {

    @Query("SELECT b FROM Bill b WHERE b.relationship = :relationship AND b.monthly = :monthly")
    Bill findByRelationshipAndMonthly(Relationship relationship, String monthly);

    @Query("SELECT b FROM Bill b " + "JOIN FETCH b.relationship r " + "JOIN FETCH r.apartment a " + "WHERE b.id = :id")
    Bill findByIdAndJoinApartment(Integer id);

    // find all bills that have not been calculated water price
    List<Bill> findByDeletedAtIsNullAndMonthlyAndNewWaterIsNull(String monthly);

    List<Bill> findByDeletedAtIsNullAndMonthlyAndStatus(String monthly, BillStatus status);
}
