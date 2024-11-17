package com.example.javista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.javista.entity.Relationship;

@Repository
public interface RelationshipRepository
        extends JpaRepository<Relationship, Integer>, JpaSpecificationExecutor<Relationship> {}
