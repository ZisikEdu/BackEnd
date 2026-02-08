package org.zisik.edu.note.request;

import lombok.Getter;
import lombok.Setter;
import org.zisik.edu.note.domain.NoteType;

@Getter
@Setter
public class CreateBookNoteRequest {

    private NoteType noteType = NoteType.FREE;
    private String content;
}
