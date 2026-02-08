package org.zisik.edu.note.response;

import lombok.Builder;
import lombok.Getter;
import org.zisik.edu.flow.response.FlowMemoResponse;
import org.zisik.edu.note.domain.BookNote;
import org.zisik.edu.note.domain.NoteType;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class BookNoteResponse {

    private Long id;
    private Long userLibraryId;
    private NoteType noteType;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<FlowMemoResponse> flowMemos;  // 집중 모드 메모들

    public static BookNoteResponse from(BookNote note) {
        return BookNoteResponse.builder()
                .id(note.getId())
                .userLibraryId(note.getUserLibraryId())
                .noteType(note.getNoteType())
                .content(note.getContent())
                .createdAt(note.getCreatedAt())
                .updatedAt(note.getUpdatedAt())
                .build();
    }

    public static BookNoteResponse from(BookNote note, List<FlowMemoResponse> flowMemos) {
        return BookNoteResponse.builder()
                .id(note.getId())
                .userLibraryId(note.getUserLibraryId())
                .noteType(note.getNoteType())
                .content(note.getContent())
                .createdAt(note.getCreatedAt())
                .updatedAt(note.getUpdatedAt())
                .flowMemos(flowMemos)
                .build();
    }
}
