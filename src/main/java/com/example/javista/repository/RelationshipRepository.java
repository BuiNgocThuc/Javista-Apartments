package com.example.javista.repository;

import com.example.javista.enums.BillStatus;
import com.example.javista.enums.RelationshipRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.javista.entity.Relationship;

import java.util.List;
import java.util.Optional;

@Repository
public interface RelationshipRepository
        extends JpaRepository<Relationship, Integer>, JpaSpecificationExecutor<Relationship> {
    List<Relationship> findByRole(RelationshipRole role);

    @Query("SELECT r FROM Relationship r " +
        "INNER JOIN fetch r.apartment a " +
        "INNER JOIN r.user u " +
        "WHERE r.role = :role AND r.deletedAt IS NULL " +
        "AND u.isStaying = true")
    List<Relationship> findByRoleAndDeletedAtNull(RelationshipRole role);

    // find all delinquent owners
    @Query("SELECT r " +
        "FROM Relationship r " +
        "INNER JOIN fetch r.apartment a " +
        "INNER JOIN r.bills b " +
        "WHERE r.role = :ownerRole AND r.deletedAt IS NULL " +
        "AND b.status = :overdueStatus " +
        "GROUP BY r.id, a.id, b.id")
    List<Relationship> findDelinquentOwners(
        @Param("ownerRole") RelationshipRole ownerRole,
        @Param("overdueStatus") BillStatus overdueStatus);

}
