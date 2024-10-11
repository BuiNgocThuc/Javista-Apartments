package com.example.javista.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "reports")
public class Report {
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer id;

        @Column(name = "content")
        String content;

        @Column(name = "title")
        String title;

        @Column(name = "status")
        String status;

        @CreatedDate
        @Column(name = "created_at")
        LocalDateTime createdAt;

        @LastModifiedDate
        @Column(name = "updated_at")
        LocalDateTime updatedAt;

        @Column(name = "deleted_at")
        LocalDateTime deletedAt;

        // relationship many to one mapped by relationship
        @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                        CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
        @JoinColumn(name = "relationship_id")
        Relationship relationship;

        // relationship one to one mapped by rejection reason
        @OneToOne(mappedBy = "report",
                        cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        RejectionReason rejectionReason;
}