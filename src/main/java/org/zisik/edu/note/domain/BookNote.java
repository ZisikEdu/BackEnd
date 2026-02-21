package org.zisik.edu.note.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"userLibraryId"}))
public class BookNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userLibraryId;   // UNIQUE - 책당 하나

    @Enumerated(EnumType.STRING)
    private NoteType noteType;

    @Lob
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public BookNote(Long userLibraryId, NoteType noteType, String content) {
        this.userLibraryId = userLibraryId;
        this.noteType = noteType != null ? noteType : NoteType.FREE;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateContent(String content) {
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateNoteType(NoteType noteType) {
        this.noteType = noteType;
        this.updatedAt = LocalDateTime.now();
    }
}
