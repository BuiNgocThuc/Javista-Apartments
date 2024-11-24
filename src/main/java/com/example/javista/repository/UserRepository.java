package com.example.javista.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.javista.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    List<User> findByPassword(String password);
}
