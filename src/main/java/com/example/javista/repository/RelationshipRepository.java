package com.example.javista.repository;

import com.example.javista.enums.RelationshipRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.javista.entity.Relationship;

import java.util.List;
import java.util.Optional;

@Repository
public interface RelationshipRepository
        extends JpaRepository<Relationship, Integer>, JpaSpecificationExecutor<Relationship> {
    List<Relationship> findByRole(RelationshipRole role);
}
