package org.zisik.edu.library.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserLibrary is a Querydsl query type for UserLibrary
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserLibrary extends EntityPathBase<UserLibrary> {

    private static final long serialVersionUID = 1350371811L;

    public static final QUserLibrary userLibrary = new QUserLibrary("userLibrary");

    public final NumberPath<Long> accountId = createNumber("accountId", Long.class);

    public final NumberPath<Long> bookId = createNumber("bookId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> completedAt = createDateTime("completedAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> currentPage = createNumber("currentPage", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> registeredAt = createDateTime("registeredAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> rereadCount = createNumber("rereadCount", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> startedAt = createDateTime("startedAt", java.time.LocalDateTime.class);

    public final EnumPath<ReadingStatus> status = createEnum("status", ReadingStatus.class);

    public final NumberPath<Integer> userRating = createNumber("userRating", Integer.class);

    public QUserLibrary(String variable) {
        super(UserLibrary.class, forVariable(variable));
    }

    public QUserLibrary(Path<? extends UserLibrary> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserLibrary(PathMetadata metadata) {
        super(UserLibrary.class, metadata);
    }

}

