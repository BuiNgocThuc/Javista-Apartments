package com.example.javista.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "apartments")
public class Apartment {
        @Id
        @Column(name = "id")
        String id;

        @Column(name = "area")
        Float area;

        @Column(name = "description")
        String description;

        @Column(name = "floor_number")
        Integer floorNumber;

        @Column(name = "apartment_number")
        Integer apartmentNumber;

        @Column(name = "status")
        String status;

        @Column(name = "created_at")
        LocalDateTime createdAt;

        @Column(name = "updated_at")
        LocalDateTime updatedAt;

        @Column(name = "deleted_at")
        LocalDateTime deletedAt;

        @OneToMany(mappedBy = "apartment", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                        CascadeType.DETACH, CascadeType.REFRESH})
        Set<Relationship> relationships;
}