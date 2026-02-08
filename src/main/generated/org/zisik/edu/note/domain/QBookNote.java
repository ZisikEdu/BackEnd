package org.zisik.edu.note.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBookNote is a Querydsl query type for BookNote
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookNote extends EntityPathBase<BookNote> {

    private static final long serialVersionUID = -2061884493L;

    public static final QBookNote bookNote = new QBookNote("bookNote");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<NoteType> noteType = createEnum("noteType", NoteType.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> userLibraryId = createNumber("userLibraryId", Long.class);

    public QBookNote(String variable) {
        super(BookNote.class, forVariable(variable));
    }

    public QBookNote(Path<? extends BookNote> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBookNote(PathMetadata metadata) {
        super(BookNote.class, metadata);
    }

}

