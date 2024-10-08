package com.example.javista.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "rejection_reasons")
public class RejectionReason {
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer id;

        @Column(name = "content")
        String content;

        @Column(name = "created_at")
        LocalDateTime createdAt;

        @Column(name = "updated_at")
        LocalDateTime updatedAt;

        @Column(name = "deleted_at")
        LocalDateTime deletedAt;

        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "report_id")
        Report report;
}