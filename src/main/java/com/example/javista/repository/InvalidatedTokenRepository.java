package com.example.javista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.javista.entity.InvalidatedToken;

@Repository
public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {
    @Modifying
    @Query("DELETE FROM InvalidatedToken t WHERE t.expiryTime < CURRENT_TIMESTAMP")
    void deleteExpiredTokens();
}
