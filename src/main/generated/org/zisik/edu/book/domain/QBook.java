package org.zisik.edu.book.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBook is a Querydsl query type for Book
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBook extends EntityPathBase<Book> {

    private static final long serialVersionUID = 2126956696L;

    public static final QBook book = new QBook("book");

    public final StringPath aladinItemId = createString("aladinItemId");

    public final StringPath author = createString("author");

    public final StringPath category = createString("category");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath description = createString("description");

    public final StringPath genre = createString("genre");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath isbn = createString("isbn");

    public final BooleanPath isManualEntry = createBoolean("isManualEntry");

    public final DateTimePath<java.time.LocalDateTime> publishedDate = createDateTime("publishedDate", java.time.LocalDateTime.class);

    public final StringPath publisher = createString("publisher");

    public final NumberPath<Double> rating = createNumber("rating", Double.class);

    public final StringPath title = createString("title");

    public final NumberPath<Integer> totalPages = createNumber("totalPages", Integer.class);

    public QBook(String variable) {
        super(Book.class, forVariable(variable));
    }

    public QBook(Path<? extends Book> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBook(PathMetadata metadata) {
        super(Book.class, metadata);
    }

}

