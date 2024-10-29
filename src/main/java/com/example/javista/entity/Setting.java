package com.example.javista.entity;

import com.example.javista.enums.SystemStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "settings")
public class Setting {
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer id;

        @Column(name = "current_monthly")
        String currentMonthly;

        @Enumerated(EnumType.STRING)
        @Column(name = "system_status")
        SystemStatus systemStatus;

        @Column(name = "room_price_per_m2")
        Float roomPricePerM2;

        @Column(name = "water_price_per_m3")
        Float waterPricePerM3;

        @Column(name = "room_vat")
        Float roomVat;

        @Column(name = "water_vat")
        Integer waterVat;

        @Column(name = "env_protection_tax")
        Integer envProtectionTax;
}