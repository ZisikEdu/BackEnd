package org.zisik.edu.mail.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long senderId;
    private String subject;

    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    private FeedbackStatus status;

    private LocalDateTime createdAt;

    @Builder
    public Feedback(Long senderId, String subject, String content) {
        this.senderId = senderId;
        this.subject = subject;
        this.content = content;
        this.status = FeedbackStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    public void updateStatus(FeedbackStatus status) {
        this.status = status;
    }
}
