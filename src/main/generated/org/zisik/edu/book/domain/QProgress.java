package org.zisik.edu.book.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProgress is a Querydsl query type for Progress
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProgress extends EntityPathBase<Progress> {

    private static final long serialVersionUID = 58777692L;

    public static final QProgress progress = new QProgress("progress");

    public final NumberPath<Long> accountId = createNumber("accountId", Long.class);

    public final NumberPath<Long> bookId = createNumber("bookId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> readingStartDate = createDateTime("readingStartDate", java.time.LocalDateTime.class);

    public final TimePath<java.time.LocalTime> readingTime = createTime("readingTime", java.time.LocalTime.class);

    public final NumberPath<Integer> tasks = createNumber("tasks", Integer.class);

    public QProgress(String variable) {
        super(Progress.class, forVariable(variable));
    }

    public QProgress(Path<? extends Progress> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProgress(PathMetadata metadata) {
        super(Progress.class, metadata);
    }

}

