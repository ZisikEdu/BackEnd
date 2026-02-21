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
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long recipientId;

    @Enumerated(EnumType.STRING)
    private MailType type;

    private String title;

    @Lob
    private String content;

    private Boolean isRead;
    private LocalDateTime createdAt;

    @Builder
    public Mail(Long recipientId, MailType type, String title, String content) {
        this.recipientId = recipientId;
        this.type = type;
        this.title = title;
        this.content = content;
        this.isRead = false;
        this.createdAt = LocalDateTime.now();
    }

    public void markAsRead() {
        this.isRead = true;
    }
}
