package org.zisik.edu.home.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLiteraryCard is a Querydsl query type for LiteraryCard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLiteraryCard extends EntityPathBase<LiteraryCard> {

    private static final long serialVersionUID = -1715100359L;

    public static final QLiteraryCard literaryCard = new QLiteraryCard("literaryCard");

    public final StringPath author = createString("author");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> createdBy = createNumber("createdBy", Long.class);

    public final DatePath<java.time.LocalDate> displayDate = createDate("displayDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath source = createString("source");

    public final EnumPath<LiteraryType> type = createEnum("type", LiteraryType.class);

    public QLiteraryCard(String variable) {
        super(LiteraryCard.class, forVariable(variable));
    }

    public QLiteraryCard(Path<? extends LiteraryCard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLiteraryCard(PathMetadata metadata) {
        super(LiteraryCard.class, metadata);
    }

}

