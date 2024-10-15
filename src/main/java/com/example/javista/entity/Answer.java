package com.example.javista.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "answers")
public class Answer implements Serializable {
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

        @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                        CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
        @JoinColumn(name = "question_id")
        Question question;

        @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
        Set<UserAnswer> userAnswers;
}