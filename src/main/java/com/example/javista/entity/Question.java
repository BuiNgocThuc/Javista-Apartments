package com.example.javista.entity;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "questions")
public class Question {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "content")
    String content;

    @CreatedDate
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    LocalDateTime deletedAt;

    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    Survey survey;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    Set<Answer> answers;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    Set<OtherAnswer> otherAnswers;
}
