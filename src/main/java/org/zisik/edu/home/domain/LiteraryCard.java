package org.zisik.edu.home.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LiteraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LiteraryType type;

    @Lob
    private String content;

    private String author;
    private String source;
    private LocalDate displayDate;
    private Long createdBy;
    private LocalDateTime createdAt;

    @Builder
    public LiteraryCard(LiteraryType type, String content, String author,
                        String source, LocalDate displayDate, Long createdBy) {
        this.type = type;
        this.content = content;
        this.author = author;
        this.source = source;
        this.displayDate = displayDate;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
    }
}
