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
@Table(name = "other_answers")
public class OtherAnswer {
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

        @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                        CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
        @JoinColumn(name = "question_id")
        Question question;

        @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                        CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        User user;
}