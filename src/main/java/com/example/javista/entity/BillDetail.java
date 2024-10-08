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
@Table(name = "bill_details")
public class BillDetail {
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer id;

        @Column(name = "price")
        Float price;

        @Column(name = "created_at")
        LocalDateTime createdAt;

        @Column(name = "updated_at")
        LocalDateTime updatedAt;

        @Column(name = "deleted_at")
        LocalDateTime deletedAt;

        @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                        CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
        @JoinColumn(name = "bill_id")
        Bill bill;

        @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                        CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
        @JoinColumn(name = "service_id")
        Service service;
}