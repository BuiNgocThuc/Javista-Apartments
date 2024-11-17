package com.example.javista.entity;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.javista.enums.UserGender;
import com.example.javista.enums.UserType;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @Column(name = "avatar")
    String avatar;

    @Column(name = "is_first_login")
    Boolean isFirstLogin;

    @Column(name = "email")
    String email;

    @Column(name = "phone")
    String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    UserGender gender;

    @Column(name = "full_name")
    String fullName;

    @Column(name = "nation_id")
    String nationId;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    UserType userType;

    @Column(name = "date_of_birth")
    LocalDateTime dateOfBirth;

    @Column(name = "is_staying")
    Boolean isStaying;

    @CreatedDate
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    LocalDateTime deletedAt;

    @OneToMany(
            mappedBy = "user",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    Set<UserAnswer> userAnswers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    Set<Relationship> relationships;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    Set<Item> items;

    @OneToMany(
            mappedBy = "user",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    Set<Survey> surveys;

    @OneToMany(
            mappedBy = "user",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    Set<OtherAnswer> otherAnswers;
}
