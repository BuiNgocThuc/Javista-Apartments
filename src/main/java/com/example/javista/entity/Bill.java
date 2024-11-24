package com.example.javista.entity;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.javista.enums.BillStatus;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "bills")
public class Bill {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "monthly")
    String monthly;

    @Column(name = "total_price")
    Float totalPrice;

    @Column(name = "old_water")
    Integer oldWater;

    @Column(name = "new_water")
    Integer newWater;

    @Column(name = "water_reading_date")
    LocalDateTime waterReadingDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    BillStatus status;

    @CreatedDate
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    LocalDateTime deletedAt;

    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "relationship_id")
    Relationship relationship;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    Set<BillDetail> billDetails;
}
