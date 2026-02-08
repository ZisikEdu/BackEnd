package org.zisik.edu.flow.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFlowSession is a Querydsl query type for FlowSession
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlowSession extends EntityPathBase<FlowSession> {

    private static final long serialVersionUID = -1361851756L;

    public static final QFlowSession flowSession = new QFlowSession("flowSession");

    public final NumberPath<Long> accountId = createNumber("accountId", Long.class);

    public final NumberPath<Long> duration = createNumber("duration", Long.class);

    public final NumberPath<Integer> endPage = createNumber("endPage", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> endTime = createDateTime("endTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> sessionDate = createDate("sessionDate", java.time.LocalDate.class);

    public final NumberPath<Integer> startPage = createNumber("startPage", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> startTime = createDateTime("startTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> userLibraryId = createNumber("userLibraryId", Long.class);

    public final StringPath videoUrl = createString("videoUrl");

    public QFlowSession(String variable) {
        super(FlowSession.class, forVariable(variable));
    }

    public QFlowSession(Path<? extends FlowSession> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFlowSession(PathMetadata metadata) {
        super(FlowSession.class, metadata);
    }

}

