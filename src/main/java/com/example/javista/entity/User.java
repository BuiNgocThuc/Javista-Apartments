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
        private Byte isFirstLogin;

        @Column(name = "email")
        private String email;

        @Column(name = "phone")
        private String phone;

        @Column(name = "gender")
        private String gender;

        @Column(name = "full_name")
        private String fullName;

        @Column(name = "nation_id")
        private String nationId;

        @Column(name = "user_type")
        private String userType;

        @Column(name = "date_of_birth")
        private LocalDateTime dateOfBirth;

        @Column(name = "is_staying")
        private Byte isStaying;

        @Column(name = "created_at")
        private LocalDateTime createdAt;

        @Column(name = "updated_at")
        private LocalDateTime updatedAt;

        @Column(name = "deleted_at")
        private LocalDateTime deletedAt;

        @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                        CascadeType.DETACH, CascadeType.REFRESH})
        Set<UserAnswer> userAnswers;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        Set<Relationship> relationships;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        Set<Item> items;

        @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                        CascadeType.DETACH, CascadeType.REFRESH})
        Set<Survey> surveys;

        @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                        CascadeType.DETACH, CascadeType.REFRESH})
        Set<OtherAnswer> otherAnswers;
}
