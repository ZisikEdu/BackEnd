package org.zisik.edu.book.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommit is a Querydsl query type for Commit
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommit extends EntityPathBase<Commit> {

    private static final long serialVersionUID = -370473178L;

    public static final QCommit commit = new QCommit("commit");

    public final NumberPath<Long> accountId = createNumber("accountId", Long.class);

    public final NumberPath<Long> bookId = createNumber("bookId", Long.class);

    public final StringPath contents = createString("contents");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCommit(String variable) {
        super(Commit.class, forVariable(variable));
    }

    public QCommit(Path<? extends Commit> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommit(PathMetadata metadata) {
        super(Commit.class, metadata);
    }

}

