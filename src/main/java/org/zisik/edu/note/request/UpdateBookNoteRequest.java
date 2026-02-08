package org.zisik.edu.note.request;

import lombok.Getter;
import lombok.Setter;
import org.zisik.edu.note.domain.NoteType;

@Getter
@Setter
public class UpdateBookNoteRequest {

    private NoteType noteType;
    private String content;
}
