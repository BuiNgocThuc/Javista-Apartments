package com.example.javista.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "services")
public class Service {
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer id;

        @Column(name = "name")
        String name;

        @Column(name = "description")
        String description;

        @Column(name = "price")
        Float price;

        @Column(name = "created_at")
        LocalDateTime createdAt;

        @Column(name = "updated_at")
        LocalDateTime updatedAt;

        @Column(name = "deleted_at")
        LocalDateTime deletedAt;

        @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
        Set<BillDetail> billDetails;
}