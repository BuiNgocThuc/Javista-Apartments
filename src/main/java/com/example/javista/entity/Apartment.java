package com.example.javista.entity;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.javista.enums.ApartmentStatus;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
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

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    ApartmentStatus status;

    @Column(name = "current_water_number")
    Integer currentWaterNumber;

    @CreatedDate
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    LocalDateTime deletedAt;

    @OneToMany(
            mappedBy = "apartment",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<Relationship> relationships;
}
